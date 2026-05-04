package model.database;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import model.Equipment;
import model.enums.EquipmentStatus;

public class EquipmentTable extends Table{

	private Map<Integer, Equipment> equipment;

	private String dbPath = getDBPath();

	private static EquipmentTable dbInstance;

	//singleton of the user database
	private EquipmentTable() {
		equipment = new HashMap<>();
		try {
			loadCSV();
		} catch (Exception e) {
			System.err.println("Error reading Equipment CSV");
		}
	}
	
	//mainly used for testing, for utilizing test csv files 
  	public void setCustomDBPath(String path) {
  		dbPath=path;
  	}
	
	public void loadFromDB() {
		try {
			loadCSV();
		} catch (Exception e) {
			System.err.println("Error reading Equipment CSV");
		}
	}


	public static EquipmentTable getInstance() {
		//use lazy initialization of the database
		if(dbInstance==null) {
			dbInstance=new EquipmentTable();
		}
		return dbInstance;
	}

	public void addEquipment(Equipment e) {
		equipment.put(e.getId(), e);
	}

	public Equipment getEquipment(int id) {
		return equipment.get(id);
	}

	public List<Equipment> getEquipmentAsList(){
		return new ArrayList<>(equipment.values());
	}

	@Override
	public final void loadCSV(){
		equipment = new HashMap<>();
		try {
			CsvReader reader = new CsvReader(dbPath); 
			reader.readHeaders();
			int noRecords=0;
			while(reader.readRecord()){ 			
				int key = Integer.parseInt(reader.get("id"));
				String name = reader.get("name");
				String description = reader.get("description");
				String labLocation = reader.get("labLocation");
				EquipmentStatus status = convertToEnum(reader.get("status"));

				Equipment e = new Equipment(key, name, description, labLocation, status);

				equipment.put(key, e);
				noRecords++;
			}
			idGen.setEquipmentIdCount(noRecords+1);
		} catch ( IOException | NumberFormatException e) {
			System.err.println("Error reading Equipment CSV");
		}

	}

	@Override
	public final void update(){
		try {		
			CsvWriter csvOutput = new CsvWriter(new FileWriter(dbPath, false), ',');

			csvOutput.write("id");
			csvOutput.write("name");
			csvOutput.write("description");
			csvOutput.write("labLocation");
			csvOutput.write("status");
			csvOutput.endRecord();

			for(Equipment e: getEquipmentAsList()){
				csvOutput.write(String.valueOf(e.getId()));
				csvOutput.write(e.getName());
				csvOutput.write(e.getDescription());
				csvOutput.write(e.getLabLocation());
				csvOutput.write(convertToString(e.getStatus()));
				csvOutput.endRecord();
			}
			csvOutput.close();

		}catch (IOException e) {
			System.err.println("Error writing to Equipment CSV");
		}
	}
	//mainly for testing purposes
	public void setEquipment(Map<Integer, Equipment> equipment) {
		this.equipment=equipment;
	}

	//reset and set the instance to null for resetting for testing purposes
	public static void resetToNull() {
		dbInstance=null;
	}

	//Helper methods for Enums
	private static String convertToString(EquipmentStatus status){
		return status.toString();
	}

	private static EquipmentStatus convertToEnum(String status){
		return EquipmentStatus.valueOf(status);
	}

	@Override
	public final String getDBPath(){
		return getDBFolder() + "Equipment.csv";
	}
}
