package model.systemFacades;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import model.Checkout;
import model.Equipment;
import model.Reservation;
import model.enums.EquipmentStatus;
import model.enums.ReservationStatus;
import model.paymentclasses.PaymentStrategy;
import model.repository.EquipmentRepository;
import model.repository.ReservationRepository;
import model.repository.UserRepository;
import model.database.DatabaseService;
import model.userhierarchy.User;

/**
 * Facade for equipment reservation operations: conflicts, reservations,
 * payments, extensions, cancellations. Backed by Spring Data JPA repositories.
 */
@Component
public class EquipmentReservationFacade {

    private final ReservationRepository reservationRepository;
    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;
    private final DatabaseService databaseService;

    public EquipmentReservationFacade(
            ReservationRepository reservationRepository,
            EquipmentRepository equipmentRepository,
            UserRepository userRepository,
            DatabaseService databaseService) {
        this.reservationRepository = reservationRepository;
        this.equipmentRepository = equipmentRepository;
        this.userRepository = userRepository;
        this.databaseService = databaseService;
    }

    /** Delegate to Spring for singleton-style access from GUI classes. */
    public static EquipmentReservationFacade getInstance() {
        return model.SpringContext.getBean(EquipmentReservationFacade.class);
    }

    public void makePayment(Reservation reservation, int amount, PaymentStrategy strategy) {
        int totalOwed = reservation.getTotalOwed();
        if (totalOwed - amount >= 0) {
            reservation.setTotalOwed(totalOwed - amount);
        } else {
            reservation.setTotalOwed(0);
        }
        Checkout checkout = new Checkout();
        checkout.setPaymentStrategy(strategy);
        checkout.makePayment(amount);
    }

    public boolean hasConflict(LocalDateTime startTime, LocalDateTime endTime,
                               int userId, int equipmentId) {
        List<Reservation> reservations = reservationRepository.findByEquipmentId(equipmentId);
        for (Reservation r : reservations) {
            if (r.getEquipmentId() == equipmentId &&
                (r.getStatus() == ReservationStatus.ACTIVE || r.getStatus() == ReservationStatus.ARRIVED)) {
                if (startTime.isBefore(r.getEndTime()) && endTime.isAfter(r.getStartTime())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Transactional
    public boolean reserveEquipment(LocalDateTime startTime, LocalDateTime endTime,
                                    User u, Equipment e, PaymentStrategy strategy) {
        if (hasConflict(startTime, endTime, u.getId(), e.getId())) {
            return false;
        }
        if (e.getStatus() != EquipmentStatus.ENABLED) {
            return false;
        }
        Reservation r = new Reservation(0, startTime, endTime, u, e);
        r.setDepositAmount(u.getHourlyRate());
        long hoursBetween = ChronoUnit.HOURS.between(startTime, endTime);
        long minutesBetween = ChronoUnit.MINUTES.between(startTime, endTime);
        int totalOwed;
        if (minutesBetween % 60 != 0) {
            totalOwed = (int)(hoursBetween + 1) * u.getHourlyRate();
        } else {
            totalOwed = (int)hoursBetween * u.getHourlyRate();
        }
        r.setTotalOwed(totalOwed);
        r.setStatus(ReservationStatus.ACTIVE);
        Checkout checkout = new Checkout();
        checkout.setPaymentStrategy(strategy);
        checkout.makePayment(u.getHourlyRate());
        reservationRepository.save(r);
        return true;
    }

    @Transactional
    public boolean extendReservation(Reservation reservation, int hours) {
        Equipment equipment = equipmentRepository.findById(reservation.getEquipmentId())
                .orElseThrow(() -> new IllegalStateException("Equipment not found"));
        if (equipment.getStatus() != EquipmentStatus.ENABLED) {
            return false;
        }
        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            return false;
        }
        LocalDateTime newEndTime = reservation.getEndTime().plusHours(hours);
        if (hasConflict(reservation.getEndTime(), newEndTime, reservation.getUserId(), reservation.getEquipmentId())) {
            return false;
        }
        reservation.extendReservation(hours);
        return true;
    }

    @Transactional
    public boolean cancelReservation(Reservation reservation) {
        if (reservation.getStartTime().isAfter(LocalDateTime.now())) {
            reservation.cancelReservation();
            return true;
        }
        return false;
    }

    public List<Equipment> getEnabledEquipment() {
        List<Equipment> allEquipment = equipmentRepository.findAll();
        List<Equipment> availableEquipment = new ArrayList<>();
        for (Equipment e : allEquipment) {
            if (e.getStatus() == EquipmentStatus.ENABLED) {
                availableEquipment.add(e);
            }
        }
        return availableEquipment;
    }

    public List<Reservation> getUserReservations(User u) {
        return databaseService.getUserReservations(u);
    }

    public List<Reservation> getActiveUserReservations(User u) {
        List<Reservation> allReservations = databaseService.getUserReservations(u);
        List<Reservation> notCancelled = new ArrayList<>();
        for (Reservation r : allReservations) {
            if (r.getStatus() == ReservationStatus.ACTIVE) {
                notCancelled.add(r);
            }
        }
        return notCancelled;
    }

    public String getUnavailableTimes(Equipment e) {
        StringBuilder result = new StringBuilder();
        List<Reservation> allReservations = databaseService.getEquipmentReservations(e);
        for (int i = 0; i < allReservations.size(); i++) {
            Reservation r = allReservations.get(i);
            if (i < allReservations.size() - 1) {
                result.append("Start: ").append(r.getStartTime().toString())
                        .append(" End: ").append(r.getEndTime().toString()).append("; ");
            } else {
                result.append("Start: ").append(r.getStartTime().toString())
                        .append(" End: ").append(r.getEndTime().toString());
            }
        }
        return result.toString();
    }
}
