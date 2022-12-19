package com.pmk.katalog.Model;

public class m_barang {
    int img;
    String id_barang;
    String nama_barang;
    String harga;
    String stok;
    String satuan;

    public m_barang(int img, String nama_barang, String harga, String stok, String satuan) {
        this.img = img;
        this.nama_barang = nama_barang;
        this.harga = harga;
        this.stok = stok;
        this.satuan = satuan;
    }

    public m_barang(String id_barang, String nama_barang, String harga, String stok, String satuan) {
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.harga = harga;
        this.stok = stok;
        this.satuan = satuan;
    }

    public m_barang(String nama_barang, String harga, String stok, String satuan) {
        this.nama_barang = nama_barang;
        this.harga = harga;
        this.stok = stok;
        this.satuan = satuan;
    }

    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
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

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
