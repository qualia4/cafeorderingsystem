package com.cafe.entities;

import java.util.UUID;

public class Order {
    private String userEmail;
    private UUID productId;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private String address;

    public Order(String userEmail, UUID productId, PaymentMethod paymentMethod, String address) {
        this.userEmail = userEmail;
        this.productId = productId;
        this.status = OrderStatus.PENDING;
        this.paymentMethod = paymentMethod;
        this.address = address;
    }

    // Getters and setters
    public String getUserEmail() {
        return userEmail;
    }

    public UUID getProductId() {
        return productId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
