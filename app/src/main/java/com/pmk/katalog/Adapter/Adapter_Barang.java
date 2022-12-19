package com.pmk.katalog.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pmk.katalog.DatabaseHelper;
import com.pmk.katalog.Main;
import com.pmk.katalog.Model.m_barang;
import com.pmk.katalog.R;
import java.util.ArrayList;

public class Adapter_Barang extends RecyclerView.Adapter<Adapter_Barang.BarangViewHolder> {
    private final Context mCtx;
    private final ArrayList<m_barang> dataBarang;
    private LinearLayout ln;


    public Adapter_Barang(Context ctx, ArrayList<m_barang> dataBarang, LinearLayout ln){
        this.mCtx = ctx;
        this.dataBarang = dataBarang;
        this.ln = ln;
    }

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.list_barang, null);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        m_barang mod = dataBarang.get(position);

        holder.imgBarang.setImageResource(R.drawable.ic_bike);
        holder.tvBarang.setText(mod.getNama_barang());
        holder.tvHarga.setText(String.valueOf(mod.getHarga()));
        holder.tvStok.setText(mod.getStok());
        holder.tvSatuan.setText(mod.getSatuan());

        holder.imgUpdate.setOnClickListener(view -> {
            ln.setVisibility(View.VISIBLE);
            Intent intent = new Intent("pass");
            intent.putExtra("id_barang",mod.getId_barang());
            intent.putExtra("nama_barang",mod.getNama_barang());
            intent.putExtra("harga_barang",mod.getHarga());
            intent.putExtra("stok_barang",mod.getStok());
            intent.putExtra("satuan",mod.getSatuan());
            LocalBroadcastManager.getInstance(mCtx).sendBroadcast(intent);
        });

        holder.imgDelete.setOnClickListener(view -> {
            DatabaseHelper db = new DatabaseHelper(mCtx);
            db.deleteBarang(mod.getId_barang());
            Main.fetchData();
        });

    }

    @Override
    public int getItemCount() {
        return dataBarang.size();
    }

    static class BarangViewHolder extends RecyclerView.ViewHolder{
        ImageView imgBarang,imgUpdate,imgDelete;
        TextView tvBarang,tvHarga,tvStok,tvSatuan;
        public BarangViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBarang = itemView.findViewById(R.id.imgBarang);
            imgUpdate = itemView.findViewById(R.id.imgUpdate);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            tvBarang = itemView.findViewById(R.id.tvBarang);
            tvHarga = itemView.findViewById(R.id.tvHarga);
            tvStok = itemView.findViewById(R.id.tvStok);
            tvSatuan = itemView.findViewById(R.id.tvSatuan);

        }
    }

}
