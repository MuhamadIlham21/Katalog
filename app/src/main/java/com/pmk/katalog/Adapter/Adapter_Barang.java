package com.pmk.katalog.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pmk.katalog.Model.m_barang;
import com.pmk.katalog.R;

import java.util.ArrayList;

public class Adapter_Barang extends RecyclerView.Adapter<Adapter_Barang.BarangViewHolder> {
    private final Context mCtx;
    private final ArrayList<m_barang> dataBarang;

    public Adapter_Barang(Context ctx, ArrayList<m_barang> dataBarang){
        this.mCtx = ctx;
        this.dataBarang = dataBarang;
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

        holder.tvBarang.setText(mod.getNama_barang());
        holder.imgBarang.setImageResource(mod.getImg());
        holder.tvHarga.setText(String.valueOf(mod.getHarga()));
        holder.imgBarang.setImageResource(R.drawable.ic_cart);

    }

    @Override
    public int getItemCount() {
        return dataBarang.size();
    }

    static class BarangViewHolder extends RecyclerView.ViewHolder{
        ImageView imgBarang;
        ImageView imgKeranjang;
        TextView tvBarang;
        TextView tvHarga;
        public BarangViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBarang = itemView.findViewById(R.id.imgBarang);
            imgKeranjang = itemView.findViewById(R.id.imgKeranjang);
            tvBarang = itemView.findViewById(R.id.tvBarang);
            tvHarga = itemView.findViewById(R.id.tvHarga);

        }
    }

}
