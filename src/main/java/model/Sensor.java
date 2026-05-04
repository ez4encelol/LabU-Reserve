package model;

import java.util.ArrayList;
import java.util.List;

import model.database.DatabaseService;

//sensor object that tracks each equipment
//theres a sensor object for each equipment
public class Sensor {
	
	Equipment equipment; //reference to equipment to be able to get updated reservations
	List<SensorObserver> observers;
	
	public Sensor(Equipment equipment) {
		this.observers = new ArrayList<>();
		this.equipment = equipment;
	}
	
	public void attach(SensorObserver o) {
		observers.add(o);
	}
	
	public void detach(SensorObserver o) {
		observers.remove(o);
	}
	
	public void notifyObservers(String updateMessage) {
		for(SensorObserver o : observers) {
			o.update(this, updateMessage);
		}
	}
	
	public Equipment getEquipment() {
		return this.equipment;
	}
	
	//sensor detects an arrival
	public void arrive() {
		List<Reservation> reservations=DatabaseService.getInstance().getEquipmentReservations(equipment); //get updated list of reservations that correlate to equipment
		observers=new ArrayList<SensorObserver>(reservations);
		observers.add(equipment);
		notifyObservers("arrive"); //send message that User arrived, and equipment is in use
	}
	
	//sensor detects departure from equipment, equipment not in use anymore
	//only the equipment needs to be notified for updates
	public void departure() {
		notifyObservers("depart"); //send message that user departed, and equipment not in use anymore
	}
}