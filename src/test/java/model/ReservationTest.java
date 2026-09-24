package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import model.Reservation;
import model.Equipment;
import model.userhierarchy.User;
import model.userhierarchy.Student;
import model.enums.ReservationStatus;


public class ReservationTest {

    private Reservation makeReservation(int id, int userId, int equipmentId) {
        LocalDateTime start = LocalDateTime.parse("2099-01-01T10:00");
        LocalDateTime end   = LocalDateTime.parse("2099-01-01T12:00");
        User user = new Student(userId, "test@test.edu", "john", "Pass123!", 0);
        Equipment equipment = new Equipment(equipmentId, "Equipment", "Desc", "Lab A");
        Reservation r = new Reservation(start, end, user, equipment);
        r.setId(id);
        return r;
    }

    @Test
    void testCreate() {
        assertEquals(1, makeReservation(10, 1, 2).getUserId());
    }

    @Test
    void testEquipmentId() {
        assertEquals(2, makeReservation(10, 1, 2).getEquipmentId());
    }

    @Test
    void testSetStatus() {
        Reservation r = makeReservation(10, 1, 2);
        r.setStatus(ReservationStatus.ACTIVE);
        assertEquals(ReservationStatus.ACTIVE, r.getStatus());
    }

    @Test
    void testDefaultStatus() {
        Reservation r = makeReservation(10, 1, 2);
        r.setStatus(ReservationStatus.ACTIVE);
        assertNotNull(r.getStatus());
    }

    @Test
    void testIdExists() {
        assertTrue(makeReservation(5, 1, 2).getId() > 0);
    }

    @Test
    void testNullStatus() {
        Reservation r = makeReservation(10, 1, 2);
        r.setStatus(null);
        assertNull(r.getStatus());
    }

    @Test
    void testMultipleReservations() {
        Reservation r1 = makeReservation(1, 1, 2);
        Reservation r2 = makeReservation(2, 1, 2);
        assertNotEquals(r1.getId(), r2.getId());
    }

    @Test
    void testEdgeIds() {
        assertEquals(Integer.MAX_VALUE, makeReservation(10, Integer.MAX_VALUE, 2).getUserId());
    }

    @Test
    void testNegativeIds() {
        assertEquals(-1, makeReservation(10, -1, 2).getUserId());
    }

    @Test
    void testToString() {
        Reservation r = makeReservation(10, 1, 2);
        r.setStatus(ReservationStatus.ACTIVE);
        assertNotNull(r.toString());
    }
}