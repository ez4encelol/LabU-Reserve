package model.userhierarchy;

import model.enums.UserType;

public class Faculty extends UniversityAffiliated{
	
	public Faculty(int userId, String email, String userName, String passWord, int verificationNum) {
		super(userId, email, userName, passWord, verificationNum, UserType.FACULTY);
	}


	private final int hourlyRate=15;

	
	@Override
	public int getHourlyRate() {
		return this.hourlyRate;
	}
}
