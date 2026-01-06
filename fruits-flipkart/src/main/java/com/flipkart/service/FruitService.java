package com.flipkart.service;

import com.flipkart.model.Fruit;
import com.flipkart.model.CartItem;
import com.flipkart.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class FruitService {
    
    // In-memory storage for fruits
    private final Map<Long, Fruit> fruits = new HashMap<>();
    private final Map<Long, CartItem> cart = new HashMap<>();
    private final Map<Long, Order> orders = new HashMap<>();
    
    private Long fruitId = 1L;
    private Long cartId = 1L;
    private Long orderId = 1L;
    
    public FruitService() {
        // Initialize with sample fruits
        initFruits();
    }
    
    private void initFruits() {
        addFruit(new Fruit(1L, "Apple", 50.0, "Fresh Fruits", 100, "🍎"));
        addFruit(new Fruit(2L, "Banana", 30.0, "Fresh Fruits", 150, "🍌"));
        addFruit(new Fruit(3L, "Orange", 40.0, "Citrus", 80, "🍊"));
        addFruit(new Fruit(4L, "Mango", 100.0, "Seasonal", 50, "🥭"));
        addFruit(new Fruit(5L, "Grapes", 60.0, "Berries", 70, "🍇"));
        addFruit(new Fruit(6L, "Strawberry", 120.0, "Berries", 40, "🍓"));
        addFruit(new Fruit(7L, "Watermelon", 80.0, "Melons", 30, "🍉"));
        addFruit(new Fruit(8L, "Pineapple", 90.0, "Tropical", 60, "🍍"));
    }
    
    // Fruit operations
    public List<Fruit> getAllFruits() {
        return new ArrayList<>(fruits.values());
    }
    
    public Fruit getFruitById(Long id) {
        return fruits.get(id);
    }
    
    public Fruit addFruit(Fruit fruit) {
        fruit.setId(fruitId++);
        fruits.put(fruit.getId(), fruit);
        return fruit;
    }
    
    public Fruit updateFruit(Long id, Fruit fruit) {
        fruit.setId(id);
        fruits.put(id, fruit);
        return fruit;
    }
    
    public void deleteFruit(Long id) {
        fruits.remove(id);
    }
    
    public List<Fruit> searchFruits(String keyword) {
        List<Fruit> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        
        for (Fruit fruit : fruits.values()) {
            if (fruit.getName().toLowerCase().contains(lowerKeyword) ||
                fruit.getCategory().toLowerCase().contains(lowerKeyword)) {
                result.add(fruit);
            }
        }
        return result;
    }
    
    // Cart operations
    public CartItem addToCart(Long fruitId, Integer quantity) {
        Fruit fruit = getFruitById(fruitId);
        if (fruit == null) {
            throw new RuntimeException("Fruit not found");
        }
        
        if (fruit.getStock() < quantity) {
            throw new RuntimeException("Not enough stock");
        }
        
        // Update stock
        fruit.setStock(fruit.getStock() - quantity);
        fruits.put(fruitId, fruit);
        
        CartItem cartItem = new CartItem(cartId++, fruitId, fruit.getName(), quantity, fruit.getPrice());
        cart.put(cartItem.getId(), cartItem);
        return cartItem;
    }
    
    public List<CartItem> getCart() {
        return new ArrayList<>(cart.values());
    }
    
    public void removeFromCart(Long cartItemId) {
        CartItem item = cart.remove(cartItemId);
        if (item != null) {
            // Restore stock
            Fruit fruit = getFruitById(item.getFruitId());
            if (fruit != null) {
                fruit.setStock(fruit.getStock() + item.getQuantity());
                fruits.put(fruit.getId(), fruit);
            }
        }
    }
    
    public void clearCart() {
        // Restore all stock before clearing
        for (CartItem item : cart.values()) {
            Fruit fruit = getFruitById(item.getFruitId());
            if (fruit != null) {
                fruit.setStock(fruit.getStock() + item.getQuantity());
                fruits.put(fruit.getId(), fruit);
            }
        }
        cart.clear();
    }
    
    public Double getCartTotal() {
        return cart.values().stream()
                .mapToDouble(CartItem::getTotal)
                .sum();
    }
    
    // Order operations
    public Order placeOrder(String customerName, String address) {
        List<CartItem> cartItems = getCart();
        
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        
        Double total = getCartTotal();
        String orderNumber = "ORD" + System.currentTimeMillis();
        
        Order order = new Order(
            orderId++,
            orderNumber,
            new ArrayList<>(cartItems),
            total,
            "CONFIRMED",
            LocalDateTime.now(),
            customerName,
            address
        );
        
        orders.put(order.getId(), order);
        clearCart(); // Clear cart after order
        
        return order;
    }
    
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
    
    public Order getOrderById(Long id) {
        return orders.get(id);
    }
    
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(status);
            orders.put(orderId, order);
        }
        return order;
    }
}
