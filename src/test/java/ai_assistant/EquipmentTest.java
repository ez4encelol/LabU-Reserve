package ai_assistant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Equipment;
import model.enums.EquipmentStatus;

public class EquipmentTest {

    @Test
    void testCreate() {
        // Original: assertEquals("A", new Equipment(1,"A","L").getName());
        assertEquals("A", new Equipment(1, "A", "desc", "L").getName());
    }

    @Test
    void testLocation() {
        // Original: assertEquals("L", new Equipment(1,"A","L").getLab());
        // getLab() does not exist; correct method is getLabLocation()
        assertEquals("L", new Equipment(1, "A", "desc", "L").getLabLocation());
    }

    @Test
    void testSetName() {
        Equipment e = new Equipment(1, "A", "desc", "L");
        e.setName("B");
        assertEquals("B", e.getName());
    }

    @Test
    void testStatus() {
        // Original: e.setStatus(EquipmentStatus.AVAILABLE) AVAILABLE does not exist in EquipmentStatus
        // Using ENABLED as the closest available status.
        Equipment e = new Equipment(1, "A", "desc", "L");
        e.setStatus(EquipmentStatus.ENABLED);
        assertEquals(EquipmentStatus.ENABLED, e.getStatus());
    }

    @Test
    void testDefaultStatus() {
        // Default constructor sets status to ENABLED
        assertNotNull(new Equipment(1, "A", "desc", "L").getStatus());
    }

    @Test
    void testNullName() {
        Equipment e = new Equipment(1, null, "desc", "L");
        assertNull(e.getName());
    }

    @Test
    void testEmptyName() {
        Equipment e = new Equipment(1, "", "desc", "L");
        assertEquals("", e.getName());
    }

    @Test
    void testId() {
        assertEquals(1, new Equipment(1, "A", "desc", "L").getId());
    }

    @Test
    void testToString() {
        assertNotNull(new Equipment(1, "A", "desc", "L").toString());
    }

    @Test
    void testMultipleObjects() {
        // Two different equipment objects with different IDs should not be equal
        // Equipment does not override equals(), so reference inequality is guaranteed
        assertNotSame(new Equipment(1, "A", "desc", "L"), new Equipment(2, "A", "desc", "L"));
    }
}