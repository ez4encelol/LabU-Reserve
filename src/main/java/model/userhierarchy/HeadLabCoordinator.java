package model.userhierarchy;

import model.enums.UserType;

//singleton head lab coordinator that has ability to create lab manager accounts
public class HeadLabCoordinator extends UniversityAffiliated{
	
	private static HeadLabCoordinator instance;

    private HeadLabCoordinator() {
    	//initialize default attributes, need to set later by the factory
    	super(0, null, null, null, 0, null);
    }

    public static HeadLabCoordinator getInstance() {
        if(instance==null) {
            instance=new HeadLabCoordinator();
        }
        return instance;
    }
    
    //initialize the attributes of the head lab coordinator
    public void initialize(int userId, String email, String userName, String password, int verificationNum) {
    	this.userId=userId;
    	this.email=email;
    	this.userName=userName;
    	this.password=password;
    	this.verificationNum=verificationNum;
    	this.userType=UserType.HEADLABCOORDINATOR;
    }
    
    public void setUserId(int userId) {
    	this.userId=userId;
    }
    public int getUserId() {
    	return this.userId;
    }
    
    //head lab coordinator doesn't reserve equipment, return default value
	@Override
	public int getHourlyRate() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//reset and set the instance to null for resetting for testing purposes
	public static void resetToNull() {
		instance=null;
	}

}
