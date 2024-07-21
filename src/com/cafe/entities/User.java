package com.cafe.entities;

import java.util.UUID;

public class User {
    private String email;
    private String name;
    private String hashedPassword;
    private int orderCount;

    public User(String email, String name, String hashedPassword) {
        this.email = email;
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.orderCount = 0;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void incrementOrderCount() {
        this.orderCount++;
    }
}
