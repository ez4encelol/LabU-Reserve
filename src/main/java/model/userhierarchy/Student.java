package model.userhierarchy;

import model.enums.UserType;

public class Student extends UniversityAffiliated{
	
	public Student(int userId, String email, String userName, String passWord, int verificationNum) {
		super(userId, email, userName, passWord, verificationNum, UserType.STUDENT);
	}

	private final int hourlyRate=10;

	@Override
	public int getHourlyRate() {
		return this.hourlyRate;
	}
}
