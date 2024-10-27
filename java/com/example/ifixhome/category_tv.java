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

public class category_tv extends AppCompatActivity implements CardAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_tv);

        // Back button
        ImageButton btn1 = findViewById(R.id.backButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(category_tv.this, Homepage.class));
            }
        });

        List<Product> productList = new ArrayList<>();

        // Example items
        productList.add(new Product(R.drawable.tv_repair1, "TV Repair Service", "Comprehensive repair solutions for all types of TV issues including screen problems, audio malfunctions, and connectivity issues.", 399.00));
        productList.add(new Product(R.drawable.tv_repair2, "TV Installation & Setup", "Professional installation and setup of your new TV, including wall mounting, cable connections, and configuration of settings for optimal viewing experience.", 299.99));
        productList.add(new Product(R.drawable.tv_repair3, "TV Screen Replacement", "Expert replacement of damaged or faulty TV screens with high-quality replacements, ensuring crystal-clear viewing without any distortions.", 459.99));
        productList.add(new Product(R.drawable.tv_repair4, "TV Cable & Connection Setup", "Professional setup of TV cables and connections to ensure seamless integration with external devices such as cable boxes, gaming consoles, and streaming devices.", 149.99));
        productList.add(new Product(R.drawable.tv_repair5, "TV Cable Setup", "Professional setup of TV cables and connections to ensure seamless integration with external devices such as cable boxes, gaming consoles, and streaming devices.", 249.99));


        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        CardAdapter cardAdapter = new CardAdapter(productList);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(category_tv.this, single_product_details.class);
        intent.putExtra("image", product.getImageResId());
        intent.putExtra("title", product.getTitle());
        intent.putExtra("description", product.getDescription());
        intent.putExtra("price", product.getPrice());
        startActivity(intent);
    }
}
