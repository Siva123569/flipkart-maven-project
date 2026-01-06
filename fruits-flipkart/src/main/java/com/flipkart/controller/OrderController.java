package com.flipkart.controller;

import com.flipkart.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private FruitService fruitService;
    
    @PostMapping("/place")
    public ResponseEntity<Map<String, Object>> placeOrder(
            @RequestBody Map<String, String> request) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            String customerName = request.get("customerName");
            String address = request.get("address");
            
            if (customerName == null || customerName.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Customer name is required");
                return ResponseEntity.badRequest().body(response);
            }
            
            if (address == null || address.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Address is required");
                return ResponseEntity.badRequest().body(response);
            }
            
            var order = fruitService.placeOrder(customerName, address);
            response.put("success", true);
            response.put("message", "Order placed successfully");
            response.put("data", order);
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllOrders() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Orders retrieved successfully");
        response.put("data", fruitService.getAllOrders());
        response.put("count", fruitService.getAllOrders().size());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOrder(@PathVariable Long id) {
        var order = fruitService.getOrderById(id);
        Map<String, Object> response = new HashMap<>();
        
        if (order == null) {
            response.put("success", false);
            response.put("message", "Order not found");
            return ResponseEntity.status(404).body(response);
        }
        
        response.put("success", true);
        response.put("message", "Order retrieved successfully");
        response.put("data", order);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        
        var order = fruitService.updateOrderStatus(id, status);
        Map<String, Object> response = new HashMap<>();
        
        if (order == null) {
            response.put("success", false);
            response.put("message", "Order not found");
            return ResponseEntity.status(404).body(response);
        }
        
        response.put("success", true);
        response.put("message", "Order status updated");
        response.put("data", order);
        return ResponseEntity.ok(response);
    }
}
