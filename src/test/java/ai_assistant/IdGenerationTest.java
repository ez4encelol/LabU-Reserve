package ai_assistant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.IdGeneration;

public class IdGenerationTest {

    @BeforeEach
    void setUp() {
        IdGeneration.resetToNull();
    }

    @AfterEach
    void tearDown() {
        IdGeneration.resetToNull();
    }

    @Test
    void testIncrement() {
        // Original: assertTrue(g.getNextEquipmentId() < g.getNextEquipmentId())
        // getNextEquipmentId() does not exist correct method is nextEquipmentId()
        IdGeneration g = IdGeneration.getInstance();
        g.setEquipmentIdCount(1);
        int first = g.nextEquipmentId();
        int second = g.nextEquipmentId();
        assertTrue(first < second);
    }

    @Test
    void testSingleton() {
        assertSame(IdGeneration.getInstance(), IdGeneration.getInstance());
    }

    @Test
    void testMultipleCalls() {
        IdGeneration g = IdGeneration.getInstance();
        g.setEquipmentIdCount(1);
        g.nextEquipmentId();
        g.nextEquipmentId();
        assertTrue(true);
    }

    @Test
    void testNotNegative() {
        // Original: assertTrue(g.getNextEquipmentId() >= 0)
        IdGeneration g = IdGeneration.getInstance();
        g.setEquipmentIdCount(1);
        assertTrue(g.nextEquipmentId() >= 0);
    }

    @Test
    void testSequence() {
        IdGeneration g = IdGeneration.getInstance();
        int a = g.nextEquipmentId();
        int b = g.nextEquipmentId();
        assertTrue(b > a);
    }

    @Test
    void testLargeCalls() {
        IdGeneration g = IdGeneration.getInstance();
        g.setEquipmentIdCount(1);
        for (int i = 0; i < 100; i++) g.nextEquipmentId();
        assertTrue(true);
    }

    @Test
    void testConsistency() {
        assertNotNull(IdGeneration.getInstance());
    }

    @Test
    void testUnique() {
        // Original: assertNotEquals(g.getNextEquipmentId(), g.getNextEquipmentId())
        IdGeneration g = IdGeneration.getInstance();
        g.setEquipmentIdCount(1);
        assertNotEquals(g.nextEquipmentId(), g.nextEquipmentId());
    }

    @Test
    void testNoCrash() {
        assertDoesNotThrow(() -> IdGeneration.getInstance());
    }

    @Test
    void testInstanceEquality() {
        assertSame(IdGeneration.getInstance(), IdGeneration.getInstance());
    }
}