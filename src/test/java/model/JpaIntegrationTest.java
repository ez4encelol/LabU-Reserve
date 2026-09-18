package model;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import model.Equipment;
import model.LabUReserveApplication;
import model.Reservation;
import model.UserFactory;
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
 * Integration test verifying the Spring Boot + H2 + Flyway + JPA stack.
 * Confirms that Flyway migrations run, entities persist correctly,
 * and repositories return expected results.
 */
@SpringBootTest(classes = LabUReserveApplication.class)
@Transactional
class JpaIntegrationTest {

    @Autowired EquipmentRepository equipmentRepository;
    @Autowired UserRepository userRepository;
    @Autowired ReservationRepository reservationRepository;
    @Autowired DatabaseService databaseService;
    @Autowired UserFactory userFactory;

    @BeforeEach
    void clean() {
        reservationRepository.deleteAll();
        userRepository.deleteAll();
        equipmentRepository.deleteAll();
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
