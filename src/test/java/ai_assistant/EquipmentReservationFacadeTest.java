package ai_assistant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import model.Equipment;
import model.IdGeneration;
import model.Reservation;
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
import model.userhierarchy.User;
import model.UserFactory;

public class EquipmentReservationFacadeTest {
	
	EquipmentManagementFacade equipmentManagementFacade;
	EquipmentReservationFacade facade;
	LoginAndRegistrationFacade registrationAndLoginFacade;
	DatabaseService sDb;
	EquipmentTable eDb;
	ReservationTable rDb;
	UserTable uDb;
	IdGeneration idGenerator;
	
    User testUser;
    Equipment testEquipment;
	
	void updateTables() {
		eDb.update();
		rDb.update();
		uDb.update();
	}
	
	String originalUserPath;
	String originalReservationPath;
	String originalEquipmentPath;
	
	String userPath;
	String reservationPath;
	String equipmentPath;
	
	String projectRoot = System.getProperty("user.dir");
    String folderPath=projectRoot + File.separator + 
        "src" + File.separator + "resources" + File.separator + "test_csv_files" + 
        File.separator + "Ai_Tests_Empty_Files";
	
    
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
		equipmentManagementFacade=new EquipmentManagementFacade();
		facade=new EquipmentReservationFacade();
		registrationAndLoginFacade=new LoginAndRegistrationFacade();
		
		testUser = new UserFactory().createUser(1, "u@test.com", "user", "pass", 0, UserType.STUDENT);
        uDb.addUser(testUser);

        testEquipment = new Equipment(1, "Scope", "desc", "LabA", EquipmentStatus.ENABLED);
        eDb.addEquipment(testEquipment);
        
		idGenerator=IdGeneration.getInstance();
	}
	
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
	
	@AfterEach
	void tearDown() {
	    IdGeneration.resetToNull();
	    EquipmentTable.resetToNull();
	    ReservationTable.resetToNull();
	    DatabaseService.resetToNull();
	    UserTable.resetToNull();
	}
	
    LocalDateTime futureStart = LocalDateTime.parse("2099-01-01T10:00");
    LocalDateTime futureEnd   = LocalDateTime.parse("2099-01-01T12:00");
    LocalDateTime veryFarEnd = LocalDateTime.parse("9999-12-31T23:59");
    
    @Test
    void testReserveValid() {
        // Original: assertNotNull(facade.reserveEquipment(1,1))
        assertTrue(facade.reserveEquipment(futureStart, futureEnd, testUser, testEquipment, new CreditPayment()));
    }

    @Test
    void testReserveInvalidEquipment() {
        // Original: assertNull(facade.reserveEquipment(1,-1))  
        Equipment disabled = new Equipment(2, "Broken", "desc", "LabB", EquipmentStatus.DISABLED);
        eDb.addEquipment(disabled);
        assertFalse(facade.reserveEquipment(futureStart, futureEnd, testUser, disabled, new CreditPayment()));
    }

    @Test
    void testReserveInvalidUser() {
        // Original: assertNull(facade.reserveEquipment(-1,1))
        // reserveEquipment does not validate the user object itself it uses u.getId() and u.getHourlyRate().
        Equipment maintenance = new Equipment(3, "InMaintenance", "desc", "LabC", EquipmentStatus.MAINTENANCE);
        eDb.addEquipment(maintenance);
        assertFalse(facade.reserveEquipment(futureStart, futureEnd, testUser, maintenance, new CreditPayment()));
    }

    @Test
    void testCancelReservation() {
        // Original: assertTrue(facade.cancelReservation(r.getId()))  cancelReservation takes Reservation, not int
        facade.reserveEquipment(futureStart, futureEnd, testUser, testEquipment, new CreditPayment());
        Reservation r = rDb.getReservation(1);
        assertNotNull(r);
        assertTrue(facade.cancelReservation(r));
        assertEquals(ReservationStatus.CANCELLED, r.getStatus());
    }

    @Test
    void testCancelInvalidReservation() {
        // Original: assertFalse(facade.cancelReservation(-1))  cancelReservation takes Reservation not int
        LocalDateTime pastStart = LocalDateTime.parse("2020-01-01T10:00");
        LocalDateTime pastEnd   = LocalDateTime.parse("2020-01-01T12:00");
        Reservation pastR = new Reservation(99, pastStart, pastEnd, testUser.getId(), testEquipment.getId());
        pastR.setStatus(ReservationStatus.ACTIVE);
        rDb.addReservation(pastR);
        assertFalse(facade.cancelReservation(pastR));
    }

    @Test
    void testMultipleReservations() {
        LocalDateTime start2 = LocalDateTime.parse("2099-02-01T10:00");
        LocalDateTime end2   = LocalDateTime.parse("2099-02-01T12:00");

        Equipment e2 = new Equipment(2, "Scope2", "desc", "LabB", EquipmentStatus.ENABLED);
        eDb.addEquipment(e2);

        assertTrue(facade.reserveEquipment(futureStart, futureEnd, testUser, testEquipment, new CreditPayment()));
        assertTrue(facade.reserveEquipment(start2, end2, testUser, e2, new CreditPayment()));
    }

    @Test
    void testCancelTwice() {
        facade.reserveEquipment(futureStart, futureEnd, testUser, testEquipment, new CreditPayment());
        Reservation r = rDb.getReservation(1);
        facade.cancelReservation(r);
        facade.cancelReservation(r);
        assertEquals(ReservationStatus.CANCELLED, r.getStatus());
    }

    @Test
    void testReserveNullCase() {
        // Original: assertNull(facade.reserveEquipment(0,0))
        assertThrows(NullPointerException.class, () ->
            facade.reserveEquipment(futureStart, futureEnd, testUser, null, new CreditPayment()));
    }

    @Test
    void testReserveEdgeCase() {
        // Original: assertNotNull(facade.reserveEquipment(Integer.MAX_VALUE, 1))
        int hoursBetween=(int)ChronoUnit.HOURS.between(futureStart, veryFarEnd);
        assertTrue(facade.reserveEquipment(futureStart, veryFarEnd, testUser, testEquipment, new CreditPayment()));
        assertEquals(testUser.getHourlyRate()*(hoursBetween+1), rDb.getReservation(1).getTotalOwed());
    }

    @Test
    void testReservationNotNullId() {
        // Original: assertTrue(r.getId() > 0)
        facade.reserveEquipment(futureStart, futureEnd, testUser, testEquipment, new CreditPayment());
        Reservation r = rDb.getReservation(1);
        assertNotNull(r);
        assertTrue(r.getId() > 0);
    }
}