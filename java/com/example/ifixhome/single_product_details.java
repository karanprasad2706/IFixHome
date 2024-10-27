package com.example.ifixhome;

// single_product_details.java
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.appcompat.widget.AppCompatButton;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class single_product_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_details);

        ImageButton backButton = findViewById(R.id.backButton);
        ImageView imageView = findViewById(R.id.detailActivityShoeIV);
        TextView titleTextView = findViewById(R.id.detailActivityShoeNameTv);
        TextView descriptionTextView = findViewById(R.id.detailActivityShoeBrandNameTv);
        TextView priceTextView = findViewById(R.id.detailActivityShoePriceTv);
        AppCompatButton addToCartButton = findViewById(R.id.detailActivityAddToCartBtn);

        // Retrieve data from the intent
        int imageResource = getIntent().getIntExtra("image", 0);
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        double price = getIntent().getDoubleExtra("price", 0.0);

        // Set data to the views
        imageView.setImageResource(imageResource);
        titleTextView.setText(title);
        descriptionTextView.setText(description);

        // Format the price in Rupees
        Locale locale = new Locale("en", "IN"); // India locale
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        currencyFormatter.setCurrency(Currency.getInstance("INR"));

        priceTextView.setText(currencyFormatter.format(price));

        // Set click listener for the backButton
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Use NavUtils to navigate up one level in the application structure
                NavUtils.navigateUpFromSameTask(single_product_details.this);
            }
        });

        // Set click listener for the addToCartButton
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the details of the clicked product (replace with your logic)
                Product clickedProduct = getProductDetails();

                // Add the product to the cart
                CartManager.addToCart(clickedProduct);

                // Optionally, show a message or update UI to indicate success
                Toast.makeText(single_product_details.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Replace this with your logic to get product details
    private Product getProductDetails() {
        // Sample data, replace with your logic
        int imageResId = getIntent().getIntExtra("image", 0);
        String productName = getIntent().getStringExtra("title");
        String productBrand = getIntent().getStringExtra("description");
        double productPrice = getIntent().getDoubleExtra("price", 0.0);

        return new Product(imageResId, productName, productBrand, productPrice);
    }
}

