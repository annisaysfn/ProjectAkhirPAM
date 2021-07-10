package com.example.projectakhirpam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.HashMap;

//SQLiteOpenHelper untuk membantu mengelola database
public class DatabaseHelper extends SQLiteOpenHelper {

    //membuat database dengan nama Pengeluaranku, factory null, versi 1
    public DatabaseHelper(Context context) {
        super(context, "Pengeluaranku", null, 1);
    }

    //method oncreate akan dipanggil apabila sebelumnya tidak terdapat database
    public void onCreate(SQLiteDatabase db) {
        //membuat tabel pengeluaran dengan atribut id sbg primary key, judul, keterangan, dan harga
        db.execSQL("create table pengeluaran (id integer primary key, judul text, keterangan text, harga int)");
    }

    //jika SQlite akan diupgrade, maka tabel akan dihapus terlebih dahulu. Dan tabel akan dibuat lagi.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //eksekusi syntax
        db.execSQL("drop table if exists pengeluaran");
        //tabel dibuat lagi
        onCreate(db);
    }

    //method insert untuk menambahkan data ke dalam database
    // dengan parameter hashmap (array berpasangan)
    //ada 2 parameter berjenis string yaitu kunci dan value(nilai)
    // queryvalues sebagai nama variabel
    public void insert(HashMap<String, String> queryValues) {
        //memanggil database agar dapat dimasuki data
        SQLiteDatabase sqld = this.getWritableDatabase();
        //membuat objek value berjenis contentvalues
        //ContentValues menyimpan data sebagai pasangan kunci dan nilai
        ContentValues value = new ContentValues();
        //menambahkan baris judul
        value.put("judul", queryValues.get("judul"));
        //menambahkan baris keterangan
        value.put("keterangan", queryValues.get("keterangan"));
        //menambahkan baris harga
        value.put("harga", queryValues.get("harga"));
        //insert value ke tabel pengeluaran
        sqld.insert("pengeluaran", null, value);
        //menutup database
        sqld.close();
    }

    //method update untuk memperbarui data
    // dengan parameter hashmap (array berpasangan)
    //ada 2 parameter berjenis string yaitu kunci dan value(nilai)
    // queryvalues sebagai nama variabel
    public void update(HashMap<String, String> queryValues) {
        //memanggil database agar dapat dimasuki data
        SQLiteDatabase sqld = this.getWritableDatabase();
        //membuat objek value berjenis contentvalues
        //ContentValues menyimpan data sebagai pasangan kunci dan nilai
        ContentValues value = new ContentValues();
        //menambahkan baris judul
        value.put("judul", queryValues.get("judul"));
        //menambahkan baris keterangan
        value.put("keterangan", queryValues.get("keterangan"));
        //menambahkan baris harga
        value.put("harga", queryValues.get("harga"));
        //update value ke tabel pengeluaran berdasarkan id
        sqld.update("pengeluaran", value, "id" + " =?", new String[]{queryValues.get("id")});
        //menutup database
        sqld.close();
    }

    //method delete untuk menghapus data
    // dengan parameter hashmap (array berpasangan)
    //ada 2 parameter berjenis string yaitu kunci dan value(nilai)
    // queryvalues sebagai nama variabel
    public int delete(HashMap<String, String> queryValues) {
        //memanggil database agar dapat dimasuki data
        SQLiteDatabase sqld = this.getWritableDatabase();
        //menghapus data berdasarkan id
        return sqld.delete("pengeluaran", "id" + " =?", new String[]{queryValues.get("id")});
    }

    //method untuk menghapus semua data
    void deleteall(){
        //memanggil database agar dapat dimasuki data
        SQLiteDatabase sqld = this.getWritableDatabase();
        //eksekusi syntax hapus semua data pada tabel
        sqld.execSQL("DELETE FROM pengeluaran" );
    }

    //method getAllPengeluaran tipe ArrayList
    public ArrayList<HashMap<String, String>> getAllPengeluaran() {
        //membuat objek ListPengeluaran berjenis ArrayList
        ArrayList<HashMap<String, String>> ListPengeluaran;
        ListPengeluaran = new ArrayList<HashMap<String, String>>();
        //query untuk menampilkan semua data dari tabel pengeluaran
        String selectQuery = "Select * from pengeluaran";
        //memanggil database agar dapat dilihat isi database-nya
        SQLiteDatabase sqld = this.getReadableDatabase();
        //rawQuery untuk mengirimkan perintah selectQuery kemudian disimpan dalam cursor
        Cursor cursor = sqld.rawQuery(selectQuery, null);
        //posisi cursor pada record pertama
        if (cursor.moveToFirst()) {
            do {
                //membuat objek map
                HashMap<String, String> map = new HashMap<>();
                //mengambil data id ke indeks pertama
                map.put("id", cursor.getString(0));
                //mengambil data judul ke indeks kedua
                map.put("judul", cursor.getString(1));
                //mengambil data keterangan ke indeks ketiga
                map.put("keterangan", cursor.getString(2));
                //mengambil data harga ke indeks keempat
                map.put("harga", cursor.getString(3));
                //menambahkan map ke ListPengeluaran
                ListPengeluaran.add(map);

            } while (cursor.moveToNext()); //cursor beralih ke record selanjutnya
        }
        //menutup database
        sqld.close();
        //mengembalikan ListPengeluaran ke getAllPengeluaran
        return ListPengeluaran;
    }
}