package model.systemFacades;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import model.Checkout;
import model.Equipment;
import model.IdGeneration;
import model.Reservation;
import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.EquipmentStatus;
import model.enums.ReservationStatus;
import model.paymentclasses.PaymentStrategy;
import model.userhierarchy.User;

public class EquipmentReservationFacade {

	UserTable uDb;
	ReservationTable rDb;
	EquipmentTable eDb;
	DatabaseService sDb;

	public EquipmentReservationFacade() {
		uDb=UserTable.getInstance();
		rDb=ReservationTable.getInstance();
		eDb=EquipmentTable.getInstance();
		sDb=DatabaseService.getInstance();
	}
	
	public void makePayment(Reservation reservation, int amount, PaymentStrategy strategy) {
		int totalOwed=reservation.getTotalOwed();
		if(totalOwed-amount>=0) {
			reservation.setTotalOwed(totalOwed-amount);
		}
		else {
			reservation.setTotalOwed(0);
		}
		Checkout checkout=new Checkout();
		checkout.setPaymentStrategy(strategy);
		checkout.makePayment(amount);
	}
	
	public boolean hasConflict(LocalDateTime startTime, LocalDateTime endTime, int userId, int equipmentId){
		List<Reservation> reservations = rDb.getReservationsAsList();
		for (Reservation r : reservations) {
			if (r.getEquipmentId() == equipmentId && (r.getStatus()==ReservationStatus.ACTIVE || r.getStatus()==ReservationStatus.ARRIVED)) {
				if (startTime.isBefore(r.getEndTime()) && endTime.isAfter(r.getStartTime())) {
					return true;
				}
			}
		}
		return false;
	}
	
	//return false if the equipment is already reserved for that time interval or if the equipment is not enabled
	public boolean reserveEquipment(LocalDateTime startTime, LocalDateTime endTime, User u, Equipment e, PaymentStrategy strategy) {
		if(hasConflict(startTime, endTime, u.getId(), e.getId())) {
			return false;
		}
		if(e.getStatus()!=EquipmentStatus.ENABLED) { //if equipment not enabled by manager, then can't create reservation
			return false;
		}
		Reservation r=new Reservation(IdGeneration.getInstance().nextReservationId(), startTime, endTime, u.getId(), e.getId());
		r.setDepositAmount(u.getHourlyRate());
		int hoursBetween=(int)ChronoUnit.HOURS.between(startTime, endTime);
		int minutesBetween=(int)ChronoUnit.MINUTES.between(startTime, endTime);
		int totalOwed;
		//round up the cost to the nearest hour
		if(minutesBetween%60!=0) {
			totalOwed=(hoursBetween+1)*u.getHourlyRate();
		}
		else {
			totalOwed=hoursBetween*u.getHourlyRate();
		}
		r.setTotalOwed(totalOwed);
		r.setStatus(ReservationStatus.ACTIVE);
		Checkout checkout=new Checkout();
		checkout.setPaymentStrategy(strategy);
		checkout.makePayment(u.getHourlyRate());
		rDb.addReservation(r);
		return true;
	}
	
	//returns false if the reservation cannot be extended
	public boolean extendReservation(Reservation reservation, int hours) {
		//if the equipment is not enabled, then can't extend reservation
		if(eDb.getEquipment(reservation.getEquipmentId()).getStatus()!=EquipmentStatus.ENABLED) { //if equipment not enabled by manager, then can't create reservation
			return false;
		}
		//if reservation is cancelled, or its a Noshow, then can't extend it
		if(reservation.getStatus()==ReservationStatus.CANCELLED ) {
			return false;
		}
		//extending a reservation is like creating a new reservation between times endTime, and the final hours
		LocalDateTime newEndTime=reservation.getEndTime().plusHours(hours);
		if(hasConflict(reservation.getEndTime(), newEndTime, reservation.getUserId(), reservation.getEquipmentId())) {
			return false;
		}
		reservation.extendReservation(hours);
		return true;
	}
	
	//returns true if cancel is successful, false otherwise
	public boolean cancelReservation(Reservation reservation) {
		//if before the startTime of the reservation, can cancel
		if(reservation.getStartTime().isAfter(LocalDateTime.now())) {
			reservation.cancelReservation();
			return true;
		}
		return false;
		
	}
	
	//returns list of all the Enabled equipment for GUI to show equipment that can be reserved
	public List<Equipment> getEnabledEquipment(){
		List<Equipment> allEquipment=eDb.getEquipmentAsList();
		List<Equipment> availableEquipment=new ArrayList<>();
		for(Equipment e : allEquipment) {
			if(e.getStatus()==EquipmentStatus.ENABLED) {
				availableEquipment.add(e);
			}
		}
		return availableEquipment;
	}
	
	//returns list of all reservations from a particular user
	public List<Reservation> getUserReservations(User u){
		return sDb.getUserReservations(u);
	}
	
	public List<Reservation> getActiveUserReservations(User u){
		List<Reservation> allReservations= sDb.getUserReservations(u);
		List<Reservation> notCancelled = new ArrayList<>();
		for(Reservation r: allReservations) {
			if(r.getStatus()==ReservationStatus.ACTIVE) {
				notCancelled.add(r);
			}
		}
		return notCancelled;
	}
	
	//returns string of available times to display to the user
	//TODO: Complete implementation
	public String getUnavailableTimes(Equipment e) {
		String result="";
		List<Reservation> allReservations=sDb.getEquipmentReservations(e);
		for(int i=0; i<allReservations.size(); i++) {
			Reservation r=allReservations.get(i);
			if(i<allReservations.size()-1) {
				result+= "Start: "+ r.getStartTime().toString() +" End: " + r.getEndTime().toString() + "; ";
			}
			else {
				result+= "Start: "+ r.getStartTime().toString() +" End: " + r.getEndTime().toString();
			}
		}
		return result;
	}
	
	
}
