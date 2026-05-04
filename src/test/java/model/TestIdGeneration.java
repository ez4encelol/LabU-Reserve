package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;


//test the id generation class to check if the Id generator correctly generates the id's
class TestIdGeneration {
	 
	@BeforeEach
	void setUp() {
		IdGeneration.resetToNull();
	}
 
	@AfterEach
	void tearDown() {
		IdGeneration.resetToNull();
	}
 
 
	@Test
	void testSingletonInstance() {
		// getInstance should always return the same object
		IdGeneration a = IdGeneration.getInstance();
		IdGeneration b = IdGeneration.getInstance();
		assertSame(a, b);
	}
 
	@Test
	void testNextUserIdIncrement() {
		// nextUserId should increase by 1 on each call
		IdGeneration idGen = IdGeneration.getInstance();
		idGen.setUserIdCount(1);
		assertEquals(1, idGen.nextUserId());
		assertEquals(2, idGen.nextUserId());
		assertEquals(3, idGen.nextUserId());
	}
 
	@Test
	void testNextEquipmentIdIncrement() {
		// nextEquipmentId should increase by 1 on each call
		IdGeneration idGen = IdGeneration.getInstance();
		idGen.setEquipmentIdCount(1);
		assertEquals(1, idGen.nextEquipmentId());
		assertEquals(2, idGen.nextEquipmentId());
	}
 
	@Test
	void testNextReservationIdIncrement() {
		// nextReservationId should increase by 1 on each call
		IdGeneration idGen = IdGeneration.getInstance();
		idGen.setReservationIdCount(5);
		assertEquals(5, idGen.nextReservationId());
		assertEquals(6, idGen.nextReservationId());
	}
 
	@Test
	void testSetUserIdCount() {
		// setUserIdCount should set the starting id
		IdGeneration idGen = IdGeneration.getInstance();
		idGen.setUserIdCount(10);
		assertEquals(10, idGen.nextUserId());
	}
 
	@Test
	void testSetEquipmentIdCount() {
		// setEquipmentIdCount should set the starting id
		IdGeneration idGen = IdGeneration.getInstance();
		idGen.setEquipmentIdCount(7);
		assertEquals(7, idGen.nextEquipmentId());
	}
 
	@Test
	void testSetReservationIdCount() {
		// setReservationIdCount should set the starting id
		IdGeneration idGen = IdGeneration.getInstance();
		idGen.setReservationIdCount(100);
		assertEquals(100, idGen.nextReservationId());
	}
 
	@Test
	void testResetToNull() {
		// after resetToNull, getInstance returns a new instance
		IdGeneration first = IdGeneration.getInstance();
		first.setUserIdCount(50);
		IdGeneration.resetToNull();
		IdGeneration second = IdGeneration.getInstance();
		// new instance starts at 0 by default because we didnt initialize it
		assertEquals(0, second.nextUserId());
	}
 
	@Test
	void testIdCountersAreIndependent() {
		// user, equipment, reservation counters should not affect each other
		IdGeneration idGen = IdGeneration.getInstance();
		idGen.setUserIdCount(1);
		idGen.setEquipmentIdCount(10);
		idGen.setReservationIdCount(100);
		assertEquals(1, idGen.nextUserId());
		assertEquals(10, idGen.nextEquipmentId());
		assertEquals(100, idGen.nextReservationId());
	}
 
}
