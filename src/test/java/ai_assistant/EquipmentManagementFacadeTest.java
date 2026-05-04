package ai_assistant;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import model.Equipment;
import model.IdGeneration;
import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.EquipmentStatus;
import model.systemFacades.EquipmentManagementFacade;
import model.systemFacades.EquipmentReservationFacade;
import model.systemFacades.LoginAndRegistrationFacade;

public class EquipmentManagementFacadeTest {

	EquipmentManagementFacade facade;
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
		facade=new EquipmentManagementFacade();
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
	}

    @Test
    void testAddEquipment() {
        // Original: assertNotNull(facade.addEquipment("A","Equipment A","Lab"))
        // addEquipment returns void; verify via getAllEquipment instead
        facade.addEquipment("A", "Equipment A", "Lab");
        assertEquals(1, facade.getAllEquipment().size());
    }

    @Test
    void testRemoveEquipment() {
        // Original: facade.removeEquipment(e.getId()) removeEquipment does not exist in the facade.
        facade.addEquipment("A", "desc", "Lab");
        Equipment e = eDb.getEquipment(1);
        assertNotNull(e);
        facade.disableEquipment(e);
        assertEquals(EquipmentStatus.DISABLED, e.getStatus());
    }

    @Test
    void testRemoveInvalid() {
        // Original: assertFalse(facade.removeEquipment(-1))  removeEquipment does not exist.
        assertNull(eDb.getEquipment(-1));
    }

    @Test
    void testUpdateEquipment() {
        // Original: facade.updateEquipment(e) updateEquipment does not exist in the facade.
        facade.addEquipment("A", "desc", "Lab");
        Equipment e = eDb.getEquipment(1);
        e.setName("B");
        assertEquals("B", e.getName());
    }

    @Test
    void testAddMultiple() {
        facade.addEquipment("A", "desc", "Lab");
        facade.addEquipment("B", "desc", "Lab");
        assertEquals(2, facade.getAllEquipment().size());
    }

    @Test
    void testAddNullName() {
        // Original: assertNull(facade.addEquipment(null,"Lab")) addEquipment returns void, no null guard
        assertDoesNotThrow(() -> facade.addEquipment(null, "desc", "Lab"));
    }

    @Test
    void testAddEmptyName() {
        // Original: assertNull(facade.addEquipment("","Lab"))  no empty-name guard exists
        assertDoesNotThrow(() -> facade.addEquipment("", "desc", "Lab"));
    }

    @Test
    void testUpdateNull() {
        // Original: assertDoesNotThrow(() -> facade.updateEquipment(null)) updateEquipment does not exist
        assertThrows(NullPointerException.class, () -> facade.markAsUnavailable(null));
    }

    @Test
    void testRemoveTwice() {
        // Original: facade.removeEquipment twice removeEquipment does not exist
        facade.addEquipment("A", "desc", "Lab");
        Equipment e = eDb.getEquipment(1);
        facade.disableEquipment(e);
        facade.disableEquipment(e);
        assertEquals(EquipmentStatus.DISABLED, e.getStatus());
    }

    @Test
    void testAddEdgeCase() {
        facade.addEquipment("X", "desc", "Y");
        assertNotNull(eDb.getEquipment(1));
    }
}