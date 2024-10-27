package com.example.ifixhome;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sighin extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btn;
    TextView tv , forgetp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sighin);

        edUsername = findViewById(R.id.username);
        edPassword = findViewById(R.id.password);
        btn = findViewById(R.id.loginbutton);
        tv = findViewById(R.id.signupText);
        forgetp = findViewById(R.id.forgetpasswordText);

        forgetp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sighin.this,Forget_password.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                Database database = new Database(getApplicationContext(),"ifixhome",null,1);
                if(username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill All the details" , Toast.LENGTH_SHORT).show();
                }else {
                    if (database.login(username,password)==1){
                        Toast.makeText(getApplicationContext(),"Login Success" , Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs" , Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.apply();
                        startActivity(new Intent(Sighin.this,Homepage.class));
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Invalid Username and Password" , Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sighin.this,SighUp.class));
            }
        });
    }
}