package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.EquipmentStatus;
import model.enums.ReservationStatus;
import model.enums.UserType;
import model.paymentclasses.CreditPayment;
import model.systemFacades.EquipmentManagementFacade;
import model.systemFacades.EquipmentReservationFacade;
import model.systemFacades.LoginAndRegistrationFacade;
import model.userhierarchy.*;

/*class to test any extra test cases that were either for classes that are too small to have their
own test file, or for extra tests that don't quite fall under any test class category
*/
public class TestAuxilliaryExtra {
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
        File.separator + "TestAuxilliaryExtra";
	
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
	    originalUserPath=folderPath + File.separator + "Original_files" +  File.separator + "User_original.csv";
		originalReservationPath=folderPath + File.separator + "Original_files" +  File.separator + "Reservation_original.csv";
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
	void testDepartmentApprovalUniversityAffiliated() {
		String u4Email = "lee@university.ca";
		String u4Password = "teach789";
		User u4 = registrationAndLoginFacade.loginUser(u4Email, u4Password);
		assertNotNull(u4);
		assertFalse(u4.getIsDepartmentApproved());
		Department dp = new Department();
		assertTrue(dp.approveAccount(u4));
		assertTrue(u4.getIsDepartmentApproved());
		updateTables();
	}
	
	@Test
	void testDepartmentApprovalNotUniversityAffiliated() {
		String u5Email = "guest@email.com";
		String u5Password = "guest111";
		User u5 = registrationAndLoginFacade.loginUser(u5Email, u5Password);
		assertNotNull(u5);
		assertFalse(u5.getIsDepartmentApproved());
		Department dp = new Department();
		assertFalse(dp.approveAccount(u5));
		assertFalse(u5.getIsDepartmentApproved());
		updateTables();
	}
	
	@Test
	void testUserFactoryReturnsCorrectUserType() {
		UserFactory uf= new UserFactory();
		String email = "hi";
		String password = "123asda@";
		String userName = "aName";
		int verificationNum= 2;
		User headLabCoordinator = uDb.getUser(7);
		String oldHeadLabCoordinatorEmail = headLabCoordinator.getEmail();
		assertTrue(uf.createUser(email, userName, password, verificationNum, UserType.STUDENT) instanceof Student);
		assertTrue(uf.createUser(email, userName, password, verificationNum, UserType.FACULTY) instanceof Faculty);
		assertTrue(uf.createUser(email, userName, password, verificationNum, UserType.GUEST) instanceof Guest);
		assertTrue(uf.createUser(email, userName, password, verificationNum, UserType.LABMANAGER) instanceof LabManager);
		headLabCoordinator = uf.createUser(email, userName, password, verificationNum, UserType.HEADLABCOORDINATOR);
		assertTrue(uf.createUser(email, userName, password, verificationNum, UserType.RESEARCHER) instanceof Researcher);
		assertTrue(headLabCoordinator instanceof HeadLabCoordinator);
		//check that the head lab coordinator created by the factory overrided the old head lab coordinator attributes
		assertNotEquals(oldHeadLabCoordinatorEmail, headLabCoordinator.getEmail());
		updateTables();
	}
	
	//fat test to see if system for equipment management works fine in a complete use case scenario
  	@Test
	void testEquipmentManagementFat() {
		List<Equipment> allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(6, allEquipment.size());
		String e1Name="Oscilloscope";
		String e1Description = "Rigol DS1054Z Digital Oscilloscope 50 MHz";
		String e1LabLocation = "Las03";
		equipmentManagement.addEquipment(e1Name, e1Description, e1LabLocation);
		Equipment newEquipment = eDb.getEquipment(7);
		assertEquals(e1Name, newEquipment.getName());
		assertEquals(e1Description, newEquipment.getDescription());
		assertEquals(e1LabLocation, newEquipment.getLabLocation());
		allEquipment=equipmentManagement.getAllEquipment();
		assertEquals(7, allEquipment.size());
		assertTrue(allEquipment.contains(newEquipment));
		equipmentManagement.disableEquipment(newEquipment);
		equipmentManagement.markAsUnavailable(eDb.getEquipment(1));
		assertEquals(EquipmentStatus.MAINTENANCE, eDb.getEquipment(1).getStatus());
		assertEquals(EquipmentStatus.DISABLED, newEquipment.getStatus());
		updateTables();
	}
  	
