package com.cafe.services;

import com.cafe.entities.Order;
import com.cafe.entities.PaymentMethod;
import com.cafe.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {
    private List<Order> orders;
    private ProductService productService;

    public OrderService(ProductService productService) {
        this.orders = new ArrayList<>();
        this.productService = productService;
    }

    public Order createOrder(String userEmail, UUID productId, PaymentMethod paymentMethod, String address) {
        Product product = productService.getProductById(productId);
        if (product == null || !product.isAvailable()) {
            return null;
        }
        Order newOrder = new Order(userEmail, productId, paymentMethod, address);
        orders.add(newOrder);
        return newOrder;
    }

    public List<Order> getUserOrders(String userEmail) {
        return orders.stream()
                .filter(o -> o.getUserEmail().equals(userEmail))
                .toList();
    }
}
