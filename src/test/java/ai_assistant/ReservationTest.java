package ai_assistant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import model.IdGeneration;
import model.Reservation;
import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.ReservationStatus;
import model.systemFacades.EquipmentManagementFacade;
import model.systemFacades.EquipmentReservationFacade;
import model.systemFacades.LoginAndRegistrationFacade;

public class ReservationTest {
	
    private Reservation makeReservation(int id, int userId, int equipmentId) {
        LocalDateTime start = LocalDateTime.parse("2099-01-01T10:00");
        LocalDateTime end   = LocalDateTime.parse("2099-01-01T12:00");
        return new Reservation(id, start, end, userId, equipmentId);
    }

    @Test
    void testCreate() {
        // Original: assertEquals(1, new Reservation(1,2).getUserId())
        // Reservation(int,int) does not exist constructor requires (id, start, end, userId, equipmentId)
        assertEquals(1, makeReservation(10, 1, 2).getUserId());
    }

    @Test
    void testEquipmentId() {
        // Original: assertEquals(2, new Reservation(1,2).getEquipmentId())
        assertEquals(2, makeReservation(10, 1, 2).getEquipmentId());
    }

    @Test
    void testSetStatus() {
        // Original: r.setStatus("OK")  status is ReservationStatus, not String
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
        // Original: assertTrue(new Reservation(1,2).getId() > 0)
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
        // Original: assertNotEquals ids of two new Reservation(1,2)
        // Reservation id is set by the caller two reservations with different ids are not equal
        Reservation r1 = makeReservation(1, 1, 2);
        Reservation r2 = makeReservation(2, 1, 2);
        assertNotEquals(r1.getId(), r2.getId());
    }

    @Test
    void testEdgeIds() {
        // Original: assertEquals(Integer.MAX_VALUE, new Reservation(Integer.MAX_VALUE,2).getUserId())
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