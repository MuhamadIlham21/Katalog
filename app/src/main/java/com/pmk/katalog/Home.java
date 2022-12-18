package com.pmk.katalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class Home extends AppCompatActivity {
    RelativeLayout ln11,ln12,ln21,ln22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ln11 = findViewById(R.id.ln11);
        ln12 = findViewById(R.id.ln12);
        ln21 = findViewById(R.id.ln21);
        ln22 = findViewById(R.id.ln22);

        ln11.setOnClickListener(view -> {
            startActivity(new Intent(this, Main.class));
        });

        ln12.setOnClickListener(view -> {
            startActivity(new Intent(this, Image_picker.class));
        });

        ln21.setOnClickListener(view -> {
            startActivity(new Intent(this, Webview.class));
        });

        ln22.setOnClickListener(view -> {

        });

    }
}