package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.EquipmentStatus;
import model.enums.ReservationStatus;
import model.paymentclasses.CreditPayment;
import model.paymentclasses.DebitPayment;
import model.paymentclasses.InstitutionalGrantPayment;
import model.paymentclasses.ResearchGrantPayment;
import model.systemFacades.EquipmentManagementFacade;
import model.systemFacades.EquipmentReservationFacade;
import model.systemFacades.LoginAndRegistrationFacade;
import model.userhierarchy.HeadLabCoordinator;
import model.userhierarchy.User;

//Test the csv files labelled test1
public class TestEquipmentReservation {
	EquipmentManagementFacade equipmentManagement;
	EquipmentReservationFacade reservationFacade;
	LoginAndRegistrationFacade registrationAndLoginFacade;
	DatabaseService sDb;
	EquipmentTable eDb;
	ReservationTable rDb;
	UserTable uDb;
	IdGeneration idGenerator;
	
	String originalUserPath;
	String originalReservationPath;
	String originalEquipmentPath;
	
	String userPath;
	String reservationPath;
	String equipmentPath;
	
	String projectRoot = System.getProperty("user.dir");
    String folderPath=projectRoot + File.separator + 
        "src" + File.separator + "resources" + File.separator + "test_csv_files" + 
        File.separator + "TestEquipmentReservation";
	
	//reset the csv file by replacing the test file contents with the original file; reset it
	public void resetCSV() throws IOException {
	    Files.copy(
	        Paths.get(originalUserPath),
	        Paths.get(userPath),
	        StandardCopyOption.REPLACE_EXISTING);
	    Files.copy(
		        Paths.get(originalReservationPath),
		        Paths.get(reservationPath),
		        StandardCopyOption.REPLACE_EXISTING);
	    Files.copy(
		        Paths.get(originalEquipmentPath),
		        Paths.get(equipmentPath),
		        StandardCopyOption.REPLACE_EXISTING);
	}
	
	void updateTables() {
		eDb.update();
		rDb.update();
		uDb.update();
	}
	
	//note, junit creates a seperate object for each test case, this beforeEach
	//runs before each test case, and it sets up the test object
	@BeforeEach
	void setUp(TestInfo testInfo) throws IOException {
		String testName = testInfo.getTestMethod().get().getName();
		String testPath = folderPath + File.separator + testName;

	    //Create folder if it doesn't exist
	    Files.createDirectories(Paths.get(testPath));
	    
		//set the paths to the files
		originalUserPath=folderPath + File.separator + "Original_files" + File.separator + "User_original.csv";
		originalReservationPath=folderPath + File.separator + "Original_files" + File.separator + "Reservation_original.csv";
		originalEquipmentPath=folderPath + File.separator + "Original_files" + File.separator + "Equipment_original.csv";
		
		userPath=testPath + File.separator + "User.csv";
		reservationPath=testPath + File.separator + "Reservation.csv";
		equipmentPath=testPath + File.separator + "Equipment.csv";
				
		//reset the singleton objects before every test case
		IdGeneration.resetToNull();
		EquipmentTable.resetToNull();
		ReservationTable.resetToNull();
		DatabaseService.resetToNull();
		UserTable.resetToNull();
		HeadLabCoordinator.resetToNull();
		
		//copy the original file into the test file before loading the file from the databases
		resetCSV();
		
		//get database instances
		uDb=UserTable.getInstance();
		eDb=EquipmentTable.getInstance();
		rDb=ReservationTable.getInstance();
		sDb=DatabaseService.getInstance();
		
		//update the path to custom path
		eDb.setCustomDBPath(equipmentPath);
		uDb.setCustomDBPath(userPath);
		rDb.setCustomDBPath(reservationPath);
		
		//load the database from the new custom path
		eDb.loadFromDB();
		uDb.loadFromDB();
		rDb.loadFromDB();
		
		//setup the system
		equipmentManagement=new EquipmentManagementFacade();
		reservationFacade=new EquipmentReservationFacade();
		registrationAndLoginFacade=new LoginAndRegistrationFacade();
		
		idGenerator=IdGeneration.getInstance();
	}
	
