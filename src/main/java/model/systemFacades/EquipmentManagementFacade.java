package model.systemFacades;

import java.util.List;

import model.Equipment;
import model.IdGeneration;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.EquipmentStatus;

public class EquipmentManagementFacade {
	
	UserTable uDb;
	ReservationTable rDb;
	EquipmentTable eDb;
	
	
	public EquipmentManagementFacade() {
		uDb=UserTable.getInstance();
		rDb=ReservationTable.getInstance();
		eDb=EquipmentTable.getInstance();
	}
	
	public void addEquipment(String name, String description, String labLocation) {
		Equipment e=new Equipment(IdGeneration.getInstance().nextEquipmentId(), name, description, labLocation);
		eDb.addEquipment(e);
	}
	public void enableEquipment(Equipment equipment) {
		equipment.setStatus(EquipmentStatus.ENABLED);
	}
	public void disableEquipment(Equipment equipment) {
		equipment.setStatus(EquipmentStatus.DISABLED);
	}
	public void markAsUnavailable(Equipment equipment) {
		equipment.setStatus(EquipmentStatus.MAINTENANCE);
	}
	
	//returns a list of all the equipment for the GUI component to show for equipment management
	public List<Equipment> getAllEquipment() {
		return eDb.getEquipmentAsList();
	}
	
}
