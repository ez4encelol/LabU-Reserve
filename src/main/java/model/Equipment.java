package model;

import model.enums.EquipmentStatus;

public class Equipment implements SensorObserver{
	private int id;
	private String name;
	private String description;
	private String labLocation;
	private EquipmentStatus status;
	private Sensor sensor; //every equipment has a sensor
	
  //Complete constructor for object creation from database
	public Equipment(int id, String name, String description, String labLocation, EquipmentStatus status) {
		this.id=id;
		this.name=name;
		this.description=description;
		this.labLocation=labLocation;
		this.status=status;
		this.sensor=new Sensor(this);
	}

  //Another constructor that just makes the default status to be enabled
  public Equipment(int id, String name, String description, String labLocation) {
		this(id, name, description, labLocation, EquipmentStatus.ENABLED);
	}
	
	public int getId() {
		return this.id;
	}

  public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLabLocation() {
		return this.labLocation;
	}

	public void setLabLocation(String labLocation) {
		this.labLocation = labLocation;
	}

	public EquipmentStatus getStatus() {
		return this.status;
	}

	public void setStatus(EquipmentStatus status) {
		this.status=status;
	}
	
	public Sensor getSensor() {
		return this.sensor;
	}
	
	//updates equipment status if the user arrives, or departs
	@Override
	public void update(Sensor sensor, String updateMessage) {
		
	}
	
	public String toString() {
		return String.format("%s %s %s %s %s", String.valueOf(id), 
				name, 
				description, 
				labLocation, 
				status.toString());
	}
}