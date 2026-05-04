package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
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

class TestLoginAndRegistrationFacade {

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
        File.separator + "TestLoginAndRegistrationFacade";
	
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
		originalUserPath=folderPath + File.separator + "Original_files"+ File.separator + "User_original.csv";
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
	//tests guest registration with various passwords
	void testGuestRegistrationPasswordStrength() {
		String emailBase = "guest_test_"; //avoids EmailNotUniqueException
    	String username = "Guest User";
    	int startId = 3000;
		UserType gtype = UserType.GUEST;

		//case 1: no upper case (weak)
		try {
			registrationAndLoginFacade.registerUser(emailBase + "1@test.com", username, "weakpass123!", startId++, UserType.GUEST);
			fail("case 1: should throw WeakPasswordException (no uppercase)");
		} catch (WeakPasswordException e) {
			//correct behavior
		} catch (EmailNotUniqueException e) {
			fail("case 1: shouldn't've thrown");
		}

		//case 2: no lower case (weak)
		try {
			registrationAndLoginFacade.registerUser(emailBase + "2@test.com", username, "WEAKPASS123!", startId++, UserType.GUEST);
			fail("case 1: should throw WeakPasswordException (no lowercase)");
		} catch (WeakPasswordException e) {
			//correct behavior
		} catch (EmailNotUniqueException e) {
			fail("case 2: shouldn't've thrown");
		}

		//case 3: no digit (weak)
		try {
			registrationAndLoginFacade.registerUser(emailBase + "3@test.com", username, "WeakPass!", startId++, UserType.GUEST);
			fail("case 3: should throw WeakPasswordException (no digits)");
		} catch (WeakPasswordException e) {
			//correct behavior
		} catch (EmailNotUniqueException e) {
			fail("case 2: shouldn't've thrown");
		}

		//case 4: no special char (Weak)
		try {
			registrationAndLoginFacade.registerUser(emailBase + "4@test.com", username, "WeakPass123", startId++, UserType.GUEST);
			fail("case 4: should throw WeakPasswordException (no special char)");
		} catch (WeakPasswordException e) {
			//correct behavior
		} catch (EmailNotUniqueException e) {
			fail("case 2: shouldn't've thrown");
		}

		//case 5: all g (Strong)
		try {
			//need to reference more than once
			String gemail = emailBase + "5@test.com";
        	String gpass = "Str0ngPass!";
        	String guser = "Strong User";
        	int vNum = startId++;

			registrationAndLoginFacade.registerUser(gemail, guser, gpass, vNum, UserType.GUEST);
			//verify user saved in database
			User user = sDb.findUser(gemail);
			assertNotNull(user, "case 5: user should've been created and found");

			//verify user attributes
			assertEquals(gemail,user.getEmail());
			assertEquals(guser,user.getUsername());
			assertEquals(gpass,user.getPassword());
			assertEquals(UserType.GUEST, user.getUserType());

		} catch (WeakPasswordException e) {
			fail("case 5: shouldn't throw WeakPasswordException (pass is strong)");
		} catch (EmailNotUniqueException e) {
			fail("case 2: shouldn't've thrown");
		}
    updateTables();

	}


	@Test
	//tests account registration with repeat emails
	void testEmailUniquenessSimplified() {
		String duplicateEmail = "test@example.com";
		String nonDupeEmail = "test2@example.com";
		String password = "Str0ngPass123!";
		
		//first time - unique email
		try {
			registrationAndLoginFacade.registerUser(duplicateEmail, "first User", password, 101, UserType.STUDENT);
			
			//verify user saved in database
			assertNotNull(sDb.findUser(duplicateEmail));
		} catch (Exception e) {
			fail();
		}

		//second time - non unique email 
		try {
			//different name/ID, but the same email
			registrationAndLoginFacade.registerUser(duplicateEmail, "second User", password, 102, UserType.GUEST);
			
			//exception wasn't thrown
			fail();
		} catch (EmailNotUniqueException e) {
			//correct behaviour 
		} catch (WeakPasswordException e) {
			fail();
		}

		//third time - unique email =
		try{
			//different name/ID/email
			registrationAndLoginFacade.registerUser(nonDupeEmail, "third User", password, 103, UserType.RESEARCHER);

			//verify user saved in database
			assertNotNull(sDb.findUser(nonDupeEmail));
		} catch (Exception e) {
			fail();
		}
    updateTables();
	}


