package com.example.ifixhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        ImageButton btn8 = findViewById(R.id.shopcategoryButton8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMoreProductsPopup();
            }

            private void showMoreProductsPopup() {
                // Create a Dialog
                Dialog dialog = new Dialog(SearchActivity.this);

                // Set the content view to your custom layout
                dialog.setContentView(R.layout.dialog_more_products);

                // Get a reference to the TextView in the dialog
                TextView moreProductsText = dialog.findViewById(R.id.moreProductsText);

                // Customize the dialog content as needed
                moreProductsText.setText("More products are coming soon!");

                // Show the dialog
                dialog.show();
            }
        });

        //click and go to shop_tv page
        ImageButton btn ;
        btn=findViewById(R.id.shopcategoryButton1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this,shop_tv.class));
            }
        });

        //click and go to shop_washingmachine page
        ImageButton btn2 ;
        btn2=findViewById(R.id.shopcategoryButton2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this,shop_washingmachine.class));
            }
        });

        //click and go to shop_fridge page
        ImageButton btn3 ;
        btn3=findViewById(R.id.shopcategoryButton3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this,shop_fridge.class));
            }
        });

        //click and go to shop_phones page
        ImageButton btn4 ;
        btn4=findViewById(R.id.shopcategoryButton4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this,shop_phones.class));
            }
        });




        // RecyclerView for the first set of products
        List<Product> productList1 = new ArrayList<>();
        productList1.add(new Product(R.drawable.tv_samsung, "Samsung 4K Smart TV (Model: TU8000)", "Experience stunning picture quality with this 4K Smart TV from Samsung. Enjoy vibrant colors and sharp details for immersive viewing. Features smart capabilities for accessing your favorite streaming apps.", 5299.00));
        productList1.add(new Product(R.drawable.tv_sony, "Sony Bravia 8K HDR TV (Model: X800H)", "Immerse yourself in the world of 8K resolution with this HDR TV from Sony. Experience breathtaking clarity and detail with vibrant colors and deep contrast. Features Android TV for access to a wide range of apps and content.", 1999.00));
        productList1.add(new Product(R.drawable.phone1, "Samsung Galaxy M32", "6.4-inch Super AMOLED display, MediaTek Helio G80 processor, 64MP quad-camera setup, 6000mAh battery.", 1999.00));
        productList1.add(new Product(R.drawable.phone5, "Google Pixel 6a", "6.2-inch OLED display, Google Tensor chipset, 50MP dual-camera setup, 4000mAh battery.", 3299.00));
        productList1.add(new Product(R.drawable.fridge8, "Whirlpool Side-by-Side Refrigerator (Model: SBS 700)", "Capacity: 568 L, 3-star energy rating, features IntelliSense Inverter Technology and Zeolite Technology.", 7490.00));
        productList1.add(new Product(R.drawable.washing_machine6, "Haier Fully Automatic Top Load Washing Machine (Model: HWM60-12699NZP)", "Capacity: 6 kg, 700 RPM, 5-star energy rating, features Near-Zero Pressure and Double Level Spin Tub.", 1390.00));

        // Add more products as needed

        RecyclerView recyclerView1 = findViewById(R.id.latestoffers);
        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 2);
        recyclerView1.setLayoutManager(layoutManager1);


        CardAdapter cardAdapter1 = new CardAdapter(productList1);
        recyclerView1.setAdapter(cardAdapter1);

        // Set item click listener for the first RecyclerView
        cardAdapter1.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                // Handle item click for the first set of products
                Intent intent = new Intent(SearchActivity.this, single_product_details.class);
                intent.putExtra("image", product.getImageResId());
                intent.putExtra("title", product.getTitle());
                intent.putExtra("description", product.getDescription());
                intent.putExtra("price", product.getPrice());
                startActivity(intent);
            }
        });

        // RecyclerView for the second set of products
        List<Product> productList2 = new ArrayList<>();
        productList2.add(new Product(R.drawable.tablet4, "Amazon Fire HD 10 (2021)", "10.1-inch Full HD display, MediaTek Helio P60T processor, 5MP rear camera, up to 12 hours of battery life.", 1499.00));
        productList2.add(new Product(R.drawable.tablet6, "Samsung Galaxy Tab S7 FE", "12.4-inch LCD display, Qualcomm Snapdragon 750G processor, 8MP rear camera, 10090mAh battery.", 4599.00));
        productList2.add(new Product(R.drawable.phone4, "Realme 9 Pro", "6.4-inch Super AMOLED display, Qualcomm Snapdragon 695 processor, 108MP quad-camera setup, 5000mAh battery.", 1899.00));
        productList2.add(new Product(R.drawable.phone5, "Google Pixel 6a", "6.2-inch OLED display, Google Tensor chipset, 50MP dual-camera setup, 4000mAh battery.", 3299.00));
        productList2.add(new Product(R.drawable.tablet1, "Samsung Galaxy Tab A7 Lite", "8.7-inch LCD display, MediaTek Helio P22T processor, 8MP rear camera, 5100mAh battery.", 1199.00));
        productList2.add(new Product(R.drawable.fridge1, "Samsung Frost Free Double Door Refrigerator (Model: RT28T31226U/HL)", "Capacity: 253 L, 3-star energy rating, features Digital Inverter Technology and All-around Cooling.", 2099.00));
        productList2.add(new Product(R.drawable.fridge2, "LG Frost Free Double Door Refrigerator (Model: GL-T292RSOU)", "Capacity: 260 L, 4-star energy rating, features Inverter Linear Compressor and Auto Smart Connect.", 2499.00));
        productList2.add(new Product(R.drawable.fridge3, "Whirlpool Frost Free Double Door Refrigerator (Model: IF INV CNV 278 ELT)", "Capacity: 265 L, 3-star energy rating, features Intellisense Inverter Technology and MicroBlock Technology.", 2349.00));
        productList2.add(new Product(R.drawable.tv_tcl, "TCL Roku Smart TV (Model: 6-Series)", "Enjoy stunning picture quality and smart features with this Roku Smart TV from TCL. Experience vibrant colors, sharp details, and smooth motion for an immersive viewing experience. Features built-in Roku for easy access to thousands of streaming channels.", 44999.00));
        productList2.add(new Product(R.drawable.tv_vizio, "Vizio M-Series Quantum 4K TV (Model: M65Q8-H1)", "Step into the world of Quantum color with this 4K TV from Vizio. Experience stunning clarity, vivid colors, and impressive brightness for lifelike images. Features SmartCast for easy streaming and control of your favorite content.", 63749.00));
        productList2.add(new Product(R.drawable.tv_hisense, "Hisense ULED 4K Smart TV (Model: U7G)", "Get the ultimate viewing experience with this ULED Smart TV from Hisense. Enjoy vibrant colors, deep blacks, and smooth motion for immersive entertainment. Features built-in voice control and a sleek design.", 52299.00));

        RecyclerView recyclerView2 = findViewById(R.id.latestoffers2);
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView2.setLayoutManager(layoutManager2);


        CardAdapter cardAdapter2 = new CardAdapter(productList2);
        recyclerView2.setAdapter(cardAdapter2);

        // Set item click listener for the second RecyclerView
        cardAdapter2.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                // Handle item click for the second set of products
                Intent intent = new Intent(SearchActivity.this, single_product_details.class);
                intent.putExtra("image", product.getImageResId());
                intent.putExtra("title", product.getTitle());
                intent.putExtra("description", product.getDescription());
                intent.putExtra("price", product.getPrice());
                startActivity(intent);
            }
        });


        Log.d("DEBUG", "productList1 size: " + productList1.size());
        Log.d("DEBUG", "productList2 size: " + productList2.size());


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_shope);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), Homepage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_shope) {
                // Handle search action
                return true;
            } else if (item.getItemId() == R.id.bottom_cart) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
    }
}