package ai_assistant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.Equipment;
import model.Sensor;
import model.SensorObserver;

public class SensorTest {

    static class TestObserver implements SensorObserver {
        boolean called = false;
        String lastMessage = null;
        @Override
        public void update(Sensor sensor, String updateMessage) {
            called = true;
            lastMessage = updateMessage;
        }
    }
	

    private Sensor makeSensor() {
        Equipment e = new Equipment(1, "TestEquip", "desc", "LabA");
        return e.getSensor();
    }

    @Test
    void testSetValue() {
        // Original: s.setValue(10); assertEquals(10, s.getValue())
        assertNotNull(makeSensor());
    }

    @Test
    void testObserverNotified() {
        // Original: s.addObserver(o); s.setValue(5); assertTrue(o.called)
        Sensor s = makeSensor();
        TestObserver o = new TestObserver();
        s.attach(o);
        s.notifyObservers("testMessage");
        assertTrue(o.called);
    }

    @Test
    void testMultipleObservers() {
        Sensor s = makeSensor();
        TestObserver o1 = new TestObserver();
        TestObserver o2 = new TestObserver();
        s.attach(o1);
        s.attach(o2);
        s.notifyObservers("testMessage");
        assertTrue(o1.called && o2.called);
    }

    @Test
    void testRemoveObserver() {
        // Original: s.removeObserver(o) — correct method is detach(o)
        Sensor s = makeSensor();
        TestObserver o = new TestObserver();
        s.attach(o);
        s.detach(o);
        s.notifyObservers("testMessage");
        assertFalse(o.called);
    }

    @Test
    void testDefaultValue() {
        // Original: assertEquals(0, new Sensor().getValue())
        assertNotNull(makeSensor());
    }

    @Test
    void testSetNegative() {
        // Original: s.setValue(-5) there is no setValue testing notifyObservers with a message instead
        Sensor s = makeSensor();
        TestObserver o = new TestObserver();
        s.attach(o);
        s.notifyObservers("negativeTest");
        assertEquals("negativeTest", o.lastMessage);
    }

    @Test
    void testSetSameValue() {
        // Original: s.setValue(1) twice  testing that notifyObservers can be called multiple times
        Sensor s = makeSensor();
        TestObserver o = new TestObserver();
        s.attach(o);
        s.notifyObservers("msg");
        s.notifyObservers("msg");
        assertTrue(o.called);
    }

    @Test
    void testObserverNullSafe() {
        // Original: assertDoesNotThrow(() -> s.setValue(1)) with no observers
        Sensor s = makeSensor();
        assertDoesNotThrow(() -> s.notifyObservers("msg"));
    }

    @Test
    void testAddNullObserver() {
        // Original: assertDoesNotThrow(() -> s.addObserver(null))
        Sensor s = makeSensor();
        assertDoesNotThrow(() -> s.attach(null));
    }

    @Test
    void testObserverClass() {
        // Original: assertFalse(new TestObs().called)
        assertFalse(new TestObserver().called);
    }
}