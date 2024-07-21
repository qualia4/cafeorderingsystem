package com.cafe.main;

import com.cafe.entities.Order;
import com.cafe.entities.PaymentMethod;
import com.cafe.entities.Product;
import com.cafe.services.OrderService;
import com.cafe.services.ProductService;
import com.cafe.services.UserService;

import java.util.List;
import java.util.Scanner;

public class CafeOrderingSystem {
    private static UserService userService = new UserService();
    private static ProductService productService = new ProductService();
    private static OrderService orderService = new OrderService(productService);
    private static Scanner scanner = new Scanner(System.in);
    private static String currentUserEmail = null;

    public static void main(String[] args) {
        while (true) {
            if (currentUserEmail == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showLoginMenu() {
        System.out.println("\n1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void register() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userService.registerUser(email, name, password)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Email already exists.");
        }
    }

    private static void login() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userService.login(email, password)) {
            System.out.println("Login successful!");
            currentUserEmail = email;
        } else {
            System.out.println("Login failed. Invalid email or password.");
        }
    }

    private static void showMainMenu() {
        System.out.println("\n1. View Menu");
        System.out.println("2. Place Order");
        System.out.println("3. View Orders");
        System.out.println("4. Logout");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                viewMenu();
                break;
            case 2:
                placeOrder();
                break;
            case 3:
                viewOrders();
                break;
            case 4:
                logout();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void viewMenu() {
        List<Product> products = productService.getAvailableProducts();
        System.out.println("\nMenu:");
        for (Product product : products) {
            System.out.printf("%s - $%.2f\n", product.getName(), product.getPrice() / 100.0);
        }
    }

    private static void placeOrder() {
        viewMenu();
        System.out.print("Enter the name of the product you want to order: ");
        String productName = scanner.nextLine();

        Product selectedProduct = productService.getAvailableProducts().stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);

        if (selectedProduct == null) {
            System.out.println("Invalid product name.");
            return;
        }

        System.out.print("Choose delivery method (1. In-store pickup, 2. Delivery): ");
        int deliveryChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String address = "";
        if (deliveryChoice == 2) {
            System.out.print("Enter delivery address: ");
            address = scanner.nextLine();
        }

        Order newOrder = orderService.createOrder(currentUserEmail, selectedProduct.getId(), PaymentMethod.CASH, address);
        if (newOrder != null) {
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Failed to place order.");
        }
    }

    private static void viewOrders() {
        List<Order> userOrders = orderService.getUserOrders(currentUserEmail);
        if (userOrders.isEmpty()) {
            System.out.println("You have no orders.");
        } else {
            System.out.println("\nYour Orders:");
            for (Order order : userOrders) {
                Product product = productService.getProductById(order.getProductId());
                System.out.printf("Product: %s, Status: %s, Payment Method: %s\n",
                        product.getName(), order.getStatus(), order.getPaymentMethod());
            }
        }
    }

    private static void logout() {
        currentUserEmail = null;
        System.out.println("Logged out successfully.");
    }
}