	@AfterEach
	void tearDown() {
	    IdGeneration.resetToNull();
	    EquipmentTable.resetToNull();
	    ReservationTable.resetToNull();
	    DatabaseService.resetToNull();
	    UserTable.resetToNull();
	    HeadLabCoordinator.resetToNull();
	}
	
  @Test
  void testGetEnabledEquipment() {
      List<Equipment> list = reservationFacade.getEnabledEquipment();

      assertEquals(3, list.size());

      for (Equipment e : list) {
          assertEquals(EquipmentStatus.ENABLED, e.getStatus());
      }
  }

  @Test
  void testReserveEquipmentSuccess() {
      User u = registrationAndLoginFacade.loginUser("jsmith@university.ca", "pass123");
      Equipment e = eDb.getEquipment(1);

      boolean result = reservationFacade.reserveEquipment(
          LocalDateTime.parse("2026-03-18T09:00"),
          LocalDateTime.parse("2026-03-18T12:00"),
          u, e, new CreditPayment()
      );

      assertTrue(result);
      assertNotNull(rDb.getReservation(6));

      updateTables();
  }
  
  @Test
  void testReserveEquipmentConflict() {
      User u = registrationAndLoginFacade.loginUser("tech1@lab.ca", "secure1");
      Equipment e = eDb.getEquipment(1);

      boolean result = reservationFacade.reserveEquipment(
          LocalDateTime.parse("2026-03-20T10:00"),
          LocalDateTime.parse("2026-03-20T12:00"),
          u, e, new ResearchGrantPayment()
      );

      assertFalse(result);

      updateTables();
  }

  @Test
  void testReserveEquipmentDisabled() {
      User u = registrationAndLoginFacade.loginUser("jsmith@university.ca", "pass123");
      Equipment e = eDb.getEquipment(4); // DISABLED

      boolean result = reservationFacade.reserveEquipment(
          LocalDateTime.parse("2026-03-18T09:00"),
          LocalDateTime.parse("2026-03-18T12:00"),
          u, e, new InstitutionalGrantPayment()
      );

      assertFalse(result);

      updateTables();
  }

  @Test
  void testReserveEquipmentMaintenance() {
      User u = registrationAndLoginFacade.loginUser("jsmith@university.ca", "pass123");
      Equipment e = eDb.getEquipment(2); // MAINTENANCE

      boolean result = reservationFacade.reserveEquipment(
          LocalDateTime.parse("2026-03-18T09:00"),
          LocalDateTime.parse("2026-03-18T12:00"),
          u, e, new DebitPayment()
      );

      assertFalse(result);

      updateTables();
  }

  @Test
  void testReserveEquipmentCostExact() {
      User u = registrationAndLoginFacade.loginUser("jsmith@university.ca", "pass123"); // rate = 10
      Equipment e = eDb.getEquipment(1);

      reservationFacade.reserveEquipment(
          LocalDateTime.parse("2026-03-18T09:00"),
          LocalDateTime.parse("2026-03-18T12:00"),
          u, e, new CreditPayment()
      );

      Reservation r = rDb.getReservation(6);

      assertEquals(30, r.getTotalOwed());

      updateTables();
  }

  @Test
  void testReserveEquipmentCostRoundUp() {
      User u = registrationAndLoginFacade.loginUser("jsmith@university.ca", "pass123");
      Equipment e = eDb.getEquipment(1);

      reservationFacade.reserveEquipment(
          LocalDateTime.parse("2026-03-18T09:00"),
          LocalDateTime.parse("2026-03-18T11:30"),
          u, e, new CreditPayment()
      );

      Reservation r = rDb.getReservation(6);

      assertEquals(30, r.getTotalOwed());

      updateTables();
  }

  @Test
  void testExtendReservationSuccess() {
      Reservation r = rDb.getReservation(1); // ends 11:00

      boolean result = reservationFacade.extendReservation(r, 1);

      assertTrue(result);
      assertEquals("2026-03-20T12:00", r.getEndTime().toString());

      updateTables();
  }
  
