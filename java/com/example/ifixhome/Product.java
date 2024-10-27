package com.example.ifixhome;

public class Product {
    private int imageResId;

    private String title;
    private String description;
    private double price;
    private int quantity;

    public Product(int imageResId,String title, String description, double price) {
        this.imageResId = imageResId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = 1;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
