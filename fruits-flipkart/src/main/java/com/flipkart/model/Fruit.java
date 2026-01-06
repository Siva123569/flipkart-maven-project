package com.flipkart.model;

public class Fruit {
    private Long id;
    private String name;
    private Double price;
    private String category;
    private Integer stock;
    private String image;

    public Fruit() {}

    public Fruit(Long id, String name, Double price, String category, Integer stock, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.image = image;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
