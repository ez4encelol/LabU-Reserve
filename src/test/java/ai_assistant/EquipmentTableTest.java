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

public class EquipmentTableTest {
	
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
    void testSingleton() {
        assertSame(EquipmentTable.getInstance(), EquipmentTable.getInstance());
    }

    @Test
    void testAddAndGet() {
        // Original: t.getEquipmentById(123)  correct method is getEquipment(int id)
        EquipmentTable t = EquipmentTable.getInstance();
        Equipment e = new Equipment(123, "A", "desc", "L");
        t.addEquipment(e);
        assertNotNull(t.getEquipment(123));
    }

    @Test
    void testGetInvalid() {
        // Original: t.getEquipmentById(-1)  correct method is getEquipment(int id)
        assertNull(EquipmentTable.getInstance().getEquipment(-1));
    }

    @Test
    void testUpdateNoCrash() {
        assertDoesNotThrow(() -> EquipmentTable.getInstance().update());
    }

    @Test
    void testMultipleAdds() {
        EquipmentTable t = EquipmentTable.getInstance();
        t.addEquipment(new Equipment(1, "A", "desc", "L"));
        t.addEquipment(new Equipment(2, "B", "desc", "L"));
        assertNotNull(t.getEquipment(2));
    }

    @Test
    void testOverwrite() {
        EquipmentTable t = EquipmentTable.getInstance();
        t.addEquipment(new Equipment(1, "A", "desc", "L"));
        t.addEquipment(new Equipment(1, "B", "desc", "L"));
        assertEquals("B", t.getEquipment(1).getName());
    }

    @Test
    void testNullAdd() {
        // Original: assertDoesNotThrow(() -> t.addEquipment(null))
        assertThrows(NullPointerException.class, () -> EquipmentTable.getInstance().addEquipment(null));
    }

    @Test
    void testSizeGrowth() {
        EquipmentTable t = EquipmentTable.getInstance();
        t.addEquipment(new Equipment(999, "A", "desc", "L"));
        assertNotNull(t.getEquipment(999));
    }

    @Test
    void testInstanceNotNull() {
        assertNotNull(EquipmentTable.getInstance());
    }

    @Test
    void testConsistency() {
        EquipmentTable t = EquipmentTable.getInstance();
        assertSame(t, EquipmentTable.getInstance());
    }
}