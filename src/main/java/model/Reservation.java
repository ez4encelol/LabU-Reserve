package model;

import java.time.LocalDateTime;
import java.util.List;

import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.ReservationStatus;
import model.userhierarchy.User;

public class Reservation implements SensorObserver{

	private int id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	private int equipmentId;
	private int userId;
	private int paymentStrategyId;

	private ReservationStatus status;
	private int totalOwed;
	private int depositAmount;

	public Reservation(int id, LocalDateTime startTime, LocalDateTime endTime, int userId, int equipmentId) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.userId = userId;
		this.equipmentId = equipmentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return this.endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public int getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ReservationStatus getStatus() {
		return this.status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public int getTotalOwed() {
		return this.totalOwed;
	}

	public void setTotalOwed(int totalOwed) {
		this.totalOwed = totalOwed;
	}
	
	public void setDepositAmount(int depositAmount) {
		this.depositAmount=depositAmount;
	}

	public int getDepositAmount() {
		return this.depositAmount;
	}

	public void cancelReservation() {
		status = ReservationStatus.CANCELLED;
	}

	public void extendReservation(int hours) {
		totalOwed+=hours*UserTable.getInstance().getUser(userId).getHourlyRate();
		endTime = endTime.plusHours(hours);
	}
	public User getUser() {
	    return UserTable.getInstance().getUser(userId);
	}
	
	public Equipment getEquipment() {
		return EquipmentTable.getInstance().getEquipment(equipmentId);
	}
	
	@Override
	public void update(Sensor sensor, String updateMessage) {
		/*if there is an arrival within 20 minutes after the start time, and the reservation is ACTIVE,
		 *(i.e not cancelled or such) then update status to ARRIVED
		 */
		if(updateMessage.equals("arrive") && status==ReservationStatus.ACTIVE) {
			LocalDateTime currentTime=LocalDateTime.now();
			if(currentTime.isAfter(startTime) && currentTime.isBefore(startTime.plusMinutes(20))) {
				status=ReservationStatus.ARRIVED;
				//subtract the deposit from the total amount owed
				if(totalOwed-depositAmount>=0) {
					totalOwed-=depositAmount;
				}
				else {
					totalOwed=0;
				}
			}
		}
	}

	public String toString() {
		return String.format("%s %s %s %s %s %s %s %s", 
				String.valueOf(id), 
				startTime.toString(), 
				endTime.toString(), 
				String.valueOf(equipmentId),
				String.valueOf(userId), 
				status.toString(),
				String.valueOf(totalOwed),
				String.valueOf(depositAmount));
	}
}