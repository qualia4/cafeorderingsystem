package com.cafe.test;

import com.cafe.entities.Product;
import com.cafe.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    void testGetAvailableProducts() {
        List<Product> availableProducts = productService.getAvailableProducts();
        assertFalse(availableProducts.isEmpty());
        assertTrue(availableProducts.stream().allMatch(Product::isAvailable));
    }

    @Test
    void testGetProductById() {
        List<Product> availableProducts = productService.getAvailableProducts();
        Product firstProduct = availableProducts.get(0);
        UUID productId = firstProduct.getId();

        Product retrievedProduct = productService.getProductById(productId);
        assertNotNull(retrievedProduct);
        assertEquals(firstProduct.getName(), retrievedProduct.getName());
        assertEquals(firstProduct.getPrice(), retrievedProduct.getPrice());

        assertNull(productService.getProductById(UUID.randomUUID()));
    }
}
