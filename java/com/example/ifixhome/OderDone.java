package com.example.ifixhome;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OderDone extends AppCompatActivity {

    private Button success_message_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder_done);

        success_message_btn = findViewById(R.id.success_message_btn);

        success_message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OderDone.this, Homepage.class));
            }
        });


    }
}