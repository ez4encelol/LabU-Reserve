package model.userhierarchy;

import model.enums.UserType;

public abstract class UniversityAffiliated extends User{
	public UniversityAffiliated(int userId, String email, String userName, String passWord, int verificationNum, UserType userType) {
		super(userId, email, userName, passWord, verificationNum, userType);
		universityAffiliated=true;
	}
}
