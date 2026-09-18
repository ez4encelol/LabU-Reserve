package model.userhierarchy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import model.enums.UserType;

@Entity
@DiscriminatorValue("FACULTY")
public class Faculty extends UniversityAffiliated{

	protected Faculty() { super(); }

	public Faculty(int userId, String email, String userName, String passWord, int verificationNum) {
		super(userId, email, userName, passWord, verificationNum, UserType.FACULTY);
	}


	@Transient
	private final int hourlyRate=15;

	
	@Override
	@Transient
	public int getHourlyRate() {
		return this.hourlyRate;
	}
}
