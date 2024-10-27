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

public class category_fridge extends AppCompatActivity implements CardAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_fridge);

        // Back button
        ImageButton btn1 = findViewById(R.id.backButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(category_fridge.this, Homepage.class));
            }
        });

        List<Product> productList = new ArrayList<>();

// Example items for refrigerator services
        productList.add(new Product(R.drawable.fridge_repair, "Refrigerator Repair Service", "Comprehensive repair solutions for all types of refrigerator issues including cooling problems, faulty compressors, and defrosting malfunctions.", 349.00));
        productList.add(new Product(R.drawable.fridge_installation, "Refrigerator Installation & Setup", "Professional installation and setup of your new refrigerator, including leveling, connection to power supply, and configuration of temperature settings for optimal cooling.", 199.99));
        productList.add(new Product(R.drawable.fridge_cleaning, "Refrigerator Cleaning Service", "Thorough cleaning of your refrigerator interior and exterior to remove food residues, spills, and odors, ensuring hygienic storage and improved cooling efficiency.", 79.99));
        productList.add(new Product(R.drawable.fridge_maintenance, "Refrigerator Maintenance Package", "Regular maintenance package including inspection, cleaning of condenser coils, and replacement of worn-out components to prevent breakdowns and ensure optimal performance.", 129.99));
        productList.add(new Product(R.drawable.fridge_door_seal_replacement, "Refrigerator Door Seal Replacement", "Expert replacement of damaged or worn-out refrigerator door seals with high-quality replacements, ensuring proper sealing and energy efficiency.", 149.99));

        RecyclerView recyclerView = findViewById(R.id.fridgeRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(productList);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(category_fridge.this, single_product_details.class);
        intent.putExtra("image", product.getImageResId());
        intent.putExtra("title", product.getTitle());
        intent.putExtra("description", product.getDescription());
        intent.putExtra("price", product.getPrice());
        startActivity(intent);
    }
}
