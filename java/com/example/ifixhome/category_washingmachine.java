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

public class category_washingmachine extends AppCompatActivity implements CardAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_washingmachine);

        // Back button
        ImageButton btn1 = findViewById(R.id.backButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(category_washingmachine.this, Homepage.class));
            }
        });

        List<Product> productList = new ArrayList<>();

// Example items for washing machine services
        productList.add(new Product(R.drawable.washing_machine_repair, "Washing Machine Repair Service", "Comprehensive repair solutions for all types of washing machine issues including drum problems, drainage issues, and electronic malfunctions.", 299.00));
        productList.add(new Product(R.drawable.washing_machine_installation, "Washing Machine Installation & Setup", "Professional installation and setup of your new washing machine, including connection to water supply, drainage system, and configuration of settings for optimal performance.", 199.99));
        productList.add(new Product(R.drawable.washing_machine_cleaning, "Washing Machine Cleaning Service", "Thorough cleaning of your washing machine to remove dirt, detergent residue, and mold buildup, ensuring hygienic laundry and improved machine efficiency.", 99.99));
        productList.add(new Product(R.drawable.washing_machine_maintenance, "Washing Machine Maintenance Package", "Regular maintenance package including inspection, lubrication, and adjustment of components to prevent breakdowns and prolong the lifespan of your washing machine.", 149.99));
        productList.add(new Product(R.drawable.washing_machine_drum_replacement, "Washing Machine Drum Replacement", "Expert replacement of damaged or worn-out washing machine drums with high-quality replacements, ensuring smooth operation and efficient washing performance.", 399.99));

        RecyclerView recyclerView = findViewById(R.id.washingMachineRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(productList);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(category_washingmachine.this, single_product_details.class);
        intent.putExtra("image", product.getImageResId());
        intent.putExtra("title", product.getTitle());
        intent.putExtra("description", product.getDescription());
        intent.putExtra("price", product.getPrice());
        startActivity(intent);
    }
}
