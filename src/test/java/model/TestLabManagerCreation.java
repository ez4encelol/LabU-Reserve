package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.UserType;
import model.exceptions.EmailNotUniqueException;
import model.exceptions.WeakPasswordException;
import model.systemFacades.EquipmentManagementFacade;
import model.systemFacades.EquipmentReservationFacade;
import model.systemFacades.LoginAndRegistrationFacade;
import model.userhierarchy.HeadLabCoordinator;
import model.userhierarchy.User;

//tests the lab manager creation, with the LoginAndRegistrationFacade and other classes
public class TestLabManagerCreation {
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
        File.separator + "TestLabManagerCreation";
	
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
	    originalUserPath=folderPath + File.separator + "Original_files_" + testName + File.separator + "User_original.csv";
		originalReservationPath=folderPath + File.separator + "Original_files_" + testName + File.separator + "Reservation_original.csv";
		originalEquipmentPath=folderPath + File.separator + "Original_files_" + testName + File.separator + "Equipment_original.csv";
		
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
	
	//test out the login and registration functionality of the lab managers and head lab coordinators
	@Test
	void testLabManagerCreation() {
		//login to lab manager
		String l1Email="labmanager1@gmail.com";
		String l1Password="management1!";
		User l1=registrationAndLoginFacade.loginLabManager(l1Email, l1Password);
		assertNotNull(l1);
		assertEquals(l1.getEmail(), l1Email);
		assertEquals(l1.getPassword(), l1Password);
		assertEquals(UserType.LABMANAGER, l1.getUserType());
		
		//login to head lab coordinator
		String headEmail= "headlabcoordinator@gmail.com";
		String headPassword= "abcd1!";
		User head= registrationAndLoginFacade.loginHeadLabCoordinator(headEmail, headPassword);
		assertNotNull(head);
		assertEquals(head.getEmail(), headEmail);
		assertEquals(head.getPassword(), headPassword);
		assertEquals(UserType.HEADLABCOORDINATOR, head.getUserType());
		
		//try to login to a valid account, that is not a head lab coordinator
		User invalidHead=registrationAndLoginFacade.loginHeadLabCoordinator(l1Email, l1Password);
		assertNull(invalidHead);
		
		//try to create a new lab manager, this one will have id 8 after creation
		String newManager8Email = "manager8@gmail.com";
		String newManager8Password = "541!asdSa";
		String newManager8Username = "manager8";
		int newManager8VerificationNum = 676767;
		try {
			registrationAndLoginFacade.registerLabManager(newManager8Email, newManager8Username, newManager8Password, 
					newManager8VerificationNum);
		} catch(EmailNotUniqueException e) {
			fail();
		} catch(WeakPasswordException e) {
			fail();
		}
		
		User newManager8=uDb.getUser(8);
		assertEquals(newManager8Email, newManager8.getEmail());
		assertEquals(newManager8Password, newManager8.getPassword());
		assertEquals(newManager8VerificationNum, newManager8.getVerificationNum());
		assertEquals(UserType.LABMANAGER, newManager8.getUserType());
		
		updateTables();
	}
	
	//test logging into account thats not a lab manager by using the loginLabManager method
	@Test
	void testLabManagerCreation2(){
		String u1Email = "jsmith@university.ca";
		String u1Password = "pass123";
		User u1 = registrationAndLoginFacade.loginUser(u1Email, u1Password);
		assertEquals(u1Email, u1.getEmail());
		assertEquals(u1Password, u1.getPassword());
		assertNull(registrationAndLoginFacade.loginLabManager(u1Email, u1Password));
	}
}
