package com.cafe.services;

import com.cafe.entities.User;
import com.cafe.utils.PasswordHasher;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public boolean registerUser(String email, String name, String password) {
        if (users.containsKey(email)) {
            return false;
        }
        String hashedPassword = PasswordHasher.hashPassword(password);
        User newUser = new User(email, name, hashedPassword);
        users.put(email, newUser);
        return true;
    }

    public boolean login(String email, String password) {
        User user = users.get(email);
        if (user == null) {
            return false;
        }
        String hashedPassword = PasswordHasher.hashPassword(password);
        return user.getHashedPassword().equals(hashedPassword);
    }

    public User getUser(String email) {
        return users.get(email);
    }
}
