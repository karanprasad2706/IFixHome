package com.example.ifixhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Forget_password extends AppCompatActivity {
    EditText existingEmail, newPassword, confirmNewPassword;
    Button updatePasswordBtn;
    Database database;
    TextView tv , loginpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        // Initialize UI components
        existingEmail = findViewById(R.id.ExistingEmail);
        newPassword = findViewById(R.id.NewPassword);
        confirmNewPassword = findViewById(R.id.ConfirmNewPassword);
        updatePasswordBtn = findViewById(R.id.UpdatePassword);
        tv = findViewById(R.id.signupText);
        loginpage = findViewById(R.id.loginpageText);

        // Initialize database instance
        database = new Database(this, "ifixhome", null, 1);

        // Set click listener for the update password button
        updatePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input
                String email = existingEmail.getText().toString().trim();
                String newPasswordText = newPassword.getText().toString().trim();
                String confirmNewPasswordText = confirmNewPassword.getText().toString().trim();

                // Validate input fields
                if (email.isEmpty() || newPasswordText.isEmpty() || confirmNewPasswordText.isEmpty()) {
                    Toast.makeText(Forget_password.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if passwords match
                if (!newPasswordText.equals(confirmNewPasswordText)) {
                    Toast.makeText(Forget_password.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Attempt to update the password in the database
                boolean passwordUpdated = database.updatePassword(email, newPasswordText);

                // Display appropriate toast message based on the outcome
                if (passwordUpdated) {
                    Toast.makeText(Forget_password.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                    // Navigate to the login page
                    startActivity(new Intent(Forget_password.this, Sighin.class));
                    finish(); // Finish this activity to prevent going back to it using the back button
                } else {
                    Toast.makeText(Forget_password.this, "Failed to update password. Email not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for the "Sign Up" text view
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forget_password.this, SighUp.class));
            }
        });

        // Set click listener for the "Login" text view
        loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forget_password.this, Sighin.class));
            }
        });
    }
}
