import com.cafe.entities.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTests {

    @Test
    public void testUserCreation() {
        User user = new User("test@example.com", "Test User", "hashedPassword");
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Test User", user.getName());
        assertEquals("hashedPassword", user.getHashedPassword());
        assertEquals(0, user.getOrderCount());
    }

    @Test
    public void testIncrementOrderCount() {
        User user = new User("test@example.com", "Test User", "hashedPassword");
        user.incrementOrderCount();
        assertEquals(1, user.getOrderCount());
    }

    @Test
    public void testSetName() {
        User user = new User("test@example.com", "Test User", "hashedPassword");
        user.setName("New Name");
        assertEquals("New Name", user.getName());
    }

    @Test
    public void testSetHashedPassword() {
        User user = new User("test@example.com", "Test User", "hashedPassword");
        user.setHashedPassword("newHashedPassword");
        assertEquals("newHashedPassword", user.getHashedPassword());
    }

}
