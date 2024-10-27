package com.example.ifixhome;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log; // Import Log for logging
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONException;
import org.json.JSONObject;

public class checkout_page extends AppCompatActivity implements PaymentResultListener {

    EditText edname, edaddress, edpincode, edcontact;
    Button BtnBooking;
    TextView tv;

    private SharedPreferences sharedPreferences;
    private String username;

    private static final String TAG = "checkout_page"; // Logging tag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page);

        Intent intent = getIntent();
        String titlesAndQuantities = intent.getStringExtra("titlesAndQuantities");
        double totalPrice = intent.getDoubleExtra("totalPrice", 0.0);
        int quantity = intent.getIntExtra("quantity", 0);
        String prices = intent.getStringExtra("prices");

        sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");

        edname = findViewById(R.id.LBfullname);
        edaddress = findViewById(R.id.LBaddress);
        edcontact = findViewById(R.id.LBcontactnumber);
        edpincode = findViewById(R.id.LBpincode);
        BtnBooking = findViewById(R.id.ButtonLBbook);
        tv = findViewById(R.id.price);

        tv.setText(totalPrice + "/-");

        BtnBooking.setOnClickListener(view -> {
            // Check if EditText fields are not empty
            if (TextUtils.isEmpty(edname.getText()) || TextUtils.isEmpty(edaddress.getText())
                    || TextUtils.isEmpty(edcontact.getText()) || TextUtils.isEmpty(edpincode.getText())) {
                Toast.makeText(this, "Please fill in all details", Toast.LENGTH_SHORT).show();
                return;  // Stop further execution
            }

            // Check if the total price is greater than 0
            if (totalPrice <= 0) {
                Toast.makeText(this, "Invalid total price", Toast.LENGTH_SHORT).show();
                return;  // Stop further execution
            }

            initializeRazorpay(totalPrice);
        });
    }

    private void initializeRazorpay(double totalPrice) {
        int amount = (int) Math.round(totalPrice * 100);
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_CpzEo52xfK0QZv");
        checkout.setImage(R.drawable.app_logo);

        JSONObject object = new JSONObject();
        try {
            object.put("name", "I FIXHOME");
            object.put("description", "Test payment");
            object.put("theme.color", "");
            object.put("currency", "INR");
            object.put("amount", amount);
            object.put("prefill.contact", edcontact.getText().toString());
            object.put("prefill.email", "chaitanyamunje@gmail.com");

            // Open Razorpay checkout activity
            checkout.open(checkout_page.this, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveUserDataToDatabase(String username, String name, String address, String contact, String pincode, String title, String price, double totalPrice, int quantity) {
        // Instantiate the Database class
        Database dbHelper = new Database(this, "ifixhome", null, 1);

        // Get a writable database
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // Use a transaction for database operations
        database.beginTransaction();
        try {
            // Create ContentValues to store user data
            ContentValues values = new ContentValues();
            values.put("username", username);
            values.put("name", name);
            values.put("address", address);
            values.put("contact", contact);
            values.put("pincode", pincode);
            values.put("product_title", title);
            values.put("price", price);
            values.put("total_price", totalPrice);
            values.put("quantity", quantity);

            // Insert the user data into the "user_orders" table
            database.insert("user_orders", null, values);

            // Set the transaction as successful
            database.setTransactionSuccessful();
        } finally {
            // End the transaction
            database.endTransaction();
        }

        // Close the database connection
        dbHelper.close();
    }

    @Override
    public void onPaymentSuccess(String s) {
        // This method is called on payment success.
        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();

        // Clear the cart after payment success
        clearCart();

        String name = edname.getText().toString();
        String address = edaddress.getText().toString();
        String contact = edcontact.getText().toString();
        String pincode = edpincode.getText().toString();

        Intent intent = getIntent();
        String titlesAndQuantities = intent.getStringExtra("titlesAndQuantities");
        double totalPrice = intent.getDoubleExtra("totalPrice", 0.0);
        int quantity = intent.getIntExtra("quantity", 0);
        String prices = intent.getStringExtra("prices");

        saveUserDataToDatabase(username, name, address, contact, pincode, titlesAndQuantities, prices, totalPrice, quantity);

        // Display a confirmation message
        Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        // On payment failed.
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Payment failed: " + s); // Log the error for debugging
    }

    private void clearCart() {
        // Clear the cart logic goes here
        // For example, if you're using a CartManager class to manage the cart, you can call its clearCart() method
        CartManager.clearCart();
    }
}

