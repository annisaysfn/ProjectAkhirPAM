package com.example.projectakhirpam;

public class Pengeluaran {
    String id;
    String judul;
    String keterangan;
    String harga;
    String tanggal;

    public Pengeluaran() {
    }

    public Pengeluaran(String id, String judul, String keterangan, String harga, String tanggal) {
        this.id = id;
        this.judul = judul;
        this.keterangan = keterangan;
        this.harga = harga;
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

}
