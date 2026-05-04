package model.userhierarchy;

import model.enums.UserType;

public class Guest extends User{
	
	public Guest(int userId, String email, String userName, String passWord, int verificationNum) {
		super(userId, email, userName, passWord, verificationNum, UserType.GUEST);
		universityAffiliated=false;
	}

	private final int hourlyRate=30;
	

	
	@Override
	public int getHourlyRate() {
		return this.hourlyRate;
	}
	
}
