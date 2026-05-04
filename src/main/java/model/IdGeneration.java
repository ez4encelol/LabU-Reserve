package model;

//class to keep track of id's and to generate the id's
public class IdGeneration {
	//note, database invariant, assumes that all id's already in database start from 0 and every new item is id incremented by 1
	/*in the associated database classes, we find the current id count, and then set it in this id generation class
	 * Then when an id is needed, the nextxId() methods are called
	*/
	//these 3 attributes tracks the next id to assign to an equipment
	private int userIdCount;
	private int equipmentIdCount;
	private int reservationIdCount;
	
	private static IdGeneration instance;
	
	private IdGeneration() {
		
	}
	
	public void setUserIdCount(int count) {
		userIdCount=count;
	}
	public void setEquipmentIdCount(int count) {
		equipmentIdCount=count;
	}
	
	public void setReservationIdCount(int count) {
		reservationIdCount=count;
	}
	
	public int nextUserId() {
		return userIdCount++;
	}
	
	public int nextEquipmentId() {
		return equipmentIdCount++;
	}
	public int nextReservationId() {
		return reservationIdCount++;
	}
	
	public static IdGeneration getInstance() {
		if(instance==null) {
			instance = new IdGeneration();
		}
		return instance;
	}
	
	//for testing purposes, reset the instance to null
	public static void resetToNull() {
		instance=null;
	}
}
