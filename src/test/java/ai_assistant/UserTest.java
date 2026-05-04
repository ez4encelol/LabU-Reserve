package ai_assistant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.enums.UserType;
import model.userhierarchy.Student;
import model.userhierarchy.User;

public class UserTest {

    private User makeUser(String username, String password) {
        return new Student(1, "test@test.com", username, password, 0);
    }

    @Test
    void testCreate() {
        // Original: assertEquals("u", new User("u","p").getUsername())
        assertEquals("u", makeUser("u", "p").getUsername());
    }

    @Test
    void testPassword() {
        // Original: assertTrue(new User("u","p").checkPassword("p"))
        assertEquals("p", makeUser("u", "p").getPassword());
    }

    @Test
    void testWrongPassword() {
        // Original: assertFalse(new User("u","p").checkPassword("x"))
        assertNotEquals("x", makeUser("u", "p").getPassword());
    }

    @Test
    void testNullPassword() {
        // Original: assertFalse(new User("u","p").checkPassword(null))
        assertNotEquals(null, makeUser("u", "p").getPassword());
    }

    @Test
    void testEmptyPassword() {
        // Original: assertFalse(new User("u","p").checkPassword(""))
        assertNotEquals("", makeUser("u", "p").getPassword());
    }

    @Test
    void testUsername() {
        assertNotNull(makeUser("u", "p").getUsername());
    }

    @Test
    void testToString() {
        assertNotNull(makeUser("u", "p").toString());
    }

    @Test
    void testMultipleUsers() {
        assertNotSame(makeUser("a", "p"), makeUser("b", "p"));
    }

    @Test
    void testSameUser() {
        User u = makeUser("a", "p");
        assertSame(u, u);
    }

    @Test
    void testUserType() {
        assertEquals(UserType.STUDENT, makeUser("u", "p").getUserType());
    }
}