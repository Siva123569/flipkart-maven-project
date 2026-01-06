package com.flipkart.controller;

import com.flipkart.model.Fruit;
import com.flipkart.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fruits")
public class FruitController {
    
    @Autowired
    private FruitService fruitService;
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllFruits() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Fruits retrieved successfully");
        response.put("data", fruitService.getAllFruits());
        response.put("count", fruitService.getAllFruits().size());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getFruit(@PathVariable Long id) {
        Fruit fruit = fruitService.getFruitById(id);
        Map<String, Object> response = new HashMap<>();
        
        if (fruit == null) {
            response.put("success", false);
            response.put("message", "Fruit not found");
            return ResponseEntity.status(404).body(response);
        }
        
        response.put("success", true);
        response.put("message", "Fruit retrieved successfully");
        response.put("data", fruit);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchFruits(@RequestParam String q) {
        List<Fruit> results = fruitService.searchFruits(q);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Search results for: " + q);
        response.put("data", results);
        response.put("count", results.size());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> addFruit(@RequestBody Fruit fruit) {
        Fruit newFruit = fruitService.addFruit(fruit);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Fruit added successfully");
        response.put("data", newFruit);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteFruit(@PathVariable Long id) {
        Fruit fruit = fruitService.getFruitById(id);
        Map<String, Object> response = new HashMap<>();
        
        if (fruit == null) {
            response.put("success", false);
            response.put("message", "Fruit not found");
            return ResponseEntity.status(404).body(response);
        }
        
        fruitService.deleteFruit(id);
        response.put("success", true);
        response.put("message", "Fruit deleted successfully");
        return ResponseEntity.ok(response);
    }
}
