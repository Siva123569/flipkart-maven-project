package com.flipkart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FruitsApp {
    public static void main(String[] args) {
        SpringApplication.run(FruitsApp.class, args);
        System.out.println("\n🍎🍊🍌 Fruits Flipkart Started! 🍇🍓🥭");
        System.out.println("=======================================");
        System.out.println("✅ API: http://localhost:8080/api/fruits");
        System.out.println("✅ Cart: http://localhost:8080/api/cart");
        System.out.println("✅ Orders: http://localhost:8080/api/orders");
        System.out.println("✅ Health: http://localhost:8080/health");
        System.out.println("=======================================");
    }
}
