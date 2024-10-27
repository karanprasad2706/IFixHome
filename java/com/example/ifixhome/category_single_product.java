package com.example.ifixhome;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class category_single_product extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_single_product);

        // Inside your button click listener
        ImageView addToCartButton = findViewById(R.id.eachserviceAddToCartBtn);

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the details of the clicked product
                Product clickedProduct = getProductDetailsFromYourLogic();

                // Add the product to the cart
                CartManager.addToCart(clickedProduct);

                // Optionally, show a message or update UI to indicate success
                Toast.makeText(category_single_product.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        // ... (existing code)
    }

    private Product getProductDetailsFromYourLogic() {
        // Replace this with your logic to get the details of the clicked product
        // For example, you might retrieve information from the views in your layout
        ImageView productImageView = findViewById(R.id.eachserviceIv);
        String productName = "Product Name"; // Replace with actual product name logic
        String productBrand = "Product Brand"; // Replace with actual product brand logic
        double productPrice = 19.99; // Replace with actual product price logic

        // Create a Product object with the obtained details
        return new Product(productImageView.getId(), productName, productBrand, productPrice);
    }
}
