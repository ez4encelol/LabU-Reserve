package model.systemFacades;

import java.util.List;

import model.UserFactory;
import model.database.DatabaseService;
import model.database.EquipmentTable;
import model.database.ReservationTable;
import model.database.UserTable;
import model.enums.UserType;
import model.exceptions.EmailNotUniqueException;
import model.exceptions.WeakPasswordException;
import model.userhierarchy.User;

//facade for the subsystem of user login and registration
public class LoginAndRegistrationFacade {
	
	UserFactory f=new UserFactory();
	UserTable uDb;
	ReservationTable rDb;
	EquipmentTable eDb;
	DatabaseService sDb;

	public LoginAndRegistrationFacade() {
		uDb=UserTable.getInstance();
		rDb=ReservationTable.getInstance();
		eDb=EquipmentTable.getInstance();
		sDb=DatabaseService.getInstance();
	}
	
	//returns user if exists, otherwise returns null
	//since emails have to be unique, then every account can be identified with email
	public User loginUser(String email, String password) {
		User u = sDb.findUser(email);
		if(u==null) {
			return null;
		}
		//check if password matches
		if(!u.getPassword().equals(password)) {
			return null;
		}
		return u;
	}
	
	//returns lab head coordinator if exists, otherwise returns null
	public User loginHeadLabCoordinator(String email, String password) {
		User u=loginUser(email, password);
		if(u.getUserType()==UserType.HEADLABCOORDINATOR) {
			return u;
		}
		return null;
	}
	
	//return lab manager if exists, otherwise returns null
	public User loginLabManager(String email, String password) {
		User u=loginUser(email, password);
		if(u.getUserType()==UserType.LABMANAGER) {
			return u;
		}
		return null;
	}
	
	//return user if the email not unique, or the password not strong enough, otherwise return the new user
	public void registerUser(String email, String username, String password, int verificationNum, UserType type) {
		User u = createUser(email, username, password, verificationNum, type);
		uDb.addUser(u);
	}
	
	public void registerLabManager(String email, String username, String password, int verificationNum) {
		User u = createUser(email, username, password, verificationNum, UserType.LABMANAGER);
		uDb.addUser(u);
	}
	
	public User createUser(String email, String username, String password, int verificationNum, UserType type) {
		if(!checkUniqueEmail(email)) {
			throw new EmailNotUniqueException("Error registering user: email not unique");
		}
		
		if(!checkStrongPassword(password)) {
			throw new WeakPasswordException("Error registering user: password weak");
		}
		User u=f.createUser(email, username, password, verificationNum, type);
		return u;
	}
	
	private boolean checkStrongPassword(String password) {
		/*Use regex to to check if uppercase, lowercase, numbers, and symbols exist*/
		String lowerCaseRegex=".*[a-z].*";
		String upperCaseRegex=".*[A-Z].*";
		String digitRegex=".*[0-9].*";
		String specialCharRegex=".*[^a-zA-Z0-9 ].*";
		
		//note the .* means matching any number of any characters
		
		if(password.matches(lowerCaseRegex) && password.matches(upperCaseRegex) &&
				password.matches(digitRegex) && password.matches(specialCharRegex)) {
			return true;
		}
		else {
			return false;
		}
	}
	private boolean checkUniqueEmail(String email) {
		List<User> users = uDb.getUsersAsList();
		for(User current : users) {
			if(current.getEmail().equals(email)) {
				return false;
			}
		}
		return true;
	}
	
}
