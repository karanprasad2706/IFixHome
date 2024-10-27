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

public class category_irons extends AppCompatActivity implements CardAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_irons);

        // Back button
        ImageButton btn1 = findViewById(R.id.backButton5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(category_irons.this, Homepage.class));
            }
        });

        List<Product> productList = new ArrayList<>();

// Example items for speaker services
        productList.add(new Product(R.drawable.speaker_repair, "Speaker Repair Service", "Comprehensive repair solutions for all types of speaker issues including audio distortion, blown drivers, and connectivity problems.", 79.99));
        productList.add(new Product(R.drawable.speaker_installation, "Speaker Installation Service", "Professional installation of your new speakers, including placement optimization, wiring, and setup for optimal sound performance.", 49.99));
        productList.add(new Product(R.drawable.speaker_upgrade, "Speaker Upgrade Service", "Upgrade your existing speakers with high-quality components for enhanced sound quality and performance.", 99.99));
        productList.add(new Product(R.drawable.speaker_calibration, "Speaker Calibration Service", "Precision calibration of speaker settings to ensure balanced sound output and accurate audio reproduction.", 59.99));


        RecyclerView recyclerView = findViewById(R.id.ironsRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(productList);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(category_irons.this, single_product_details.class);
        intent.putExtra("image", product.getImageResId());
        intent.putExtra("title", product.getTitle());
        intent.putExtra("description", product.getDescription());
        intent.putExtra("price", product.getPrice());
        startActivity(intent);
    }
}
