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

public class shop_tv extends AppCompatActivity implements CardAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_tv);

        // Back button
        ImageButton btn1 = findViewById(R.id.backButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(shop_tv.this, SearchActivity.class));
            }
        });

        List<Product> productList = new ArrayList<>();
// Example items for purchasing TVs with realistic prices in INR
        productList.add(new Product(R.drawable.tv_samsung, "Samsung 4K Smart TV (Model: TU8000)", "Experience stunning picture quality with this 4K Smart TV from Samsung. Enjoy vibrant colors and sharp details for immersive viewing. Features smart capabilities for accessing your favorite streaming apps.", 5299.00));
        productList.add(new Product(R.drawable.tv_lg, "LG OLED TV (Model: C1)", "Elevate your entertainment experience with this OLED TV from LG. Enjoy deep blacks, rich colors, and stunning contrast for lifelike images. Features advanced AI picture and sound enhancements for immersive viewing.", 1299.00));
        productList.add(new Product(R.drawable.tv_sony, "Sony Bravia 8K HDR TV (Model: X800H)", "Immerse yourself in the world of 8K resolution with this HDR TV from Sony. Experience breathtaking clarity and detail with vibrant colors and deep contrast. Features Android TV for access to a wide range of apps and content.", 1799.00));
        productList.add(new Product(R.drawable.tv_tcl, "TCL Roku Smart TV (Model: 6-Series)", "Enjoy stunning picture quality and smart features with this Roku Smart TV from TCL. Experience vibrant colors, sharp details, and smooth motion for an immersive viewing experience. Features built-in Roku for easy access to thousands of streaming channels.", 4499.00));
        productList.add(new Product(R.drawable.tv_vizio, "Vizio M-Series Quantum 4K TV (Model: M65Q8-H1)", "Step into the world of Quantum color with this 4K TV from Vizio. Experience stunning clarity, vivid colors, and impressive brightness for lifelike images. Features SmartCast for easy streaming and control of your favorite content.", 3749.00));
        productList.add(new Product(R.drawable.tv_hisense, "Hisense ULED 4K Smart TV (Model: U7G)", "Get the ultimate viewing experience with this ULED Smart TV from Hisense. Enjoy vibrant colors, deep blacks, and smooth motion for immersive entertainment. Features built-in voice control and a sleek design.", 5299.00));
        productList.add(new Product(R.drawable.tv_panasonic, "Panasonic 4K OLED TV (Model: HZ2000)", "Experience cinematic picture quality with this 4K OLED TV from Panasonic. Enjoy deep blacks, accurate colors, and precise details for a true-to-life viewing experience. Features Dolby Atmos for immersive sound.", 1749.00));
        productList.add(new Product(R.drawable.tv_philips, "Philips Ambilight 4K TV (Model: 6700 Series)", "Enhance your viewing experience with Ambilight technology on this 4K TV from Philips. Enjoy immersive lighting effects that extend beyond the screen. Features HDR for vibrant colors and sharp details.", 7799.00));

        RecyclerView recyclerView = findViewById(R.id.shoptvRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(productList);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(shop_tv.this, single_product_details.class);
        intent.putExtra("image", product.getImageResId());
        intent.putExtra("title", product.getTitle());
        intent.putExtra("description", product.getDescription());
        intent.putExtra("price", product.getPrice());
        startActivity(intent);
    }
}