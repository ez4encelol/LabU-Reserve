package model;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.enums.EquipmentStatus;
import model.enums.ReservationStatus;
import model.enums.UserType;
import model.repository.EquipmentRepository;
import model.repository.UserRepository;
import model.repository.ReservationRepository;
import model.userhierarchy.User;
import model.database.DatabaseService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Shared test methods for PostgreSQL integration tests.
 * Concrete subclasses pick the database strategy:
 * <ul>
 *   <li>{@link JpaIntegrationTest} — Testcontainers PostgreSQL 16 (CI / Docker)</li>
 *   <li>{@link JpaLocalIntegrationTest} — local PostgreSQL at localhost:5432 (local dev)</li>
 * </ul>
 */
@SpringBootTest(classes = LabUReserveApplication.class)
@Transactional
abstract class AbstractJpaTest {

    @Autowired EquipmentRepository equipmentRepository;
    @Autowired UserRepository userRepository;
    @Autowired ReservationRepository reservationRepository;
    @Autowired DatabaseService databaseService;
    @Autowired UserFactory userFactory;

    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    void clean() {
        // TRUNCATE is transactional in PostgreSQL (rollback-safe) and executes
        // immediately — unlike deleteAll() which defers DELETEs until Hibernate
        // flush. This avoids "duplicate key" errors when Hibernate flushes
        // INSERTs before DELETEs within the same transaction.
        // CASCADE handles FK dependencies (reservations → users, equipment).
        // RESTART IDENTITY resets serial sequences so nextval() starts at 1.
        entityManager.createNativeQuery(
            "TRUNCATE TABLE equipment, users RESTART IDENTITY CASCADE"
        ).executeUpdate();
    }

    @Test
    void flywayMigrationsCreateTables() {
        // If Flyway ran, the tables exist and we can query them
        assertEquals(0, equipmentRepository.findAll().size());
        assertEquals(0, userRepository.findAll().size());
        assertEquals(0, reservationRepository.findAll().size());
    }

    @Test
    void testEquipmentCrud() {
        Equipment e = new Equipment(0, "Oscilloscope", "Digital scope", "Lab B", EquipmentStatus.ENABLED);
        equipmentRepository.save(e);

        List<Equipment> all = equipmentRepository.findAll();
        assertEquals(1, all.size());
        assertEquals("Oscilloscope", all.get(0).getName());
        assertTrue(all.get(0).getId() > 0); // JPA @GeneratedValue assigned
    }

    @Test
    void testUserInheritance() {
        User student = userFactory.createUser(
            "student@test.edu", "john", "Pass123!", 12345, UserType.STUDENT
        );
        userRepository.save(student);

        User fetched = userRepository.findByEmail("student@test.edu").orElse(null);
        assertNotNull(fetched);
        assertEquals(UserType.STUDENT, fetched.getUserType());
        assertEquals(10, fetched.getHourlyRate());
    }

    @Test
    void testReservationWithManyToOne() {
        User student = userFactory.createUser(
            "student@test.edu", "john", "Pass123!", 12345, UserType.STUDENT
        );
        Equipment eq = new Equipment(0, "3D Printer", "Resin printer", "Lab A", EquipmentStatus.ENABLED);
        userRepository.save(student);
        equipmentRepository.save(eq);

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        Reservation r = new Reservation(0, start, end, student, eq);
        r.setStatus(ReservationStatus.ACTIVE);
        reservationRepository.save(r);

        List<Reservation> reservations = reservationRepository.findByUserId(student.getId());
        assertEquals(1, reservations.size());
        assertEquals("3D Printer", reservations.get(0).getEquipment().getName());
        assertEquals("john", reservations.get(0).getUser().getUsername());
    }
}
