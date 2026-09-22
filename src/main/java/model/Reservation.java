package model;

import java.time.LocalDateTime;
import javax.persistence.*;
import model.enums.ReservationStatus;
import model.userhierarchy.User;

/**
 * JPA entity for equipment reservations.
 * Uses {@code @ManyToOne} relationships to Equipment and User
 * instead of raw integer foreign keys.
 */
@Entity
@Table(name = "reservations")
@Access(AccessType.FIELD)
public class Reservation implements SensorObserver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private int totalOwed;
    private int depositAmount;

    /** JPA no-arg constructor */
    protected Reservation() {}

    /** Constructor using entity references (preferred for JPA). */
    public Reservation(LocalDateTime startTime, LocalDateTime endTime, User user, Equipment equipment) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.equipment = equipment;
    }

    /** Constructor with explicit ID — for programmatic creation with known ID. */
    public Reservation(int id, LocalDateTime startTime, LocalDateTime endTime, User user, Equipment equipment) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.equipment = equipment;
    }

    /** @deprecated Use {@link #Reservation(LocalDateTime, LocalDateTime, User, Equipment)}
     *  — the int-ID constructor cannot resolve entity references without a repository. */
    @Deprecated
    public Reservation(int id, LocalDateTime startTime, LocalDateTime endTime, int userId, int equipmentId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Equipment getEquipment() {
        return this.equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
    public int getEquipmentId() {
        return equipment != null ? equipment.getId() : 0;
    }

    @Transient
    public int getUserId() {
        return user != null ? user.getId() : 0;
    }

    public ReservationStatus getStatus() {
        return this.status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public int getTotalOwed() {
        return this.totalOwed;
    }

    public void setTotalOwed(int totalOwed) {
        this.totalOwed = totalOwed;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getDepositAmount() {
        return this.depositAmount;
    }

    public void cancelReservation() {
        status = ReservationStatus.CANCELLED;
    }

    public void extendReservation(int hours) {
        if (user != null) {
            totalOwed += hours * user.getHourlyRate();
        }
        endTime = endTime.plusHours(hours);
    }

    @Override
    public void update(Sensor sensor, String updateMessage) {
        if (updateMessage.equals("arrive") && status == ReservationStatus.ACTIVE) {
            LocalDateTime currentTime = LocalDateTime.now();
            if (currentTime.isAfter(startTime) && currentTime.isBefore(startTime.plusMinutes(20))) {
                status = ReservationStatus.ARRIVED;
                if (totalOwed - depositAmount >= 0) {
                    totalOwed -= depositAmount;
                } else {
                    totalOwed = 0;
                }
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s %s",
                id,
                startTime.toString(),
                endTime.toString(),
                getEquipmentId(),
                getUserId(),
                status.toString(),
                totalOwed,
                depositAmount);
    }
}
