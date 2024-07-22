package com.cafe.test;

import com.cafe.entities.User;
import com.cafe.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testRegisterUser() {
        assertTrue(userService.registerUser("test@example.com", "Test User", "password123"));
        assertFalse(userService.registerUser("test@example.com", "Another User", "password456"));
    }

    @Test
    void testLogin() {
        userService.registerUser("login@example.com", "Login User", "loginpass");
        assertTrue(userService.login("login@example.com", "loginpass"));
        assertFalse(userService.login("login@example.com", "wrongpass"));
        assertFalse(userService.login("nonexistent@example.com", "anypass"));
    }

    @Test
    void testGetUser() {
        userService.registerUser("get@example.com", "Get User", "getpass");
        User user = userService.getUser("get@example.com");
        assertNotNull(user);
        assertEquals("Get User", user.getName());
        assertNull(userService.getUser("nonexistent@example.com"));
    }
}
