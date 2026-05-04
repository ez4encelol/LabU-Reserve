package model.userhierarchy;

import model.enums.UserType;

public abstract class User {
	protected int userId;
	protected String userName;
	protected String password;
	protected String email;
	protected boolean universityAffiliated;
	protected int verificationNum; //studentID/StaffID/CertificationNumber
	protected boolean isDepartmentApproved; //department approval
	protected UserType userType;

	public User(int userId, String email, String userName, String password, int verificationNum, UserType userType) {
		this.userId=userId;
		this.userName=userName;
		this.password=password;
		this.email=email;
		this.verificationNum=verificationNum;
		this.userType = userType;
		isDepartmentApproved=false;
	}

	public abstract int getHourlyRate();

	public int getId() {
		return this.userId;
	}
	public String getUsername() {
		return this.userName;
	}
	public String getPassword() {
		return this.password;
	}
	public String getEmail(){
		return this.email;
	}
	public boolean getUniversityAfilliated() {
		return this.universityAffiliated;
	}
	public void setUniversityAfilliated(boolean affiliated) {
		this.universityAffiliated = affiliated;
	}
	public int getVerificationNum() {
		return this.verificationNum;
	}
	public UserType getUserType(){
		return this.userType;
	}
	public boolean getIsDepartmentApproved() {
		return this.isDepartmentApproved;
	}

	public void setIsDepartmentApproved(boolean approval) {
		this.isDepartmentApproved=approval;
	}
}
