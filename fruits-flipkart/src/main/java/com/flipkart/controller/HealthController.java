package com.flipkart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {
    
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Fruits Flipkart");
        response.put("version", "1.0.0");
        response.put("timestamp", LocalDateTime.now());
        response.put("java", System.getProperty("java.version"));
        response.put("memory", Runtime.getRuntime().maxMemory() / 1024 / 1024 + "MB");
        return response;
    }
    
    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Fruits Flipkart API");
        response.put("endpoints", Map.of(
            "GET /health", "Health check",
            "GET /api/fruits", "Get all fruits",
            "POST /api/cart/add/{id}", "Add to cart",
            "GET /api/cart", "View cart",
            "POST /api/orders/place", "Place order",
            "GET /api/orders", "View orders"
        ));
        response.put("sampleFruits", 8);
        response.put("ready", true);
        return response;
    }
}
