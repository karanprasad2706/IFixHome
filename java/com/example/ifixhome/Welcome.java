package com.example.ifixhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    private Button signInButton;
    private Button signUpButton;  // Add a reference for the new button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);  // Initialize the new button

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignIn();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignIn();  // Call the method for button1
            }
        });
    }

    public void openSignIn() {
        Intent intent = new Intent(this, Sighin.class);
        startActivity(intent);
    }

    public void openButton1() {
        // Replace Button1Activity.class with the actual class representing your Button1 activity
        Intent intent = new Intent(this,  Sighin.class);
        startActivity(intent);
    }
}
