package model;

import model.userhierarchy.UniversityAffiliated;
import model.userhierarchy.User;

public class Department {
	
	//approves the account. Returns false if the user is not a university affiliated account
	public boolean approveAccount(User u) {
		//to simulate university approval, can have a seperate button or whatnot that approves the account
		if(!(u instanceof UniversityAffiliated)) {
			return false;
		}
		UniversityAffiliated ua=(UniversityAffiliated)u;
		ua.setIsDepartmentApproved(true);
		
		return true;
	}
}
