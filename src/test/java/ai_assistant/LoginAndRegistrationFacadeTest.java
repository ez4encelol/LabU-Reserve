package ai_assistant;

import org.junit.Ignore;
import org.junit.jupiter.api.*;

import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.UserType;
import model.IdGeneration;
import model.systemFacades.EquipmentManagementFacade;
import model.systemFacades.EquipmentReservationFacade;
import model.systemFacades.LoginAndRegistrationFacade;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class LoginAndRegistrationFacadeTest {

    LoginAndRegistrationFacade facade;

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
        File.separator + "Ai_Tests_Empty_Files";
	
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
		facade=new LoginAndRegistrationFacade();
		
		idGenerator=IdGeneration.getInstance();
	}
	
	@AfterEach
	void tearDown() {
	    IdGeneration.resetToNull();
	    EquipmentTable.resetToNull();
	    ReservationTable.resetToNull();
	    DatabaseService.resetToNull();
	    UserTable.resetToNull();
	}
	
	
    @Test
    void testRegisterValidUser() {
        assertNotNull(facade.createUser("john@gmail.com", "john", "Password1!", 123, UserType.STUDENT));
    }

    @Disabled
    @Test
    void testRegisterDuplicateUser() {
        facade.createUser("john@gmail.com", "john", "Password1!", 123, UserType.STUDENT);
        assertThrows(Exception.class, () ->
            facade.createUser("john@gmail.com", "john", "Password1!", 123, UserType.STUDENT));
    }

    @Disabled
    @Test
    void testLoginSuccess() {
        facade.createUser("john@gmail.com", "john", "Password1!", 123, UserType.STUDENT);
        assertNotNull(facade.loginUser("john@gmail.com", "Password1!"));
    }

    @Test
    void testLoginWrongPassword() {
        facade.createUser("john@gmail.com", "john", "Password1!", 123, UserType.STUDENT);
        assertNull(facade.loginUser("john@gmail.com", "wrong"));
    }

    @Test
    void testLoginNonExistingUser() {
        assertNull(facade.loginUser("no@email.com", "p"));
    }

    @Test
    void testRegisterNullUsername() {
        assertDoesNotThrow(() -> facade.createUser("john@gmail.com", null, "Password1!", 123, UserType.STUDENT));
    }

    @Test
    void testRegisterEmptyUsername() {
        assertDoesNotThrow(() -> facade.createUser("john@gmail.com", "", "Password1!", 123, UserType.STUDENT));
    }

    @Test
    void testRegisterNullPassword() {
        assertThrows(Exception.class, () ->
            facade.createUser("john@gmail.com", "john", null, 123, UserType.STUDENT));
    }

    @Test
    void testLoginNullInput() {
        assertThrows(Exception.class, () ->
            facade.createUser(null, null, null, -1, null));
    }

    @Disabled
    @Test
    void testMultipleUsers() {
        facade.createUser("john@gmail.com", "john", "Password1!", 123, UserType.STUDENT);
        facade.createUser("smith@gmail.com", "smith", "Password1!", 234, UserType.STUDENT);
        assertNotNull(facade.loginUser("john@gmail.com", "Password1!"));
        assertNotNull(facade.loginUser("smith@gmail.com", "Password1!"));
    }
}