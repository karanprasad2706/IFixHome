package com.example.ifixhome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating 'users' table
        String createUserTableQuery = "CREATE TABLE users (" +
                "username TEXT," +
                "email TEXT," +
                "password TEXT)";
        db.execSQL(createUserTableQuery);

        // Creating 'profile' table
        String createProfileTableQuery = "CREATE TABLE profile (" +
                "username TEXT," +
                "image BLOB)";
        db.execSQL(createProfileTableQuery);

        // Creating 'user_orders' table
        String createUserOrdersTableQuery = "CREATE TABLE user_orders (" +
                "username TEXT," +
                "name TEXT," +
                "address TEXT," +
                "contact TEXT," +
                "pincode TEXT," +
                "product_title TEXT," +
                "price REAL," +
                "total_price REAL," +
                "quantity INTEGER)";
        db.execSQL(createUserOrdersTableQuery);

        String createReportedProblems = "CREATE TABLE problems (" +
                "username TEXT," +
                "name TEXT ," +
                "email TEXT," +
                "problem TEXT)";
        db.execSQL(createReportedProblems);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade if needed
    }

    public boolean updatePassword(String email, String newPassword) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", newPassword);

        // Update the password associated with the given email
        int rowsAffected = database.update("users", values, "email = ?", new String[]{email});

        // Close the database connection
        database.close();

        // Return true if the update was successful, false otherwise
        return rowsAffected > 0;
    }

    // Other methods for registration, login, profile management, and user orders...

    // Example registration method
    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        SQLiteDatabase database = getWritableDatabase();
        database.insert("users", null, cv);
        database.close();
    }

    // Example login method
    public int login(String username, String password) {
        int result = 0;
        String[] str = {username, password};

        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        c.close();
        database.close();
        return result;
    }

    //Profile Section
//    public void addProfile(String username, byte[] image) {
//        ContentValues cv = new ContentValues();
//        cv.put("username", username);
//        cv.put("image", image);
//        SQLiteDatabase database = getWritableDatabase();
//        database.insert("profile", null, cv);
//        database.close();
//    }
    // New method to get profile image by username
    public byte[] getProfileImage(String username) {
        byte[] image = null;
        SQLiteDatabase database = null;
        Cursor cursor = null;

        try {
            database = getReadableDatabase();
            String[] selectionArgs = {username};
            cursor = database.rawQuery("SELECT image FROM profile WHERE username = ?", selectionArgs);
            if (cursor.moveToFirst()) {
                image = cursor.getBlob(0);
            }
        } catch (Exception e) {
            // Handle exceptions, log or show error message
            e.printStackTrace();
        } finally {
            // Close cursor and database
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
        return image;
    }

    public boolean addOrUpdateProfile(String username, byte[] image) {
        SQLiteDatabase database = null;
        Cursor cursor = null;
        boolean updatedSuccessfully = false;

        try {
            database = getWritableDatabase();
            cursor = database.rawQuery("SELECT * FROM profile WHERE username=?", new String[]{username});
            if (cursor.moveToFirst()) {
                ContentValues cv = new ContentValues();
                cv.put("image", image);
                updatedSuccessfully = database.update("profile", cv, "username=?", new String[]{username}) > 0;
            } else {
                // If no profile image exists, insert a new record
                ContentValues cv = new ContentValues();
                cv.put("username", username);
                cv.put("image", image);
                updatedSuccessfully = database.insert("profile", null, cv) != -1;
            }
        } catch (Exception e) {
            // Handle exceptions, log or show error message
            e.printStackTrace();
        } finally {
            // Close cursor and database
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
        return updatedSuccessfully;
    }

    public void saveUserOrder(String username, String name, String address, String contact, String pincode,
                              String productTitle , double price, double totalPrice, int quantity) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("name", name);
        values.put("address", address);
        values.put("contact", contact);
        values.put("pincode", pincode);
        values.put("product_title", productTitle);
        values.put("price",price);
        values.put("total_price", totalPrice);
        values.put("quantity", quantity);
        database.insert("user_orders", null, values);
        database.close();
    }

    public boolean checkContactExists(String contact) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String[] projection = {"contact"};
            String selection = "contact = ?";
            String[] selectionArgs = {contact};

            cursor = db.query(
                    "user_orders", // Table name
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            return cursor != null && cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    public boolean insertData(String username, String name, String email, String problem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username); // Add username
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("problem", problem);
        long result = db.insert("Problems", null, contentValues);
        db.close();

        return result != -1;
    }


}
