package com.pmk.katalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spProdi;
    EditText txtNim,txtNama,txtPassword,txtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.btnLogin);
        Button register = findViewById(R.id.btnRegister);
        txtNim = findViewById(R.id.txtNim);
        txtNama = findViewById(R.id.txtNama);
        txtPassword = findViewById(R.id.txtPassword);
        txtAlamat = findViewById(R.id.txtAlamat);
        spProdi = findViewById(R.id.spProdi);

        addValSpinner();

        login.setOnClickListener(view -> {
            startActivity(new Intent(this, Login.class));
        });

        register.setOnClickListener(view -> {
            String nim = txtNim.getText().toString();
            String nama = txtNama.getText().toString();
            String pass = txtPassword.getText().toString();
            String alamat = txtAlamat.getText().toString();
            String prodi = spProdi.getSelectedItem().toString();
//            Toast.makeText(this,
//                    "nim : "+nim+"\n"+
//                            "nama : "+nama+"\n"+
//                            "pass : "+pass+"\n"+
//                            "alamat : "+alamat+"\n"+
//                            "prodi : "+prodi+"\n",
//                    Toast.LENGTH_SHORT
//            ).show();
            ArrayList<String> data = new ArrayList<>();
            data.add(nim);
            data.add(pass);
            Intent i = new Intent(this, Login.class);
            i.putExtra("id", data);
            startActivity(i);
        });

    }

    private void addValSpinner(){
        ArrayList<String> prd = new ArrayList<>();
        prd.add("Sistem Informasi");
        prd.add("Informatika");
        prd.add("Teknik Elektro");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                prd
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_item);
        spProdi.setAdapter(adapter);

    }

}