  	@Test
	void testSensorsAndDepartmentApproval() {
		Sensor sensor1; //sensor for equipment of id1
		sensor1=eDb.getEquipment(1).getSensor();
		Equipment equipment1 = eDb.getEquipment(1);
		assertEquals(EquipmentStatus.ENABLED, equipment1.getStatus());
		//get the user reservations, and check their status
		List<Reservation> equipment1Reservations = sDb.getEquipmentReservations(equipment1);
		//the equipment reservations should all be NOSHOW for the equipment
		assertTrue(equipment1Reservations.contains(rDb.getReservation(1)));
		assertTrue(equipment1Reservations.contains(rDb.getReservation(4)));
		assertEquals(2, equipment1Reservations.size());
		assertEquals(ReservationStatus.ACTIVE, rDb.getReservation(1).getStatus());
		assertEquals(ReservationStatus.ACTIVE, rDb.getReservation(4).getStatus());
		
		Reservation reservation1=rDb.getReservation(1);
		Reservation reservation4=rDb.getReservation(4);
		//get the old values of the totalOwed before arrival
		int totalOwedR1=reservation1.getTotalOwed();
		int totalOwedR4=reservation4.getTotalOwed();
		
		sensor1.arrive();
		assertEquals(EquipmentStatus.ENABLED, equipment1.getStatus());
		//check that the totalOwed doesn't change after arrival
		assertEquals(totalOwedR1, reservation1.getTotalOwed());
		assertEquals(totalOwedR4, reservation4.getTotalOwed());
		
		//test departure
		sensor1.departure();
		assertEquals(EquipmentStatus.ENABLED, equipment1.getStatus());
		
		//check that none of the reservations changed their status since the current time is 
		//past 20 minutes of their start time
		for(Reservation r: equipment1Reservations) {
			assertEquals(ReservationStatus.ACTIVE, r.getStatus());
		}
		List<Reservation> allReservations=rDb.getReservationsAsList();
		for(Reservation r: allReservations) {
			assertTrue(ReservationStatus.ACTIVE == r.getStatus());
		}
		
		//add a reservation at the current time, then check if arrival can be made on it
		String u1Email="jsmith@university.ca";
		String u1Password="pass123";
		User u1=registrationAndLoginFacade.loginUser(u1Email, u1Password);
		Reservation reservation6;
		LocalDateTime r6StartTime=LocalDateTime.now();
		LocalDateTime r6EndTime=r6StartTime.plusMinutes(55); //add 55 minutes so to get 1 hour totalOwed
		assertTrue(reservationFacade.reserveEquipment(r6StartTime, r6EndTime, u1, equipment1, new CreditPayment()));
		reservation6=rDb.getReservation(6);
		//check sensor arrival
		assertEquals(u1.getHourlyRate(), reservation6.getTotalOwed());
		assertEquals(ReservationStatus.ACTIVE, reservation6.getStatus());
		/*wait one second to be able to arrive at a time slightly, later. 
		 * Test case sometimes fails if this delay isn't present, most likely because
		 * the time between reserving at time now() is so close to calling arrive(), which
		 * checks the current time now() and compares it to the reservation start time
		 * and checks if the current time is after the reservation start time
		 */
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		reservation6.getEquipment().getSensor().arrive();
		assertEquals(ReservationStatus.ARRIVED, reservation6.getStatus());
		assertEquals(EquipmentStatus.ENABLED, equipment1.getStatus());
		assertFalse(reservationFacade.cancelReservation(reservation6)); //try to cancel, can't cancel reservation
		//check that the deposit was subtracted from the totalOwed
		
		
		//add a reservation that is 21 minutes before the current time, and check if reservation can be made on it
		Reservation reservation7;
		LocalDateTime r7StartTime=LocalDateTime.now().minusMinutes(21);
		LocalDateTime r7EndTime=r6StartTime.plusHours(1);
		assertFalse(reservationFacade.reserveEquipment(r7StartTime, r7EndTime, u1, equipment1, new CreditPayment())); //try to reserve with conflicting times
		r7EndTime=r7StartTime.plusMinutes(5); //change r7EndTime so it doesn't conflict
		assertTrue(reservationFacade.reserveEquipment(r7StartTime, r7EndTime, u1, equipment1, new CreditPayment()));
		reservation7=rDb.getReservation(7);
		
		//check the sensor arrival, since made 21 minutes before current time, the arrival should not be able to be made
		assertEquals(ReservationStatus.ACTIVE, reservation7.getStatus());
		reservation7.getEquipment().getSensor().arrive();
		assertEquals(ReservationStatus.ACTIVE, reservation7.getStatus());
		
		//add a reservation that is 10 minutes before the current time, and check if reservation can be made on it (should be able to create reservation
		Reservation reservation8;
		LocalDateTime r8StartTime=LocalDateTime.now().minusMinutes(10);
		LocalDateTime r8EndTime=r8StartTime.plusMinutes(5);
		assertTrue(reservationFacade.reserveEquipment(r8StartTime, r8EndTime, u1, equipment1, new CreditPayment()));
		reservation8=rDb.getReservation(8);
		
		//check the sensor arrival, since made 10 minutes before current time, the arrival should be able to be made
		assertEquals(ReservationStatus.ACTIVE, reservation8.getStatus());
		int reservation8BeforeTotalOwed=reservation8.getTotalOwed();
		reservation8.getEquipment().getSensor().arrive();
		assertEquals(ReservationStatus.ARRIVED, reservation8.getStatus());
		//check that the deposit was subtracted from the total
		assertEquals(reservation8BeforeTotalOwed-u1.getHourlyRate(), reservation8.getTotalOwed());
		assertEquals(0, reservation8.getTotalOwed());
		
		//test the department approval
		Department dept=new Department();
		assertEquals(false, u1.getIsDepartmentApproved());
		dept.approveAccount(u1);
		assertEquals(true, u1.getIsDepartmentApproved());
		
		//test making payments
		reservationFacade.makePayment(reservation8, 10, new CreditPayment());
		assertEquals(0, reservation8.getTotalOwed());

		updateTables();
	}
}