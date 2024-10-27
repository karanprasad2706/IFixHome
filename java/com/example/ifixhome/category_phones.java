package com.example.ifixhome;

// category_tv.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class category_phones extends AppCompatActivity implements CardAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_phones);

        // Back button
        ImageButton btn1 = findViewById(R.id.backButton4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(category_phones.this, Homepage.class));
            }
        });

        List<Product> productList = new ArrayList<>();

// Example items for phone and tablet services
        productList.add(new Product(R.drawable.phone_repair, "Phone Repair Service", "Comprehensive repair solutions for all types of phone issues including screen replacements, battery replacements, and software troubleshooting.", 99.00));
        productList.add(new Product(R.drawable.tablet_repair, "Tablet Repair Service", "Expert repair solutions for all types of tablet problems including screen repairs, charging port replacements, and hardware diagnostics.", 149.00));
        productList.add(new Product(R.drawable.phone_battery_replacement, "Phone Battery Replacement", "Professional replacement of phone batteries with genuine batteries, ensuring extended battery life and improved performance.", 490.99));
        productList.add(new Product(R.drawable.tablet_battery_replacement, "Tablet Battery Replacement", "High-quality replacement of tablet batteries to restore battery life and ensure optimal performance.", 690.99));
        productList.add(new Product(R.drawable.phone_screen_protector_installation, "Phone Screen Protector Installation", "Professional installation of screen protectors to safeguard your phone screen from scratches, cracks, and damage.", 190.99));
        productList.add(new Product(R.drawable.tablet_screen_protector_installation, "Tablet Screen Protector Installation", "Expert installation of screen protectors to protect your tablet screen from scratches, smudges, and fingerprints.", 240.99));

        RecyclerView recyclerView = findViewById(R.id.phonesRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(productList);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(category_phones.this, single_product_details.class);
        intent.putExtra("image", product.getImageResId());
        intent.putExtra("title", product.getTitle());
        intent.putExtra("description", product.getDescription());
        intent.putExtra("price", product.getPrice());
        startActivity(intent);
    }
}
