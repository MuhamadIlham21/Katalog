package com.pmk.katalog.Model;

public class m_barang {
    int img;
    String nama_barang;
    int harga;

    public m_barang(int img, String nama_barang, int harga) {
        this.img = img;
        this.nama_barang = nama_barang;
        this.harga = harga;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
