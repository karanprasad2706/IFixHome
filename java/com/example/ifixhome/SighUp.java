package com.example.ifixhome;
import static android.opengl.ETC1.isValid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SighUp extends AppCompatActivity {
    EditText edUsername, edEmail , edPassword , edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigh_up);

        edUsername = findViewById(R.id.usernameSignUp);
        edEmail = findViewById(R.id.emailSignUp);
        edPassword = findViewById(R.id.passwordSignUp);
        edConfirm = findViewById(R.id.confirmPasswordSignUp);
        btn = findViewById(R.id.registrationButton);
        tv = findViewById(R.id.signInText);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SighUp.this,Sighin.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database database = new Database(getApplicationContext(),"ifixhome",null,1);

                if(username.length() == 0 || email.length() == 0 || password.length() == 0 || confirm.length() == 0){
                    Toast.makeText(getApplicationContext(),"Please fill All the details" , Toast.LENGTH_SHORT).show();
                } else {
                    // Add email validation using regex
                    if (!isValidEmail(email)) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    } else if (password.compareTo(confirm) == 0) {
                        if (isValid(password)) {
                            database.register(username, email, password);
                            Toast.makeText(getApplicationContext(), "Register successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SighUp.this,Sighin.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must be at least 8 characters, containing letters, digits, and special symbols", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public static boolean isValid(String pass){
        int f1=0,f2=0,f3=0;
        if(pass.length()<8){
            return false;
        }else{
            for (int p = 0 ; p < pass.length(); p++){
                if(Character.isLetter(pass.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0 ; r<pass.length(); r++){
                if(Character.isDigit(pass.charAt(r))){
                    f2=1;
                }
            }
            for(int s=0; s<pass.length(); s++){
                char c = pass.charAt(s);
                if (c>=33&&c<=46 || c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }

    }
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}