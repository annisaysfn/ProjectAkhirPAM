package com.example.projectakhirpam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "Pengeluaranku", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pengeluaran (id integer primary key, judul text, keterangan text, harga int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists pengeluaran");
        onCreate(db);
    }

    public void insert(HashMap<String, String> queryValues) {
        SQLiteDatabase sqld = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("judul", queryValues.get("judul"));
        value.put("keterangan", queryValues.get("keterangan"));
        value.put("harga", queryValues.get("harga"));
        sqld.insert("pengeluaran", null, value);
        sqld.close();
    }

    public void update(HashMap<String, String> queryValues) {
        SQLiteDatabase sqld = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("judul", queryValues.get("judul"));
        value.put("keterangan", queryValues.get("keterangan"));
        value.put("harga", queryValues.get("harga"));
        sqld.update("pengeluaran", value, "id" + " =?", new String[]{queryValues.get("id")});
        sqld.close();
    }

    public int delete(HashMap<String, String> queryValues) {
        SQLiteDatabase sqld = this.getWritableDatabase();
        return sqld.delete("pengeluaran", "id" + " =?", new String[]{queryValues.get("id")});
    }

    void deleteall(){
        SQLiteDatabase sqld = this.getWritableDatabase();
        sqld.execSQL("DELETE FROM pengeluaran" );
    }

    public ArrayList<HashMap<String, String>> getAllPengeluaran() {
        ArrayList<HashMap<String, String>> ListPengeluaran;
        ListPengeluaran = new ArrayList<HashMap<String, String>>();
        String selectQuery = "Select * from pengeluaran";
        SQLiteDatabase sqld = this.getReadableDatabase();
        Cursor cursor = sqld.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("judul", cursor.getString(1));
                map.put("keterangan", cursor.getString(2));
                map.put("harga", cursor.getString(3));
                ListPengeluaran.add(map);
            } while (cursor.moveToNext());
        }
        sqld.close();
        return ListPengeluaran;
    }
}