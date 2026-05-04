package model.userhierarchy;

import model.enums.UserType;

public class Researcher extends UniversityAffiliated{
	public Researcher(int userId, String email, String userName, String passWord, int verificationNum) {
		super(userId, email, userName, passWord, verificationNum, UserType.RESEARCHER);
	}
	
	private final int hourlyRate=20;
	

	
	@Override
	public int getHourlyRate() {
		return this.hourlyRate;
	}
}
