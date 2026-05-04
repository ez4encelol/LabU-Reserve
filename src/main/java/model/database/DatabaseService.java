package model.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Equipment;
import model.Reservation;
import model.userhierarchy.User;

//service to make complex database queries, like getting the reservations made on an equipment for example
public class DatabaseService {
	private static DatabaseService instance;
	
	private EquipmentTable eDb;
	private UserTable uDb;
	private ReservationTable rDb;
	
	private DatabaseService() {
		eDb=EquipmentTable.getInstance();
		uDb=UserTable.getInstance();
		rDb=ReservationTable.getInstance();
	}
	
	public static DatabaseService getInstance() {
		if(instance==null) {
			instance=new DatabaseService();
		}
		return instance;
	}
	
	//finds all the reservations associated with this equipment, then returns a list of those reservations
	public List<Reservation> getEquipmentReservations(Equipment equipment){
		List<Reservation> reservations=new ArrayList<>();
		rDb=ReservationTable.getInstance();
		List<Reservation> allReservations = rDb.getReservationsAsList();
		for(Reservation r : allReservations) {
			if(r.getEquipmentId()==equipment.getId()) {
				reservations.add(r);
			}
		}
		return reservations;
	}
	
	//returns null if no user found
	public User findUser(String email) {
		List<User> users=uDb.getUsersAsList();
		for(User u : users) {
			if(u.getEmail().equals(email)) {
				return u;
			}
		}
		return null; //if no user found, return null
	}
	
	//returns all of the reservations from a user
	public List<Reservation> getUserReservations(User u){
		List<Reservation> reservations=rDb.getReservationsAsList();
		List<Reservation> userReservations=new ArrayList<>();
		for(Reservation r: reservations) {
			if(r.getUserId()==u.getId()) {
				userReservations.add(r);
			}
		}
		return userReservations;
		
	}
	
	//update all of the tables right before closing the program
	public void updateAllTables() {
		eDb.update();
		uDb.update();
		rDb.update();
	}
	
	//removes all the users in the User database, mainly used for testing purposes, to get a fresh user database
	public void removeAllUsers() {
		uDb.setUsers(new HashMap<>());
	}
	
	//removes all the Equipment in the Equipment database, mainly used for testing purposes, to get a fresh equipment database
	public void removeAllEquipments() {
		eDb.setEquipment(new HashMap<>());
	}
	
	//removes all the Reservations in the Reservation database, mainly used for testing purposes, to get a fresh reservation database
	public void removeAllReservations() {
    rDb.setReservations(new HashMap<>());
	}
	
	//reset and set the instance to null for resetting for testing purposes
	public static void resetToNull() {
		instance=null;
	}
		
}
