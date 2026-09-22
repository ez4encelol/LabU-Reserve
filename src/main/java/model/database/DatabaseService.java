package model.database;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Equipment;
import model.Reservation;
import model.userhierarchy.User;
import model.repository.EquipmentRepository;
import model.repository.ReservationRepository;
import model.repository.UserRepository;

import java.util.List;

/**
 * Service for cross-table database queries (e.g. reservations for an equipment,
 * users by email). Replaces the old CSV-backed singleton.
 */
@Service
@Transactional(readOnly = true)
public class DatabaseService {

    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public DatabaseService(EquipmentRepository equipmentRepository,
                           UserRepository userRepository,
                           ReservationRepository reservationRepository) {
        this.equipmentRepository = equipmentRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    /** Delegate to Spring for singleton-style access from GUI classes. */
    public static DatabaseService getInstance() {
        return model.SpringContext.getBean(DatabaseService.class);
    }

    /** Finds all reservations associated with this equipment. */
    public List<Reservation> getEquipmentReservations(Equipment equipment) {
        return reservationRepository.findByEquipment(equipment);
    }

    /** Returns the user with the given email, or null if not found. */
    public User findUser(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    /** Returns all reservations for a particular user. */
    public List<Reservation> getUserReservations(User u) {
        return reservationRepository.findByUserId(u.getId());
    }

    /** Returns all equipment records. */
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    /** Returns all user records. */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /** Returns all reservation records. */
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    /** Returns the equipment with the given ID, or null. */
    public Equipment getEquipment(int id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    /** Returns the user with the given ID, or null. */
    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    /** Returns the reservation with the given ID, or null. */
    public Reservation getReservation(int id) {
        return reservationRepository.findById(id).orElse(null);
    }

    /** Flush pending writes (JPA auto-flushes at commit, so this is effectively a no-op). */
    @Transactional
    public void updateAllTables() {
        // JPA/Hibernate auto-flushes dirty entities at transaction commit.
        // No manual CSV write needed.
    }

    // --- methods retained for test backward compatibility ---

    @Transactional
    public void removeAllUsers() {
        userRepository.deleteAll();
    }

    @Transactional
    public void removeAllEquipments() {
        equipmentRepository.deleteAll();
    }

    @Transactional
    public void removeAllReservations() {
        reservationRepository.deleteAll();
    }

    public static void resetToNull() {
        // No-op — Spring manages the bean lifecycle
    }
}
