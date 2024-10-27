package com.example.ifixhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {

    private ImageSwitcher imageSwitcher;
    private int[] images = {R.drawable.imageslider1, R.drawable.imageslider2, R.drawable.imageslider3, R.drawable.imageslider4, R.drawable.imageslider5};
    private int currentIndex = 0;
    private Handler handler;
    private final int SWITCH_INTERVAL = 2000;

    private RecyclerView featuredRecycler;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        ImageButton btn8 = findViewById(R.id.categoryButton8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMoreProductsPopup();
            }

            private void showMoreProductsPopup() {
                // Create a Dialog
                Dialog dialog = new Dialog(Homepage.this);

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




        //click and go to category_tv page
        ImageButton btn ;
        btn=findViewById(R.id.categoryButton1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this,category_tv.class));
            }
        });

        //click and go to category_washingmachine page
        ImageButton btn2 ;
        btn2=findViewById(R.id.categoryButton2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this,category_washingmachine.class));
            }
        });

        //click and go to category_fridge page
        ImageButton btn3 ;
        btn3=findViewById(R.id.categoryButton3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this,category_fridge.class));
            }
        });

        //click and go to category_phones page
        ImageButton btn4 ;
        btn4=findViewById(R.id.categoryButton4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this,category_phones.class));
            }
        });

        //click and go to category_Irons page
        ImageButton btn5 ;
        btn5=findViewById(R.id.categoryButton5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this,category_irons.class));
            }
        });

        //click and go to category_Speakers page
        ImageButton btn6 ;
        btn6=findViewById(R.id.categoryButton6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this,category_speakers.class));
            }
        });

        //click and go to category_Mixers page
        ImageButton btn7 ;
        btn7=findViewById(R.id.categoryButton7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this,category_mixers.class));
            }
        });





        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), Homepage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_shope) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
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

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        Toast.makeText(getApplicationContext(), "Welcome " + username, Toast.LENGTH_SHORT).show();

        // Image Switcher Initialization
        imageSwitcher = findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });

        // Handle Image Switching
        imageSwitcher.setImageResource(images[currentIndex]);
        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchImage();
            }
        });

        // Featured RecyclerView Initialization
        featuredRecycler = findViewById(R.id.featured_recycler);
        featuredRecycler();

        // Automatic Image Switching
        handler = new Handler();
        handler.postDelayed(imageSwitchRunnable, SWITCH_INTERVAL);
    }


    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.comment_emogi, "Rohit Niwangune's", "It's a very nice app and it's very flexible to use."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.comment_emogi, "Lalu Sah's", "It's a very nice app and it's very flexible to use."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.comment_emogi, "Abhishek Darji's", "It's a very nice app and it's very flexible to use."));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);
    }

    private void switchImage() {
        currentIndex = (currentIndex + 1) % images.length;
        imageSwitcher.setImageResource(images[currentIndex]);

        // Restart the timer for the next automatic switch
        handler.removeCallbacks(imageSwitchRunnable);
        handler.postDelayed(imageSwitchRunnable, SWITCH_INTERVAL);
    }

    private Runnable imageSwitchRunnable = new Runnable() {
        @Override
        public void run() {
            switchImage();
        }
    };
}
