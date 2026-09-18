package model.userhierarchy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import model.enums.UserType;

@Entity
@DiscriminatorValue("LABMANAGER")
public class LabManager extends UniversityAffiliated{

	protected LabManager() { super(); }

	public LabManager(int userId, String email, String userName, String password, int verificationNum) {
		super(userId, email, userName, password, verificationNum, UserType.LABMANAGER);
	}

	//lab managers don't have hourly rate, they do not reserve equipment, return default value
	@Override
	@Transient
	public int getHourlyRate() {
		// TODO Auto-generated method stub
		return 0;
	}

}
