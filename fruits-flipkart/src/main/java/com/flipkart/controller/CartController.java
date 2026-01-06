package com.flipkart.controller;

import com.flipkart.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    
    @Autowired
    private FruitService fruitService;
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getCart() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Cart retrieved successfully");
        response.put("data", fruitService.getCart());
        response.put("total", fruitService.getCartTotal());
        response.put("itemCount", fruitService.getCart().size());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/add/{fruitId}")
    public ResponseEntity<Map<String, Object>> addToCart(
            @PathVariable Long fruitId,
            @RequestParam(defaultValue = "1") Integer quantity) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            var cartItem = fruitService.addToCart(fruitId, quantity);
            response.put("success", true);
            response.put("message", "Added to cart successfully");
            response.put("data", cartItem);
            response.put("cartTotal", fruitService.getCartTotal());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<Map<String, Object>> removeFromCart(@PathVariable Long cartItemId) {
        fruitService.removeFromCart(cartItemId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Item removed from cart");
        response.put("cartTotal", fruitService.getCartTotal());
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/clear")
    public ResponseEntity<Map<String, Object>> clearCart() {
        fruitService.clearCart();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Cart cleared successfully");
        return ResponseEntity.ok(response);
    }
}