	//tests for expected hourly rate (is the userfactory factorying)
	//student test
	@Test
	void testStudentHourlyRate() {
		String email = "rate.student@test.ca";
		int expectedRate = 10;

		try{
			registrationAndLoginFacade.registerUser(email, "Student User", "Pass123!", 67, UserType.STUDENT);
		} catch (Exception e){
			fail();
		}
		
		
		User user = sDb.findUser(email);
		assertNotNull(user);
		assertEquals(expectedRate, user.getHourlyRate(), "Student hourly rate should be 10");
    updateTables();
	}
	//faculty test
	@Test
	void testFacultyHourlyRate() {
		String email = "rate.faculty@test.ca";
		int expectedRate = 15;

		try{
		registrationAndLoginFacade.registerUser(email, "Faculty User", "Pass123!", 6767, UserType.FACULTY);
		} catch (Exception e){
			fail();
		}

		User user = sDb.findUser(email);
		assertNotNull(user);
		assertEquals(expectedRate, user.getHourlyRate(), "Faculty hourly rate should be 15");
    updateTables();
	}
	//researcher test
	@Test
	void testResearcherHourlyRate() {
		String email = "rate.researcher@test.ca";
		int expectedRate = 20;

		try{
		registrationAndLoginFacade.registerUser(email, "Researcher User", "Pass123!", 69, UserType.RESEARCHER);
		} catch (Exception e){
			fail();
		}

		User user = sDb.findUser(email);
		assertNotNull(user);
		assertEquals(expectedRate, user.getHourlyRate(), "Researcher hourly rate should be 20");
    updateTables();
	}
	//guest test
	@Test
	void testGuestHourlyRate() {
		String email = "rate.guest@test.ca";
		// We expect 30 as defined in Guest.java
		int expectedRate = 30;

		try{
		registrationAndLoginFacade.registerUser(email, "Guest User", "Pass123!", 6969, UserType.GUEST);
		} catch (Exception e){
			fail();
		}

		User user = sDb.findUser(email);
		assertNotNull(user);
		assertEquals(expectedRate, user.getHourlyRate(), "Guest hourly rate should be 30");
    updateTables();
	}
	//labmanager test
	@Test
	void testLabManagerHourlyRate() {
		String email = "rate.manager@test.ca";
		//lab managers return 0 because they don't reserve equipment
		int expectedRate = 0;

		try{
		registrationAndLoginFacade.registerLabManager(email, "Manager User", "Pass123!", 6769);
		} catch (Exception e){
			fail();
		}

		User user = sDb.findUser(email);
		assertNotNull(user);
		assertEquals(expectedRate, user.getHourlyRate(), "Lab Manager hourly rate should be 0");
    updateTables();
	}

	//tests for correct university affiliation (on registration)
	//student (affiliated)
	@Test
	void testStudentAffiliation() {
		String email = "student.aff@test.ca";
		try {
			registrationAndLoginFacade.registerUser(email, "Student", "Pass123!", 8001, UserType.STUDENT);
		} catch (Exception e) {
			fail();
		}
		User user = sDb.findUser(email);
		assertNotNull(user);
		assertTrue(user.getUniversityAfilliated());
    updateTables();
	}
	// faculty (affiliated)
	@Test
	void testFacultyAffiliation() {
		String email = "faculty.aff@test.ca";
		try {
			registrationAndLoginFacade.registerUser(email, "Faculty", "Pass123!", 8002, UserType.FACULTY);
		} catch (Exception e) {
			fail();
		}
		User user = sDb.findUser(email);
		assertNotNull(user);
		assertTrue(user.getUniversityAfilliated());
    updateTables();
	}
	//researcher (affiliated)
	@Test
	void testResearcherAffiliation() {
		String email = "researcher.aff@test.ca";
		try {
			registrationAndLoginFacade.registerUser(email, "Researcher", "Pass123!", 8003, UserType.RESEARCHER);
		} catch (Exception e) {
			fail();
		}
		User user = sDb.findUser(email);
		assertNotNull(user);
		assertTrue(user.getUniversityAfilliated());
    updateTables();
	}
	//labmanager (affiliated)
	@Test
	void testLabManagerAffiliation() {
		String email = "manager.aff@test.ca";
		try {
			registrationAndLoginFacade.registerLabManager(email, "Manager", "Pass123!", 8004);
		} catch (Exception e) {
			fail();
		}
		User user = sDb.findUser(email);
		assertNotNull(user);
		assertTrue(user.getUniversityAfilliated());
    updateTables();
	}
	//guest (not)
	@Test
	void testGuestAffiliation() {
		String email = "guest.aff@test.ca";
		try {
			registrationAndLoginFacade.registerUser(email, "Guest", "Pass123!", 8005, UserType.GUEST);
		} catch (Exception e) {
			fail();
		}
		User user = sDb.findUser(email);
		assertNotNull(user);
		assertFalse(user.getUniversityAfilliated());
    updateTables();
	}



