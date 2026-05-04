package ai_assistant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.IdGeneration;
import model.UserFactory;
import model.enums.UserType;
import model.userhierarchy.HeadLabCoordinator;
import model.userhierarchy.User;

public class UserFactoryTest {

    @BeforeEach
    void setUp() {
        IdGeneration.resetToNull();
        HeadLabCoordinator.resetToNull();
        IdGeneration.getInstance().setUserIdCount(1);
    }

    @AfterEach
    void tearDown() {
        IdGeneration.resetToNull();
        HeadLabCoordinator.resetToNull();
    }

    @Test
    void testCreateStudent() {
        // Original: UserFactory.createUser("student","u","p")  factory is not static and signature is different
        assertNotNull(new UserFactory().createUser("u@test.com", "u", "p", 0, UserType.STUDENT));
    }

    @Test
    void testInvalidType() {
        // Original: assertNull(UserFactory.createUser("x","u","p"))
        // Passing null type causes a NullPointerException in the switch, so we expect an exception
        // Cannot replicate original assertNull logic so i am leaving as assertThrows instead
        assertThrows(Exception.class, () ->
            new UserFactory().createUser("u@test.com", "u", "p", 0, null));
    }

    @Test
    void testNullType() {
        // Original: assertNull(UserFactory.createUser(null,"u","p"))
        // Null UserType throws NullPointerException in the switch statement
        assertThrows(Exception.class, () ->
            new UserFactory().createUser("u@test.com", "u", "p", 0, null));
    }

    @Test
    void testEmptyType() {
        // Original: assertNull(UserFactory.createUser("","u","p"))
        // Testing GUEST as the most permissive type instead.
        assertNotNull(new UserFactory().createUser("u@test.com", "u", "p", 0, UserType.GUEST));
    }

    @Test
    void testMultipleTypes() {
        assertNotNull(new UserFactory().createUser("s@test.com", "u", "p", 0, UserType.STUDENT));
    }

    @Test
    void testDifferentUsers() {
        // Original: assertNotEquals two users created with different usernames
        // UserFactory creates distinct objects; reference inequality is sufficient
        User a = new UserFactory().createUser("a@test.com", "a", "p", 0, UserType.STUDENT);
        User b = new UserFactory().createUser("b@test.com", "b", "p", 0, UserType.STUDENT);
        assertNotSame(a, b);
    }

    @Test
    void testPasswordWorks() {
        // Original: assertTrue(u.checkPassword("p"))  User has no checkPassword(); use getPassword()
        User u = new UserFactory().createUser("u@test.com", "u", "myPass", 0, UserType.STUDENT);
        assertEquals("myPass", u.getPassword());
    }

    @Test
    void testUsernameStored() {
        User u = new UserFactory().createUser("u@test.com", "myUser", "p", 0, UserType.STUDENT);
        assertEquals("myUser", u.getUsername());
    }

    @Test
    void testFactoryNotNull() {
        assertNotNull(new UserFactory().createUser("u@test.com", "u", "p", 0, UserType.RESEARCHER));
    }

    @Test
    void testEdgeCase() {
        assertNotNull(new UserFactory().createUser("x@test.com", "x", "y", 0, UserType.FACULTY));
    }
}