package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.parallel.Execution;

import model.userhierarchy.*;
import model.enums.*;
import model.exceptions.EmailNotUniqueException;
import model.exceptions.WeakPasswordException;
import model.database.*;
import model.systemFacades.*;
import org.junit.jupiter.api.parallel.ExecutionMode;

//this test class reads empty test files
//original files are the ones before the modifications are made by program
//the original files are copied into the test files before they are read from
@Execution(ExecutionMode.SAME_THREAD)
class TestWithEmptyFiles {
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
        File.separator + "Test_Empty_Files";
	
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
	void testLoginAndRegistration() {
		List<User> usersList=uDb.getUsersAsList();
		assertEquals(0, usersList.size());
		//add in the first user with strong password
		String u1Email="an email";
		String u1Username="username";
		String u1Password="Str0ng password!";
		int u1VerficationNum=12345;
		try {
			registrationAndLoginFacade.registerUser(u1Email, u1Username, u1Password, 
				u1VerficationNum, UserType.STUDENT);
		}
		catch(EmailNotUniqueException e) {
			fail();
		}
		catch(WeakPasswordException e) {
			fail();
		}
		usersList=uDb.getUsersAsList();
		assertEquals(1, usersList.size());
		User u1=registrationAndLoginFacade.loginUser(u1Email, u1Password);
		assertNotNull(u1);
		assertEquals(usersList.get(0), u1);
		assertEquals(u1Email, u1.getEmail());
		assertEquals(u1Password, u1.getPassword());
		assertEquals(1, u1.getId());
		
		//second user
		//test trying to register user with unique email and weak password
		String u2Email="u2Email";
		String u2Username="username";
		String u2Password="weak password9";
		int u2VerficationNum=12345;
		try {
			registrationAndLoginFacade.registerUser(u2Email, u2Username, u2Password, 
				u2VerficationNum, UserType.FACULTY);
			fail();
		}
		catch(EmailNotUniqueException e) {
			fail();
		}
		catch(WeakPasswordException e) {
			
		}
		usersList=uDb.getUsersAsList();
		assertEquals(1, usersList.size());
		
		//third user
		//test trying to register user with duplicate email and strong password
		String u3Email="an email";
		String u3Username="username";
		String u3Password="Strong%password9";
		int u3VerficationNum=12345;
		try {
			registrationAndLoginFacade.registerUser(u3Email, u3Username, u3Password, 
				u3VerficationNum, UserType.GUEST);
			fail();
		}
		catch(EmailNotUniqueException e) {
			
		}
		catch(WeakPasswordException e) {
			fail();
		}
		
		usersList=uDb.getUsersAsList();
		assertEquals(1, usersList.size());
		
		//try to login user thats not registered
		User loginUser=registrationAndLoginFacade.loginUser("random email", u3Password);
		assertNull(loginUser);
		
		//add one more valid user
		String u4Email="u4Email";
		String u4Username="username";
		String u4Password="StronkPass#2345";
		int u4VerficationNum=12345;
		try {
			registrationAndLoginFacade.registerUser(u4Email, u4Username, u4Password, 
				u4VerficationNum, UserType.GUEST);
		}
		catch(EmailNotUniqueException e) {
			fail();
		}
		catch(WeakPasswordException e) {
			fail();
		}
		
		//try to login to that user
		loginUser=registrationAndLoginFacade.loginUser(u4Email, u4Password);
		assertEquals(loginUser.getEmail(), u4Email);
		assertEquals(loginUser.getPassword(), u4Password);
		assertEquals(loginUser.getUsername(), u4Username);
		assertEquals(loginUser.getVerificationNum(), u4VerficationNum);
		assertTrue(loginUser instanceof Guest);
		
		//login to the first user added
		loginUser=registrationAndLoginFacade.loginUser(u1Email, u1Password);
		assertEquals(loginUser.getEmail(), u1Email);
		assertEquals(loginUser.getPassword(), u1Password);
		assertEquals(loginUser.getUsername(), u1Username);
		assertEquals(loginUser.getVerificationNum(), u1VerficationNum);
		assertTrue(loginUser instanceof Student);
		updateTables();
	}
	

}
