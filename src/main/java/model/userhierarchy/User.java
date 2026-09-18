package model.userhierarchy;

import javax.persistence.*;
import model.enums.UserType;

/**
 * Root entity for all user types.
 * Uses SINGLE_TABLE inheritance: all user subclasses share one table
 * ("users") distinguished by the discriminator column "user_type".
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Access(AccessType.FIELD)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int userId;

    protected String userName;
    protected String password;
    protected String email;
    protected boolean universityAffiliated;
    protected int verificationNum;
    protected boolean isDepartmentApproved;

    /** JPA no-arg constructor */
    protected User() {}

    /**
     * Full constructor (kept for backward compatibility with UserFactory).
     * The {@code userType} param is accepted but not stored — it is derived
     * from {@link #getClass()} via {@link #getUserType()}.
     */
    public User(int userId, String email, String userName, String password,
                int verificationNum, UserType userType) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.verificationNum = verificationNum;
        this.isDepartmentApproved = false;
    }

    /** Constructor without explicit ID (for programmatic creation; JPA generates the ID). */
    public User(String email, String userName, String password, int verificationNum, UserType userType) {
        this(0, email, userName, password, verificationNum, userType);
    }

    @Transient
    public abstract int getHourlyRate();

    public int getId() {
        return this.userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getUniversityAfilliated() {
        return this.universityAffiliated;
    }

    public void setUniversityAfilliated(boolean affiliated) {
        this.universityAffiliated = affiliated;
    }

    public int getVerificationNum() {
        return this.verificationNum;
    }

    public void setVerificationNum(int verificationNum) {
        this.verificationNum = verificationNum;
    }

    public boolean getIsDepartmentApproved() {
        return this.isDepartmentApproved;
    }

    public void setIsDepartmentApproved(boolean approval) {
        this.isDepartmentApproved = approval;
    }

    /**
     * Derives the user type from the concrete entity class at runtime,
     * avoiding a redundant persisted column (the discriminator column already
     * encodes this information).
     */
    @Transient
    public UserType getUserType() {
        if (this instanceof Student) return UserType.STUDENT;
        if (this instanceof Faculty) return UserType.FACULTY;
        if (this instanceof Guest) return UserType.GUEST;
        if (this instanceof Researcher) return UserType.RESEARCHER;
        if (this instanceof LabManager) return UserType.LABMANAGER;
        if (this instanceof HeadLabCoordinator) return UserType.HEADLABCOORDINATOR;
        return null;
    }
}
