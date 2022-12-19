package com.pmk.katalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pmk.katalog.Adapter.Adapter_Barang;
import com.pmk.katalog.Model.m_barang;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
    static RecyclerView rvBarang;
    static ArrayList<m_barang> data;
    @SuppressLint("StaticFieldLeak")
    static Adapter_Barang adp;
    @SuppressLint("StaticFieldLeak")
    static DatabaseHelper db;
    @SuppressLint("StaticFieldLeak")
    static LinearLayout lnInput;
    TextView tvClose;
    Button btnTambah, btnUbah;
    FloatingActionButton btnAdd;
    TextView txtNama,txtHarga,txtStok;
    Spinner spSatuan;
    ArrayList<String> satuan;
    String id,nama,harga,stok,st;

//    public  Main(){}
//
//    public Main(String id,String nama, String harga, String stok, String st){
//        this.id = id;
//        this.nama = nama;
//        this.harga = harga;
//        this.stok = stok;
//        this.st = st;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rvBarang = findViewById(R.id.rvBarang);
        lnInput = findViewById(R.id.lnInput);
        btnTambah = findViewById(R.id.btnTambah);
        btnUbah = findViewById(R.id.btnUbah);
        btnAdd = findViewById(R.id.btnAdd);
        tvClose = findViewById(R.id.tvClose);
        txtNama = findViewById(R.id.txtNama);
        txtHarga = findViewById(R.id.txtHarga);
        txtStok = findViewById(R.id.txtStok);
        spSatuan = findViewById(R.id.spSatuan);
        db = new DatabaseHelper(this);

        fetchData();
        getSatuan();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("pass"));

        btnAdd.setOnClickListener(view -> {
            lnInput.setVisibility(View.VISIBLE);
            btnUbah.setVisibility(View.GONE);
        });

        btnTambah.setOnClickListener(view -> {
            db.insertBarang(new m_barang(
                    txtNama.getText().toString(),
                    txtHarga.getText().toString(),
                    txtStok.getText().toString(),
                    spSatuan.getSelectedItem().toString()
            ));
            clear();
            lnInput.setVisibility(View.GONE);
            fetchData();
        });

        btnUbah.setOnClickListener(view -> {
            db.updateBarang(new m_barang(
                    id,
                    txtNama.getText().toString(),
                    txtHarga.getText().toString(),
                    txtStok.getText().toString(),
                    spSatuan.getSelectedItem().toString()
            ));
            clear();
            lnInput.setVisibility(View.GONE);
            fetchData();
        });

        tvClose.setOnClickListener(view -> {
            lnInput.setVisibility(View.GONE);
        });

    }

    private void clear(){
        txtNama.setText("");
        txtHarga.setText("");
        txtStok.setText("");
    }

    public static void fetchData(){
        data = new ArrayList<>();
        data = db.getBarang();
        adp = new Adapter_Barang(rvBarang.getContext(), data, lnInput);
        LinearLayoutManager llm = new LinearLayoutManager(rvBarang.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvBarang.setLayoutManager(llm);
        rvBarang.setAdapter(adp);
    }

    private void getSatuan(){
        satuan = new ArrayList<>();
        satuan.add("pcs");
        satuan.add("kg");
        satuan.add("liter");
        satuan.add("karton");
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, satuan);
        spSatuan.setAdapter(adp);
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            id = intent.getStringExtra("id_barang");
            txtNama.setText(intent.getStringExtra("nama_barang"));
            txtHarga.setText(intent.getStringExtra("harga_barang"));
            txtStok.setText(intent.getStringExtra("stok_barang"));
        }
    };
}