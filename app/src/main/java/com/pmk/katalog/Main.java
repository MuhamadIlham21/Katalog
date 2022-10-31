package com.pmk.katalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pmk.katalog.Adapter.Adapter_Barang;
import com.pmk.katalog.Model.m_barang;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
    RecyclerView rvBarang;
    ArrayList<m_barang> data;
    Adapter_Barang adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rvBarang = findViewById(R.id.rvBarang);

        fetchData();

    }

    private void fetchData(){
        data = new ArrayList<>();
        data.add(new m_barang(
                R.drawable.ic_bike,
                "Sepeda",
                4000000
        ));
        data.add(new m_barang(
                R.drawable.ic_cart,
                "Keranjang",
                250000
        ));


        adp = new Adapter_Barang(this, data);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvBarang.setLayoutManager(llm);
        rvBarang.setAdapter(adp);

    }
}