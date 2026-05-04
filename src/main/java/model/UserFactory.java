package model;

import model.enums.UserType;
import model.userhierarchy.Faculty;
import model.userhierarchy.Guest;
import model.userhierarchy.HeadLabCoordinator;
import model.userhierarchy.LabManager;
import model.userhierarchy.Researcher;
import model.userhierarchy.Student;
import model.userhierarchy.User;

public class UserFactory {

  public User createUser(String email, String username, String password, int verificationNum, UserType type) {
      return createUser(IdGeneration.getInstance().nextUserId(), email, username, password, verificationNum, type);
  }

	public User createUser(int id, String email, String username, String password, int verificationNum, UserType type) {
		User u=null;
		switch(type) {
		case STUDENT:
			u=new Student(id, email, username, password, verificationNum);
			break;
		case FACULTY:
			u=new Faculty(id, email, username, password, verificationNum);
			break;
		case GUEST:
			u=new Guest(id, email, username, password, verificationNum);
			break;
		case RESEARCHER:
			u=new Researcher(id, email, username, password, verificationNum);
			break;
		case LABMANAGER:
			u=new LabManager(id, email, username, password, verificationNum);
			break;
		case HEADLABCOORDINATOR:
			u=HeadLabCoordinator.getInstance();
			((HeadLabCoordinator)u).initialize(id, email, username, password, verificationNum);
		default:
			break;
			
		}
		return u;
	}
}