  @Test
  void testExtendReservationCancelled() {
      Reservation r = rDb.getReservation(1);
      r.setStatus(ReservationStatus.CANCELLED);

      boolean result = reservationFacade.extendReservation(r, 1);

      assertFalse(result);

      updateTables();
  }
  
  @Test
  void testExtendReservationConflict() {
      Reservation r = rDb.getReservation(1);

      boolean result = reservationFacade.extendReservation(r, 30);

      assertFalse(result);

      updateTables();
  }

  @Test
  void testExtendReservationDisabledEquipment() {
      Reservation r = rDb.getReservation(1);
      eDb.getEquipment(1).setStatus(EquipmentStatus.DISABLED);

      boolean result = reservationFacade.extendReservation(r, 1);

      assertFalse(result);

      updateTables();
  }

  @Test
  void testCancelReservationSuccess() {
      Reservation r = rDb.getReservation(5);//Reservation in June

      boolean result = reservationFacade.cancelReservation(r);

      assertTrue(result);
      assertEquals(ReservationStatus.CANCELLED, r.getStatus());

      updateTables();
  }

  @Test
  void testCancelReservationFail() {
      Reservation r = rDb.getReservation(1);//Reservation in March

      boolean result = reservationFacade.cancelReservation(r);

      assertFalse(result);

      updateTables();
  }

  @Test
  void testGetUserReservations() {
      User u = registrationAndLoginFacade.loginUser("lee@university.ca", "teach789");//No reservations at all

      List<Reservation> list = reservationFacade.getUserReservations(u);

      assertTrue(list.isEmpty());
  }

  @Test
  void testGetActiveUserReservationsOnlyActive() {
      User u = registrationAndLoginFacade.loginUser("jsmith@university.ca", "pass123");

      List<Reservation> list = reservationFacade.getActiveUserReservations(u);

      assertEquals(1, list.size());

      for (Reservation r : list) {
          assertEquals(ReservationStatus.ACTIVE, r.getStatus());
      }
  }
  @Test
  void testGetUnavailableTimes() {
      Equipment e = eDb.getEquipment(1);

      String result = reservationFacade.getUnavailableTimes(e);

      String expected =
          "Start: 2026-03-20T09:00 End: 2026-03-20T11:00; " +
          "Start: 2026-03-21T10:00 End: 2026-03-21T12:00";

      assertEquals(expected, result);
  }

  @Test
  void testMakePaymentReducesTotal() {
      Reservation r = rDb.getReservation(1); // totalOwed = 20

      reservationFacade.makePayment(r, 10, new CreditPayment());

      assertEquals(10, r.getTotalOwed());

      updateTables();
  }

  @Test
  void testMakePaymentOverpay() {
      Reservation r = rDb.getReservation(1); // totalOwed = 20

      reservationFacade.makePayment(r, 50, new CreditPayment());

      assertEquals(0, r.getTotalOwed());

      updateTables();
  }
  
