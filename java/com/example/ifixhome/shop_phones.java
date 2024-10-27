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

public class shop_phones extends AppCompatActivity implements CardAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_phones);

        // Back button
        ImageButton btn1 = findViewById(R.id.backButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(shop_phones.this, SearchActivity.class));
            }
        });

        List<Product> productList = new ArrayList<>();

// Example items for purchasing phones with realistic prices in INR
        productList.add(new Product(R.drawable.phone1, "Samsung Galaxy M32", "6.4-inch Super AMOLED display, MediaTek Helio G80 processor, 64MP quad-camera setup, 6000mAh battery.", 1499.00));
        productList.add(new Product(R.drawable.phone2, "Xiaomi Redmi Note 11 Pro", "6.67-inch AMOLED display, MediaTek Dimensity 920 processor, 108MP quad-camera setup, 5000mAh battery.", 1799.00));
        productList.add(new Product(R.drawable.phone3, "OnePlus Nord 2", "6.43-inch Fluid AMOLED display, MediaTek Dimensity 1200-AI processor, 50MP triple-camera setup, 4500mAh battery.", 2799.00));
        productList.add(new Product(R.drawable.phone4, "Realme 9 Pro", "6.4-inch Super AMOLED display, Qualcomm Snapdragon 695 processor, 108MP quad-camera setup, 5000mAh battery.", 1899.00));
        productList.add(new Product(R.drawable.phone5, "Google Pixel 6a", "6.2-inch OLED display, Google Tensor chipset, 50MP dual-camera setup, 4000mAh battery.", 2999.00));
        productList.add(new Product(R.drawable.phone6, "iPhone SE (2022)", "4.7-inch Retina HD display, Apple A15 Bionic chip, 12MP single-camera setup, 1821mAh battery.", 2900.00));

// Example items for purchasing tablets with realistic prices in INR
        productList.add(new Product(R.drawable.tablet1, "Samsung Galaxy Tab A7 Lite", "8.7-inch LCD display, MediaTek Helio P22T processor, 8MP rear camera, 5100mAh battery.", 1999.00));
        productList.add(new Product(R.drawable.tablet2, "Apple iPad (9th generation)", "10.2-inch Retina display, Apple A13 Bionic chip, 8MP rear camera, 32GB storage.", 3900.00));
        productList.add(new Product(R.drawable.tablet3, "Huawei MatePad T10s", "10.1-inch Full HD display, Kirin 710A processor, 5MP rear camera, 5100mAh battery.", 1990.00));
        productList.add(new Product(R.drawable.tablet4, "Amazon Fire HD 10 (2021)", "10.1-inch Full HD display, MediaTek Helio P60T processor, 5MP rear camera, up to 12 hours of battery life.", 1499.00));
        productList.add(new Product(R.drawable.tablet6, "Samsung Galaxy Tab S7 FE", "12.4-inch LCD display, Qualcomm Snapdragon 750G processor, 8MP rear camera, 10090mAh battery.", 4599.00));

        RecyclerView recyclerView = findViewById(R.id.shopphonesRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(productList);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(shop_phones.this, single_product_details.class);
        intent.putExtra("image", product.getImageResId());
        intent.putExtra("title", product.getTitle());
        intent.putExtra("description", product.getDescription());
        intent.putExtra("price", product.getPrice());
        startActivity(intent);
    }
}