package model.userhierarchy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import model.enums.UserType;

@Entity
@DiscriminatorValue("GUEST")
public class Guest extends User{

	protected Guest() { super(); }

	public Guest(int userId, String email, String userName, String passWord, int verificationNum) {
		super(userId, email, userName, passWord, verificationNum, UserType.GUEST);
		universityAffiliated=false;
	}

	@Transient
	private final int hourlyRate=30;
	

	
	@Override
	@Transient
	public int getHourlyRate() {
		return this.hourlyRate;
	}
	
}
