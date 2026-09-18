package model.userhierarchy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import model.enums.UserType;

@Entity
@DiscriminatorValue("RESEARCHER")
public class Researcher extends UniversityAffiliated{
	protected Researcher() { super(); }
	public Researcher(int userId, String email, String userName, String passWord, int verificationNum) {
		super(userId, email, userName, passWord, verificationNum, UserType.RESEARCHER);
	}
	
	@Transient
	private final int hourlyRate=20;
	

	
	@Override
	@Transient
	public int getHourlyRate() {
		return this.hourlyRate;
	}
}
