package com.cafe.test;

import com.cafe.entities.Order;
import com.cafe.entities.PaymentMethod;
import com.cafe.entities.Product;
import com.cafe.services.OrderService;
import com.cafe.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class OrderServiceTest {

    private OrderService orderService;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
        orderService = new OrderService(productService);
    }

    @Test
    void testCreateOrder() {
        Product product = productService.getAvailableProducts().get(0);
        Order order = orderService.createOrder("test@example.com", product.getId(), PaymentMethod.CREDIT_CARD, "123 Test St");

        assertNotNull(order);
        assertEquals("test@example.com", order.getUserEmail());
        assertEquals(product.getId(), order.getProductId());
        assertEquals(PaymentMethod.CREDIT_CARD, order.getPaymentMethod());
        assertEquals("123 Test St", order.getAddress());
    }

    @Test
    void testCreateOrderWithInvalidProduct() {
        Order order = orderService.createOrder("test@example.com", java.util.UUID.randomUUID(), PaymentMethod.CASH, "");
        assertNull(order);
    }

    @Test
    void testGetUserOrders() {
        Product product1 = productService.getAvailableProducts().get(0);
        Product product2 = productService.getAvailableProducts().get(1);

        orderService.createOrder("user1@example.com", product1.getId(), PaymentMethod.CASH, "");
        orderService.createOrder("user1@example.com", product2.getId(), PaymentMethod.CREDIT_CARD, "456 Test Ave");
        orderService.createOrder("user2@example.com", product1.getId(), PaymentMethod.DEBIT_CARD, "789 Test Blvd");

        List<Order> user1Orders = orderService.getUserOrders("user1@example.com");
        assertEquals(2, user1Orders.size());

        List<Order> user2Orders = orderService.getUserOrders("user2@example.com");
        assertEquals(1, user2Orders.size());

        List<Order> nonexistentUserOrders = orderService.getUserOrders("nonexistent@example.com");
        assertTrue(nonexistentUserOrders.isEmpty());
    }
}
