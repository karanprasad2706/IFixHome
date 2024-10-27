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

public class shop_fridge extends AppCompatActivity implements CardAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_fridge);

        // Back button
        ImageButton btn1 = findViewById(R.id.backButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(shop_fridge.this, SearchActivity.class));
            }
        });

        List<Product> productList = new ArrayList<>();

// Example items for purchasing refrigerators with realistic prices in INR
        productList.add(new Product(R.drawable.fridge1, "Samsung Frost Free Double Door Refrigerator (Model: RT28T31226U/HL)", "Capacity: 253 L, 3-star energy rating, features Digital Inverter Technology and All-around Cooling.", 2099.00));
        productList.add(new Product(R.drawable.fridge2, "LG Frost Free Double Door Refrigerator (Model: GL-T292RSOU)", "Capacity: 260 L, 4-star energy rating, features Inverter Linear Compressor and Auto Smart Connect.", 2490.00));
        productList.add(new Product(R.drawable.fridge3, "Whirlpool Frost Free Double Door Refrigerator (Model: IF INV CNV 278 ELT)", "Capacity: 265 L, 3-star energy rating, features Intellisense Inverter Technology and MicroBlock Technology.", 2340.00));
        productList.add(new Product(R.drawable.fridge4, "Haier Frost Free Double Door Refrigerator (Model: HRB-3404BS-R/HRB-3404BS-E)", "Capacity: 320 L, 3-star energy rating, features Twin Inverter Technology and Turbo Icing Technology.", 2490.00));
        productList.add(new Product(R.drawable.fridge5, "Bosch Frost Free Double Door Refrigerator (Model: KDN43VL30I)", "Capacity: 347 L, 3-star energy rating, features VitaFresh and AirFresh Filter.", 3990.00));
        productList.add(new Product(R.drawable.fridge6, "Samsung Side-by-Side Refrigerator (Model: RS74T5F01B4/TL)", "Capacity: 689 L, 3-star energy rating, features SpaceMax Technology and Twin Cooling Plus.", 4990.00));
        productList.add(new Product(R.drawable.fridge7, "LG Side-by-Side Refrigerator (Model: GC-X247CSAV)", "Capacity: 668 L, 4-star energy rating, features InstaView Door-in-Door and Linear Cooling.", 2990.00));
        productList.add(new Product(R.drawable.fridge8, "Whirlpool Side-by-Side Refrigerator (Model: SBS 700)", "Capacity: 568 L, 3-star energy rating, features IntelliSense Inverter Technology and Zeolite Technology.", 4990.00));

        RecyclerView recyclerView = findViewById(R.id.shopfridgeRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(productList);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(shop_fridge.this, single_product_details.class);
        intent.putExtra("image", product.getImageResId());
        intent.putExtra("title", product.getTitle());
        intent.putExtra("description", product.getDescription());
        intent.putExtra("price", product.getPrice());
        startActivity(intent);
    }
}