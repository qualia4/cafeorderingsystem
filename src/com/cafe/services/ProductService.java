package com.cafe.services;

import com.cafe.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductService {
    private List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>();
        initializeProducts();
    }

    private void initializeProducts() {
        products.add(new Product("Espresso", 250));
        products.add(new Product("Latte", 350));
        products.add(new Product("Cappuccino", 300));
        products.add(new Product("Croissant", 200));
    }

    public List<Product> getAvailableProducts() {
        return products.stream().filter(Product::isAvailable).toList();
    }

    public Product getProductById(UUID id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}