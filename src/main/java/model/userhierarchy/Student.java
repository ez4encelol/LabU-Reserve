package model.userhierarchy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import model.enums.UserType;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends UniversityAffiliated{

	protected Student() { super(); }

	public Student(int userId, String email, String userName, String passWord, int verificationNum) {
		super(userId, email, userName, passWord, verificationNum, UserType.STUDENT);
	}

	@Transient
	private final int hourlyRate=10;

	@Override
	@Transient
	public int getHourlyRate() {
		return this.hourlyRate;
	}
}
