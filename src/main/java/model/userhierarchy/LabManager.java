package model.userhierarchy;

import model.enums.UserType;

public class LabManager extends UniversityAffiliated{

	public LabManager(int userId, String email, String userName, String password, int verificationNum) {
		super(userId, email, userName, password, verificationNum, UserType.LABMANAGER);
	}

	//lab managers don't have hourly rate, they do not reserve equipment, return default value
	@Override
	public int getHourlyRate() {
		// TODO Auto-generated method stub
		return 0;
	}

}
