package com.flipkart.model;

public class CartItem {
    private Long id;
    private Long fruitId;
    private String fruitName;
    private Integer quantity;
    private Double price;

    public CartItem() {}

    public CartItem(Long id, Long fruitId, String fruitName, Integer quantity, Double price) {
        this.id = id;
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getFruitId() { return fruitId; }
    public void setFruitId(Long fruitId) { this.fruitId = fruitId; }
    
    public String getFruitName() { return fruitName; }
    public void setFruitName(String fruitName) { this.fruitName = fruitName; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public Double getTotal() {
        return price * quantity;
    }
}
