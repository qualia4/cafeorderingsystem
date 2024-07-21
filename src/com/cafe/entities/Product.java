package com.cafe.entities;

import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private int price;
    private boolean isAvailable;

    public Product(String name, int price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.isAvailable = true;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