	//test for id generation continuity - if u generate users in order (ex: 1 apart) that order should be preserved in their ids
	@Test
	void testIdSequence() {
		try{
		registrationAndLoginFacade.registerUser("u1@t.com", "U1", "P@ss1", 1, UserType.GUEST);
		} catch (Exception e){
			fail();
		} try{
		registrationAndLoginFacade.registerUser("u2@t.com", "U2", "P@ss2", 2, UserType.STUDENT);
		} catch (Exception e){
			fail();
		} try{
		registrationAndLoginFacade.registerUser("u3@t.com", "U3", "P@ss3", 3, UserType.LABMANAGER);
		} catch (Exception e){
			fail();
		}

		User first = sDb.findUser("u1@t.com");
    	User second = sDb.findUser("u2@t.com");
		User third = sDb.findUser("u3@t.com");

		//sequential continuity?
		assertEquals(first.getId() + 1, second.getId());
		assertEquals(second.getId() + 1, third.getId());
    updateTables();
	}


	//tests successful user login (is it?)
	@Test
	void testLoginSuccess() {
		String email = "login.succ@test.com";
		String pass = "Wpass1!";

		try{
			registrationAndLoginFacade.registerUser(email, "User", pass, 420, UserType.STUDENT);
		} catch (Exception e){
			fail();
		}

		User loggedIn = registrationAndLoginFacade.loginUser(email, pass);
		assertNotNull(loggedIn);
		assertEquals(email, loggedIn.getEmail());
    updateTables();
	}


	//tests inncorrect password login failure
	@Test
	void testLoginWrongPassword() {
		String email = "password.test@test.com";
		String correctPass = "CorrectPass1!";
		String wrongPass = "correctpass1!"; //"typo"
		
		try {
			registrationAndLoginFacade.registerUser(email, "User", correctPass, 1002, UserType.STUDENT);
		} catch (Exception e) {
			fail();
		}

		User loggedIn = registrationAndLoginFacade.loginUser(email, wrongPass);
		assertNull(loggedIn);
    updateTables();
	}

  @Test
  void testLoginLabManagerSuccess() {
      String email = "labmanager1@gmail.com";
      String pass = "management1!";

      User loggedIn = registrationAndLoginFacade.loginLabManager(email, pass);

      assertNotNull(loggedIn);
      assertEquals(email, loggedIn.getEmail());
      assertEquals(UserType.LABMANAGER, loggedIn.getUserType());
      updateTables();
  }

  @Test
  void testLoginHeadCoordinatorSuccess() {
      String email = "headlabcoordinator@gmail.com";
      String pass = "abcd1!";

      User loggedIn = registrationAndLoginFacade.loginHeadLabCoordinator(email, pass);

      assertNotNull(loggedIn);
      assertEquals(email, loggedIn.getEmail());
      assertEquals(UserType.HEADLABCOORDINATOR, loggedIn.getUserType());
      updateTables();
  }

  @Test
  void testLabManagerWrongRole() {
      String email = "guest@email.com";
      String pass = "guest111";

      User loggedIn = registrationAndLoginFacade.loginLabManager(email, pass);

      assertNull(loggedIn);
      updateTables();
  }

  @Test
  void testHeadCoordinatorWrongRole() {
      String email = "labmanager1@gmail.com";
      String pass = "management1!";

      User loggedIn = registrationAndLoginFacade.loginHeadLabCoordinator(email, pass);

      assertNull(loggedIn);
      updateTables();
  }

  @Test
  void testLoginUserDoesNotExist() {
      String email = "nouser@test.com";
      String pass = "nopass";

      User loggedIn = registrationAndLoginFacade.loginUser(email, pass);

      assertNull(loggedIn);
      updateTables();
  }
}
