package com.example.projectakhirpam;

//sebagai wadah data aplikasi
public class Pengeluaran {
    //deklarasi string
    String id;
    String judul;
    String keterangan;
    String harga;
    String tanggal;

    //konstruktor Pengeluaran tanpa parameter
    public Pengeluaran() {
    }

    //Konstruktor Pengeluaran dengan parameter
    public Pengeluaran(String id, String judul, String keterangan, String harga, String tanggal) {
        this.id = id;
        this.judul = judul;
        this.keterangan = keterangan;
        this.harga = harga;
        this.tanggal = tanggal;
    }

    //Generate Getter and Setter
    //method get untuk mengembalikan nilai ke variabel string
    //method set untuk mengisi data ke variabel string
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