  @Test
	void testEquipmentReservation() {
		//add a reservation for an equipment
		LocalDateTime r1StartTime=LocalDateTime.parse("2026-03-18T09:00");
		LocalDateTime r1EndTime=LocalDateTime.parse("2026-03-18T12:00");
		String u1Email="jsmith@university.ca";
		String u1Password="pass123";
		User u1=registrationAndLoginFacade.loginUser(u1Email, u1Password);
		List<Equipment> availableEquipment=reservationFacade.getEnabledEquipment();
		assertEquals(3, availableEquipment.size());
		Equipment eId1=null;
		//find the equipment with equipment id 1
		for(Equipment a : availableEquipment) {
			if(a.getId()==1) {
				eId1=a;
			}
		}
		boolean success1=reservationFacade.reserveEquipment(r1StartTime, r1EndTime, u1, eId1, new CreditPayment());
		assertTrue(success1);
		List<Reservation> u1Reservations = reservationFacade.getUserReservations(u1);
		assertEquals(3, u1Reservations.size());
		//get a list of the strings of the rows of the tables
		List<String> u1ReservationRowsList=new ArrayList<String>();
		for(Reservation r : u1Reservations) {
			u1ReservationRowsList.add(r.toString());
		}
		
		//check if the list contains the reservation just added
		//deposit made with type of user as STUDENT so totalOwed should be (3 hours)*(10)
		
		String addedReservationRow= String.format("%s %s %s %s %s %s %s %s", 
				String.valueOf(6), 
				r1StartTime.toString(), 
				r1EndTime.toString(), 
				String.valueOf(eId1.getId()),
				String.valueOf(u1.getId()), 
				ReservationStatus.ACTIVE,
				String.valueOf(u1.getHourlyRate()*3),
				String.valueOf(u1.getHourlyRate()));
		
		assertTrue(u1ReservationRowsList.contains(addedReservationRow));
		
		//alternatively, just find it from the database
		assertTrue(u1Reservations.contains(rDb.getReservation(6))); //should be 6th reservation so id=6
		
		//check that the newly added reservation cannot be cancelled, since the time already passed from reservation
		assertFalse(reservationFacade.cancelReservation(rDb.getReservation(6)));
		assertEquals(ReservationStatus.ACTIVE, rDb.getReservation(6).getStatus());
		
		//add an entry into the far future and try to cancel it
		LocalDateTime r2StartTime=LocalDateTime.parse("2030-03-18T08:00");
		LocalDateTime r2EndTime=LocalDateTime.parse("2030-03-18T15:00");
		
		String u2Email="guest@email.com";
		String u2Password="guest111";
		User u2=registrationAndLoginFacade.loginUser(u2Email, u2Password);
		Equipment eId2=null;
		//find the equipment with equipment id 2
		for(Equipment a : availableEquipment) {
			if(a.getId()==5) {
				eId2=a;
			}
		}
		assertNotNull(eId2);
		boolean success2=reservationFacade.reserveEquipment(r2StartTime, r2EndTime, u2, eId2, new DebitPayment());
		assertTrue(success2);
		
		List<Reservation> u2Reservations = reservationFacade.getUserReservations(u2);
		assertTrue(u2Reservations.contains(rDb.getReservation(7)));
		assertEquals(rDb.getReservation(7).getTotalOwed(), u2.getHourlyRate()*7);
		//try to cancel, it should let you cancel
		assertTrue(reservationFacade.cancelReservation(rDb.getReservation(7)));
		assertEquals(ReservationStatus.CANCELLED, rDb.getReservation(7).getStatus());
		
		//try to add reservation that conflicts with the others
		String u3Email="tech1@lab.ca";
		String u3Password="secure1";
		User u3=registrationAndLoginFacade.loginUser(u3Email, u3Password);
		
		boolean failure=reservationFacade.reserveEquipment(r1StartTime.minusHours(1), r1EndTime, u3, eId1, new ResearchGrantPayment());
		assertFalse(failure);
		assertNull(rDb.getReservation(8));
		//test extending the equipment reservation that is cancelled:
		assertFalse(reservationFacade.extendReservation(rDb.getReservation(7), 2));
		
		//test extending equipment reservation success:
		Reservation reservation6=rDb.getReservation(6);
		assertEquals(reservation6.getTotalOwed(), 3*u1.getHourlyRate()); //before extending check that the amount owed is correct
		assertTrue(reservationFacade.extendReservation(reservation6, 25));
		assertEquals(reservation6.getTotalOwed(), (3+25)*u1.getHourlyRate());
		//print out unavailable times to the console to check and see if output is correct (need to look at it directly)
		assertEquals(reservation6.getStartTime().toString(), "2026-03-18T09:00");
		assertEquals(reservation6.getEndTime().toString(), "2026-03-19T13:00");
		
		//test out extending reservation success
		Reservation reservation1 = rDb.getReservation(1);
		assertTrue(reservationFacade.extendReservation(reservation1, 15)); //when NOSHOW because the starttime already passed, can't extend
		assertEquals(reservation1.getEndTime().toString(), "2026-03-21T02:00");
		
		//test out extending reservation failure
		//temporarily change the reservation 4 to be ACTIVE to show that reservations with time conflicts can't be made
		rDb.getReservation(4).setStatus(ReservationStatus.ACTIVE);
		assertFalse(reservationFacade.extendReservation(reservation1, 9));
		assertEquals(reservation1.getEndTime().toString(), "2026-03-21T02:00");
		
		//test out extending reservation to the exact start time of another one (edge case)
		assertTrue(reservationFacade.extendReservation(reservation1, 8));
		assertEquals(reservation1.getEndTime().toString(), "2026-03-21T10:00");
		assertEquals(reservation1.getEndTime().toString(), rDb.getReservation(4).getStartTime().toString());
		
		
		//make one more reservation in the far future, then check the file to see if reservation8 is ACTIVE
		LocalDateTime r3StartTime=LocalDateTime.parse("2060-03-18T08:00");
		LocalDateTime r3EndTime=LocalDateTime.parse("2060-03-18T15:00");
		boolean success3=reservationFacade.reserveEquipment(r3StartTime, r3EndTime, u2, eDb.getEquipment(5), new DebitPayment());
		assertTrue(success3);
		assertEquals(rDb.getReservation(8).getStartTime(), r3StartTime);
		
		//try to make reservation on unavailable equipment
		assertFalse(reservationFacade.reserveEquipment(r3StartTime.plusYears(5), r3EndTime.plusYears(6), u3, eDb.getEquipment(6), new ResearchGrantPayment()));
		assertFalse(reservationFacade.reserveEquipment(r3StartTime.plusYears(5), r3EndTime.plusYears(6), u3, eDb.getEquipment(6), new ResearchGrantPayment()));
		assertFalse(reservationFacade.reserveEquipment(r3StartTime.plusYears(5), r3EndTime.plusYears(6), u3, eDb.getEquipment(4), new ResearchGrantPayment()));
		
		
		//print out the unavailable times in the console:
		List<Equipment> allEquipment = eDb.getEquipmentAsList();
		for(Equipment e : allEquipment) {
			System.out.print("Equipment id " +e.getId() +": ");
			System.out.println(reservationFacade.getUnavailableTimes(e));
		}
		
		//update the tables
		updateTables();
		
	}
	
