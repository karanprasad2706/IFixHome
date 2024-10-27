package com.example.ifixhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

public class shop_washingmachine extends AppCompatActivity implements CardAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_washingmachine);

        // Back button
        ImageButton btn1 = findViewById(R.id.backButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(shop_washingmachine.this, SearchActivity.class));
            }
        });

        List<Product> productList = new ArrayList<>();

// Example items for purchasing washing machines with realistic prices in INR
        productList.add(new Product(R.drawable.washing_machine1, "Samsung Fully Automatic Front Load Washing Machine (Model: WW60R20GLMA/TL)", "Capacity: 6 kg, 1200 RPM, 5-star energy rating, features Quick Wash and Digital Inverter Technology.", 2099.00));
        productList.add(new Product(R.drawable.washing_machine2, "LG Fully Automatic Top Load Washing Machine (Model: T65SKSF4Z)", "Capacity: 6.5 kg, 780 RPM, 5-star energy rating, features Smart Inverter Technology and TurboDrum.", 1490.00));
        productList.add(new Product(R.drawable.washing_machine3, "Whirlpool Fully Automatic Top Load Washing Machine (Model: Stainwash Ultra SD, 6.5)", "Capacity: 6.5 kg, 740 RPM, 5-star energy rating, features Hard Water Wash and Spa Wash System.", 1490.00));
        productList.add(new Product(R.drawable.washing_machine4, "Bosch Fully Automatic Front Load Washing Machine (Model: WAB16060IN)", "Capacity: 6 kg, 800 RPM, 5-star energy rating, features Super Quick 15 and ActiveWater.", 2090.00));
        productList.add(new Product(R.drawable.washing_machine5, "IFB Fully Automatic Front Load Washing Machine (Model: Diva Aqua VX, 6 kg)", "Capacity: 6 kg, 800 RPM, 5-star energy rating, features Aqua Energie and Crescent Moon Drum.", 2099.00));
        productList.add(new Product(R.drawable.washing_machine6, "Haier Fully Automatic Top Load Washing Machine (Model: HWM60-12699NZP)", "Capacity: 6 kg, 700 RPM, 5-star energy rating, features Near-Zero Pressure and Double Level Spin Tub.", 1340.00));

        RecyclerView recyclerView = findViewById(R.id.shopwashingmachineRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(productList);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(shop_washingmachine.this, single_product_details.class);
        intent.putExtra("image", product.getImageResId());
        intent.putExtra("title", product.getTitle());
        intent.putExtra("description", product.getDescription());
        intent.putExtra("price", product.getPrice());
        startActivity(intent);
    }
}