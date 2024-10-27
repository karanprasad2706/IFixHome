package com.example.ifixhome;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static List<Product> cartItems = new ArrayList<>();

    public static void addToCart(Product product) {
        cartItems.add(product);
    }

    public static void removeFromCart(Product product) {
        cartItems.remove(product);
    }

    public static List<Product> getCartItems() {
        return cartItems;
    }

    // Add any other methods you need for managing the cart

    // For example, if you want to clear the entire cart
    public static void clearCart() {
        cartItems.clear();
    }
}