	@Test
	void testEquipmentReservation2() {
		//test more payment methods 
		
		//test the institutional grant payment when user makes reservation
		//login to user labtech1
		String u1Email = "tech1@lab.ca";
		String u1password = "secure1";
		LocalDateTime u1StartTime = LocalDateTime.parse("2040-03-18T09:00");
		LocalDateTime u1EndTime = LocalDateTime.parse("2040-03-18T09:00");
		User u1 = registrationAndLoginFacade.loginUser(u1Email, u1password);
		Equipment e5 = eDb.getEquipment(5);
		assertTrue(reservationFacade.reserveEquipment(u1StartTime, u1EndTime, u1, e5, new InstitutionalGrantPayment())); //reservation should be successful
		Reservation r6=rDb.getReservation(6);
		assertNotNull(r6);
		assertEquals(u1StartTime, r6.getStartTime());
		assertEquals(ReservationStatus.ACTIVE, r6.getStatus());
		updateTables();
	}
	
	@Test
	void testEquipmentReservation3() {
		//test the research grant payment when user makes reservation
		//login to user labtech1
		String u1Email = "tech1@lab.ca";
		String u1password = "secure1";
		LocalDateTime u1StartTime = LocalDateTime.parse("2040-03-18T09:00");
		LocalDateTime u1EndTime = LocalDateTime.parse("2040-03-18T09:00");
		User u1 = registrationAndLoginFacade.loginUser(u1Email, u1password);
		Equipment e5 = eDb.getEquipment(5);
		assertTrue(reservationFacade.reserveEquipment(u1StartTime, u1EndTime, u1, e5, new ResearchGrantPayment())); //reservation should be successful
		Reservation r6=rDb.getReservation(6);
		assertNotNull(r6);
		assertEquals(u1StartTime, r6.getStartTime());
		assertEquals(ReservationStatus.ACTIVE, r6.getStatus());
		updateTables();
	}
	
}
