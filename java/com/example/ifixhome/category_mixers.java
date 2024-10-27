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

public class category_mixers extends AppCompatActivity implements CardAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_mixers);

        // Back button
        ImageButton btn1 = findViewById(R.id.backButton7);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(category_mixers.this, Homepage.class));
            }
        });

        List<Product> productList = new ArrayList<>();

// Example items for mixer services
        productList.add(new Product(R.drawable.mixer_repair, "Mixer Repair Service", "Comprehensive repair solutions for all types of mixer issues including motor problems, blade replacements, and electrical malfunctions.", 69.99));
        productList.add(new Product(R.drawable.mixer_cleaning, "Mixer Cleaning Service", "Thorough cleaning of your mixer to remove food residues, stains, and odors, ensuring hygiene and efficient operation.", 39.99));
        productList.add(new Product(R.drawable.mixer_blade_sharpening, "Mixer Blade Sharpening", "Professional sharpening of mixer blades to ensure precise cutting and blending performance.", 29.99));
        productList.add(new Product(R.drawable.mixer_motor_replacement, "Mixer Motor Replacement", "Expert replacement of worn-out or faulty mixer motors with high-quality replacements for optimal performance.", 129.99));

        RecyclerView recyclerView = findViewById(R.id.mixersRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(productList);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(category_mixers.this, single_product_details.class);
        intent.putExtra("image", product.getImageResId());
        intent.putExtra("title", product.getTitle());
        intent.putExtra("description", product.getDescription());
        intent.putExtra("price", product.getPrice());
        startActivity(intent);
    }
}
