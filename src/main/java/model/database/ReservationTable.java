package model.database;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import model.IdGeneration;
import model.Reservation;
import model.enums.ReservationStatus;

public class ReservationTable extends Table{
	private Map<Integer, Reservation> reservations;

	private String dbPath = getDBPath();

	private static ReservationTable dbInstance;

	//singleton of the user database
	private ReservationTable() {
		reservations=new HashMap<>();
		try {
			loadCSV();
		} catch (Exception e) {
			System.err.println("Error reading Reservation CSV");
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

	public static ReservationTable getInstance() {
		//use lazy initialization of the database
		if(dbInstance==null) {
			dbInstance=new ReservationTable();
		}
		return dbInstance;
	}

	public void addReservation(Reservation r) {
		reservations.put(r.getId(), r);
	}

	public Reservation getReservation(int id) {
		return reservations.get(id);
	}

	public List<Reservation> getReservationsAsList(){
		return new ArrayList<>(reservations.values());
	}
	@Override
	public final void loadCSV(){
		reservations = new HashMap<>();
		try {
			CsvReader reader = new CsvReader(dbPath); 
			reader.readHeaders();
			int noRecords=0;
			while(reader.readRecord()){ 			
				int key = Integer.parseInt(reader.get("id"));
				LocalDateTime startTime = LocalDateTime.parse(reader.get("startTime"));
				LocalDateTime endTime = LocalDateTime.parse(reader.get("endTime"));

				int equipmentId = Integer.parseInt(reader.get("equipmentId"));
				int userId = Integer.parseInt(reader.get("userId"));

				ReservationStatus status = convertToEnum(reader.get("status"));
				int totalOwed = Integer.parseInt(reader.get("totalOwed"));
				int depositAmount = Integer.parseInt(reader.get("depositAmount"));

				Reservation r = new Reservation(key, startTime, endTime, userId, equipmentId);

				r.setStatus(status);
				r.setTotalOwed(totalOwed);
				r.setDepositAmount(depositAmount);

				reservations.put(key, r);
				noRecords++;
			}
			idGen.setReservationIdCount(noRecords+1);
		} catch ( IOException | NumberFormatException e) {
			System.err.println("Error reading Reservation CSV");
		}
	}

	@Override
	public final void update(){
		try {		
			CsvWriter csvOutput = new CsvWriter(new FileWriter(dbPath, false), ',');
			csvOutput.write("id");
			csvOutput.write("startTime");
			csvOutput.write("endTime");
			csvOutput.write("equipmentId");
			csvOutput.write("userId");
			csvOutput.write("status");
			csvOutput.write("totalOwed");
			csvOutput.write("depositAmount");
			csvOutput.endRecord();

			for (Reservation r : getReservationsAsList()) {
				csvOutput.write(String.valueOf(r.getId()));
				csvOutput.write(r.getStartTime().toString());
				csvOutput.write(r.getEndTime().toString());
				csvOutput.write(String.valueOf(r.getEquipmentId()));
				csvOutput.write(String.valueOf(r.getUserId()));
				csvOutput.write(convertToString(r.getStatus()));
				csvOutput.write(String.valueOf(r.getTotalOwed()));
				csvOutput.write(String.valueOf(r.getDepositAmount()));
				
				csvOutput.endRecord();
			}
			csvOutput.close();
		}catch (IOException e) {
			System.err.println("Error writing to Reservation CSV");
		}
	}
	//mainly for testing purposes
	public void setReservations(Map<Integer, Reservation> reservations) {
		this.reservations=reservations;
	}

	//reset and set the instance to null for resetting for testing purposes
	public static void resetToNull() {
		dbInstance=null;
	}

	//Helper methods for Enums
	private static String convertToString(ReservationStatus status){
		return status.toString();
	}

	private static ReservationStatus convertToEnum(String status){
		return ReservationStatus.valueOf(status);
	}
	
	//got rid of the NOSHOWS AS it was somewhat clunky and confusing
	//updates the database to check if any no shows occured, if so then update the status
//	public void updateNoShows() {
//		List<Reservation> allReservations = getReservationsAsList();
//		LocalDateTime currentTime=LocalDateTime.now();
//		for(Reservation r : allReservations) {
//			LocalDateTime startTime=r.getStartTime();
//			if (r.getStatus()==ReservationStatus.ACTIVE && currentTime.isAfter(startTime.plusMinutes(20))) {
//				r.setStatus(ReservationStatus.NOSHOW);
//			}
//		}
//	}

	@Override
	public final String getDBPath(){
		return getDBFolder() + "Reservation.csv";
	}
}
