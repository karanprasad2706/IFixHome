package com.example.ifixhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private CartItemAdapter cartItemAdapter;
    TextView totalPriceTextView;
    Button OdersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_cart);

        ImageButton back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, Homepage.class));
            }
        });

        OdersButton = findViewById(R.id.OdersButton);
        OdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to the next activity
                Intent intent = new Intent(CartActivity.this, OrderStatusActivity.class);
                startActivity(intent);
            }
        });

        Button checkout = findViewById(R.id.checkoutButton);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Product> cartItems = cartItemAdapter.getCartItemList();

                // Check if there is at least one item in the cart
                if (!cartItems.isEmpty()) {
                    // Get the title from the first item in the cart
                    String titlesAndQuantities = getTitlesAndQuantitiesInCartAsString();
                    String prices = getPricesInCartAsString();

                    // Get the total price from the TextView
                    String totalPriceString = totalPriceTextView.getText().toString();

                    int firstItemQuantity = cartItemAdapter.getQuantity(0);

                    double firstItemPrice = cartItemAdapter.getPrice(0);
                    // Extract the numeric part from the string
                    String totalPriceValue = totalPriceString.substring(totalPriceString.indexOf('₹') + 1);

                    // Convert the total price to a double
                    double totalPrice = Double.parseDouble(totalPriceValue);

                    // Get the quantity of the first item in the cart
                    int quantity = cartItemAdapter.getQuantityAtPosition(0);

                    // Create an Intent to start the checkout_page activity
                    Intent intent = new Intent(CartActivity.this, checkout_page.class);
                    // Pass the title, total price, and quantity as extras
                    intent.putExtra("titlesAndQuantities", titlesAndQuantities);
                    intent.putExtra("prices", prices);
                    intent.putExtra("totalPrice", totalPrice);
                    intent.putExtra("quantity", quantity);

                    // Start the next activity
                    startActivity(intent);
                } else {
                    // Handle the case when the cart is empty
                    Toast.makeText(CartActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            // Handle bottom navigation item clicks
            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), Homepage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_shope) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_cart) {
                // Already in cart activity
                return true;
            } else if (item.getItemId() == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });

        // Set up RecyclerView
        setupRecyclerView();
    }

    // Method to get titles and quantities of items in the cart as a formatted string
    private String getTitlesAndQuantitiesInCartAsString() {
        List<Product> cartItems = CartManager.getCartItems();
        StringBuilder stringBuilder = new StringBuilder();

        for (Product product : cartItems) {
            // Append title and quantity in the specified format
            stringBuilder.append(product.getTitle())
                    .append(" - ")
                    .append(product.getQuantity())
                    .append(" | ");
        }

        // Remove the trailing " | " if there are any items in the cart
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 3);
        }

        // Return the formatted string
        return stringBuilder.toString();
    }

    // Method to get prices of items in the cart as a formatted string
    private String getPricesInCartAsString() {
        List<Product> cartItems = CartManager.getCartItems();
        StringBuilder stringBuilder = new StringBuilder();

        for (Product product : cartItems) {
            // Append price in the specified format
            stringBuilder.append(product.getPrice())
                    .append(" | ");
        }

        // Remove the trailing " | " if there are any items in the cart
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 3);
        }

        // Return the formatted string
        return stringBuilder.toString();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.cartRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Get the cart items from CartManager
        List<Product> cartItems = CartManager.getCartItems();

        // Create and set the adapter, pass CartActivity as a reference
        cartItemAdapter = new CartItemAdapter(cartItems, this);

        // Set item click listener for removing items from the cart
        cartItemAdapter.setOnItemClickListener(new CartItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                // Remove the item from the cart
                CartManager.removeFromCart(product);

                // Update the total price and display
                double totalPrice = cartItemAdapter.updateTotalPrice();
                displayTotalPrice(totalPrice);
            }
        });

        recyclerView.setAdapter(cartItemAdapter);

        // Update the total price after setting up the RecyclerView
        double totalPrice = cartItemAdapter.updateTotalPrice();
        displayTotalPrice(totalPrice);
    }

    public void displayTotalPrice(double totalPrice) {
        // Display the total price in a TextView
        totalPriceTextView.setText("Total Price: ₹" + totalPrice);
    }
}

