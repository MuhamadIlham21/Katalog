package com.pmk.katalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText txtNim = findViewById(R.id.txtNim);
        EditText txtPassword = findViewById(R.id.txtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            ArrayList<String> data = (ArrayList<String>)
                    getIntent().getSerializableExtra("id");
            String nim = data.get(0);
            String pass = data.get(1);

            Log.d("cek", nim+" "+pass);

            if (txtNim.getText().toString().equals(nim) &&
                    txtPassword.getText().toString().equals(pass)){
                startActivity(new Intent(this, Main.class));
            }else{
                Toast.makeText(this, "Username atau Password salah!",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}