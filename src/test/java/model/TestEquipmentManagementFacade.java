package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.EquipmentStatus;
import model.systemFacades.EquipmentManagementFacade;
import model.systemFacades.EquipmentReservationFacade;
import model.systemFacades.LoginAndRegistrationFacade;
import model.userhierarchy.HeadLabCoordinator;

public class TestEquipmentManagementFacade {
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
        File.separator + "TestEquipmentManagement";
	
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
	void testAddingEquipmentCorrectFields() {
		List<Equipment> allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(0, allEquipment.size());
		
    String e1Name="Oscilloscope";
		String e1Description = "Rigol DS1054Z Digital Oscilloscope 50 MHz";
		String e1LabLocation = "Lab03";
		
    equipmentManagement.addEquipment(e1Name, e1Description, e1LabLocation);

		Equipment newEquipment = eDb.getEquipment(1);
		
    assertEquals("Oscilloscope", newEquipment.getName());
		assertEquals("Rigol DS1054Z Digital Oscilloscope 50 MHz", newEquipment.getDescription());
		assertEquals("Lab03", newEquipment.getLabLocation());

		updateTables();
	}
	
  @Test
  void testIdenticalEquipmentParameterNewObjects() {
		List<Equipment> allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(0, allEquipment.size());
		
    String e1Name="Oscilloscope";
		String e1Description = "Rigol DS1054Z Digital Oscilloscope 50 MHz";
		String e1LabLocation = "Lab03";
		
    equipmentManagement.addEquipment(e1Name, e1Description, e1LabLocation);
    equipmentManagement.addEquipment(e1Name, e1Description, e1LabLocation);

		Equipment newEquipment1 = eDb.getEquipment(1);
    Equipment newEquipment2 = eDb.getEquipment(2);

    assertNotSame(newEquipment1, newEquipment2);

    assertEquals(newEquipment1.getName(), newEquipment2.getName());
		assertEquals(newEquipment1.getDescription(), newEquipment2.getDescription());
		assertEquals(newEquipment1.getLabLocation(), newEquipment2.getLabLocation());

		updateTables();
	}

  @Test
	void testAddingEquipmentIncrementSize() {
		List<Equipment> allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(0, allEquipment.size());
		
    equipmentManagement.addEquipment("TestName1", "TestDesc1", "TestLoc1");

    allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(1, allEquipment.size());

    equipmentManagement.addEquipment("TestName2", "TestDesc2", "TestLoc2");

		allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(2, allEquipment.size());

    updateTables();
	}

  @Test
	void testAddingManyEquipment() {
		List<Equipment> allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(0, allEquipment.size());
		
    for (int i = 1; i <= 50; i++){
      equipmentManagement.addEquipment("Name" + i, "Desc" + i, "Loc" + i);
    }

    allEquipment = equipmentManagement.getAllEquipment();
    assertEquals(50, allEquipment.size());

    for (int i = 1; i <= 50; i++){
      assertNotNull(eDb.getEquipment(i));
    }
    
		updateTables();
	}

  @Test
	void testEquipmentStartsEnabled() {
		List<Equipment> allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(0, allEquipment.size());

    String e1Name="Oscilloscope";
		String e1Description = "Rigol DS1054Z Digital Oscilloscope 50 MHz";
		String e1LabLocation = "Lab03";
		
    equipmentManagement.addEquipment(e1Name, e1Description, e1LabLocation);

		Equipment newEquipment = eDb.getEquipment(1);
		
    assertEquals(EquipmentStatus.ENABLED, newEquipment.getStatus());

		updateTables();
	}

  @Test
	void testEquipmentOverwritePreviousChange() {
		List<Equipment> allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(0, allEquipment.size());

    String e1Name="Oscilloscope";
		String e1Description = "Rigol DS1054Z Digital Oscilloscope 50 MHz";
		String e1LabLocation = "Lab03";
		
    equipmentManagement.addEquipment(e1Name, e1Description, e1LabLocation);

		Equipment newEquipment = eDb.getEquipment(1);
    equipmentManagement.disableEquipment(newEquipment);
    equipmentManagement.enableEquipment(newEquipment);

    assertEquals(EquipmentStatus.ENABLED, newEquipment.getStatus());

		updateTables();
	}

  @Test
	void testDisableEquipment() {
		List<Equipment> allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(0, allEquipment.size());

    String e1Name="Oscilloscope";
		String e1Description = "Rigol DS1054Z Digital Oscilloscope 50 MHz";
		String e1LabLocation = "Lab03";
		
    equipmentManagement.addEquipment(e1Name, e1Description, e1LabLocation);

		Equipment newEquipment = eDb.getEquipment(1);

    equipmentManagement.disableEquipment(newEquipment);

    assertEquals(EquipmentStatus.DISABLED, newEquipment.getStatus());

		updateTables();
	}
  
  @Test
	void testPutOnMaintenanceEquipment() {
		List<Equipment> allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(0, allEquipment.size());

    String e1Name="Oscilloscope";
		String e1Description = "Rigol DS1054Z Digital Oscilloscope 50 MHz";
		String e1LabLocation = "Lab03";
		
    equipmentManagement.addEquipment(e1Name, e1Description, e1LabLocation);

		Equipment newEquipment = eDb.getEquipment(1);

    equipmentManagement.markAsUnavailable(newEquipment);

    assertEquals(EquipmentStatus.MAINTENANCE, newEquipment.getStatus());

		updateTables();
	}

  @Test
  void testStateChangeOnIdenticalEquipment() {
		List<Equipment> allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(0, allEquipment.size());
		
    String e1Name="Oscilloscope";
		String e1Description = "Rigol DS1054Z Digital Oscilloscope 50 MHz";
		String e1LabLocation = "Lab03";
		
    equipmentManagement.addEquipment(e1Name, e1Description, e1LabLocation);
    equipmentManagement.addEquipment(e1Name, e1Description, e1LabLocation);

		Equipment newEquipment1 = eDb.getEquipment(1);
    Equipment newEquipment2 = eDb.getEquipment(2);

    equipmentManagement.disableEquipment(newEquipment1);
    assertNotEquals(newEquipment1.getStatus(), newEquipment2.getStatus());

		updateTables();
	}

  @Test
	void testAddingEquipmentEmpty() {
		List<Equipment> allEquipment = equipmentManagement.getAllEquipment();
		assertEquals(0, allEquipment.size());
		
    equipmentManagement.addEquipment("", "", "");

		Equipment newEquipment = eDb.getEquipment(1);
		
    assertEquals("", newEquipment.getName());
		assertEquals("", newEquipment.getDescription());
		assertEquals("", newEquipment.getLabLocation());

		updateTables();
	}
}
