package model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Reservation;
import model.Equipment;
import model.userhierarchy.User;

import java.util.List;

/**
 * Spring Data JPA repository for {@link Reservation} entities.
 * Uses explicit JPQL queries to avoid property-name ambiguity
 * with SINGLE_TABLE inheritance and field-based access.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.user.userId = :userId")
    List<Reservation> findByUserId(@Param("userId") int userId);

    @Query("SELECT r FROM Reservation r WHERE r.equipment.id = :equipmentId")
    List<Reservation> findByEquipmentId(@Param("equipmentId") int equipmentId);

    List<Reservation> findByUser(User user);
    List<Reservation> findByEquipment(Equipment equipment);
}
