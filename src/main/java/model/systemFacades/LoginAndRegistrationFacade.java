package model.systemFacades;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import model.UserFactory;
import model.database.DatabaseService;
import model.enums.UserType;
import model.exceptions.EmailNotUniqueException;
import model.exceptions.WeakPasswordException;
import model.repository.UserRepository;
import model.userhierarchy.User;

/**
 * Facade for user login and registration.
 * Backed by Spring Data JPA {@link UserRepository}.
 */
@Component
public class LoginAndRegistrationFacade {

    private final UserRepository userRepository;
    private final DatabaseService databaseService;
    private final UserFactory userFactory = new UserFactory();

    public LoginAndRegistrationFacade(UserRepository userRepository, DatabaseService databaseService) {
        this.userRepository = userRepository;
        this.databaseService = databaseService;
    }

    /** Delegate to Spring for singleton-style access from GUI classes. */
    public static LoginAndRegistrationFacade getInstance() {
        return model.SpringContext.getBean(LoginAndRegistrationFacade.class);
    }

    /** Returns user if exists and password matches; otherwise returns null. */
    public User loginUser(String email, String password) {
        User u = databaseService.findUser(email);
        if (u == null) {
            return null;
        }
        if (!u.getPassword().equals(password)) {
            return null;
        }
        return u;
    }

    /** Returns the Head Lab Coordinator if login matches; otherwise null. */
    public User loginHeadLabCoordinator(String email, String password) {
        User u = loginUser(email, password);
        if (u != null && u.getUserType() == UserType.HEADLABCOORDINATOR) {
            return u;
        }
        return null;
    }

    /** Returns the Lab Manager if login matches; otherwise null. */
    public User loginLabManager(String email, String password) {
        User u = loginUser(email, password);
        if (u != null && u.getUserType() == UserType.LABMANAGER) {
            return u;
        }
        return null;
    }

    @Transactional
    public void registerUser(String email, String username, String password, int verificationNum, UserType type) {
        User u = createUser(email, username, password, verificationNum, type);
        userRepository.save(u);
    }

    @Transactional
    public void registerLabManager(String email, String username, String password, int verificationNum) {
        User u = createUser(email, username, password, verificationNum, UserType.LABMANAGER);
        userRepository.save(u);
    }

    public User createUser(String email, String username, String password, int verificationNum, UserType type) {
        if (!checkUniqueEmail(email)) {
            throw new EmailNotUniqueException("Error registering user: email not unique");
        }
        if (!checkStrongPassword(password)) {
            throw new WeakPasswordException("Error registering user: password weak");
        }
        return userFactory.createUser(email, username, password, verificationNum, type);
    }

    private boolean checkStrongPassword(String password) {
        String lowerCaseRegex = ".*[a-z].*";
        String upperCaseRegex = ".*[A-Z].*";
        String digitRegex = ".*[0-9].*";
        String specialCharRegex = ".*[^a-zA-Z0-9 ].*";

        if (password.matches(lowerCaseRegex) && password.matches(upperCaseRegex) &&
                password.matches(digitRegex) && password.matches(specialCharRegex)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkUniqueEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }
}
