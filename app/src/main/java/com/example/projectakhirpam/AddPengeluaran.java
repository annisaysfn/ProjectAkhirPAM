package com.example.projectakhirpam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddPengeluaran extends AppCompatActivity {
    //deklarasi variabel textinputedittext
    private TextInputEditText edJudul, edKeterangan, edHarga;
    //deklarasi button
    private Button btnadd;
    //deklarasi variabel string judul, keterangan, harga
    String judul,keterangan,harga;
    //deklarasi dbhelp dari class DatabaseHelper
    DatabaseHelper dbhelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pengeluaran); //mengeset layout activity_add_pengeluaran

        //menghubungkan edJudul dengan edjudul pada layout
        edJudul = findViewById(R.id.edjudul);
        //menghubungkan edKeterangan dengan edketerangan pada layout
        edKeterangan = findViewById(R.id.edketerangan);
        //menghubungkan edHarga dengan edharga pada layout
        edHarga = findViewById(R.id.edharga);
        //menghubungkan btnadd dengan btnadd pada layout
        btnadd = findViewById(R.id.btnadd);
        //apabila btnadd di click
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //jika textinputedittext judul dan harga kosong
                //maka akan menampilkan toast "Kolom Judul dan Harga wajib diisi"
                if (edJudul.getText().toString().equals("")||edHarga.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Kolom Judul dan Harga wajib diisi",Toast.LENGTH_SHORT).show();
                } else {
                    //membaca isi dari edittext judul
                    judul = edJudul.getText().toString();
                    //membaca isi dari edittext keterangan
                    keterangan = edKeterangan.getText().toString();
                    //membaca isi dari edittext harga
                    harga = edHarga.getText().toString();

                    //membuat objek val berbentuk array hashmap
                    HashMap<String, String> val = new HashMap<>();
                    //memasukkan data pada kolom judul
                    val.put("judul", judul);
                    //memasukkan data pada kolom keterangan
                    val.put("keterangan", keterangan);
                    //memasukkan data pada kolom harga
                    val.put("harga", harga);

                    //memanggil method insert dari class DatabaseHelper
                    dbhelp.insert(val);
                    //menampikkan toast bahwa data berhasil ditambahkan
                    Toast.makeText(getApplicationContext(), "Data Berhasil Ditambahkan", Toast.LENGTH_LONG).show();

                    //membuat objek intent untuk berpindah ke class DataPengeluaran
                    Intent intent = new Intent(AddPengeluaran.this, DataPengeluaran.class);
                    //memulai berpindah
                    startActivity(intent);
                    //mengakhiri activity
                    finish();
                }
            }
        });
    }
}