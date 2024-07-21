package com.cafe.test;

import com.cafe.entities.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTests {

    @Test
    public void testProductCreation() {
        Product product = new Product("Espresso", 250);
        assertNotNull(product.getId());
        assertEquals("Espresso", product.getName());
        assertEquals(250, product.getPrice());
        assertTrue(product.isAvailable());
    }

    @Test
    public void testSetAvailable() {
        Product product = new Product("Latte", 350);
        product.setAvailable(false);
        assertFalse(product.isAvailable());
    }

    @Test
    public void testSetPrice() {
        Product product = new Product("Cappuccino", 300);
        product.setPrice(320);
        assertEquals(320, product.getPrice());
    }

    @Test
    public void testSetName() {
        Product product = new Product("Mocha", 400);
        product.setName("Mocha Latte");
        assertEquals("Mocha Latte", product.getName());
    }
}
