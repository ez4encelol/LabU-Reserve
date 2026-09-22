package model.userhierarchy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import model.enums.UserType;

@Entity
@DiscriminatorValue("HEADLABCOORDINATOR")
public class HeadLabCoordinator extends UniversityAffiliated {

    protected HeadLabCoordinator() {
        super();
    }

    public HeadLabCoordinator(int userId, String email, String userName, String password, int verificationNum) {
        super(userId, email, userName, password, verificationNum, model.enums.UserType.HEADLABCOORDINATOR);
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Transient
    public int getUserId() {
        return this.userId;
    }

    // Head lab coordinator doesn't reserve equipment, return default rate
    @Override
    @Transient
    public int getHourlyRate() {
        return 0;
    }
}
