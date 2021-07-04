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
    private TextInputEditText edJudul, edKeterangan, edHarga;
    private Button btnadd;
    String judul,keterangan,harga;
    DatabaseHelper dbhelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pengeluaran);

        edJudul = findViewById(R.id.edjudul);
        edKeterangan = findViewById(R.id.edketerangan);
        edHarga = findViewById(R.id.edharga);
        btnadd = findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edJudul.getText().toString().equals("")||edHarga.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Kolom Judul dan Harga wajib diisi",Toast.LENGTH_SHORT).show();
                } else {
                    judul = edJudul.getText().toString();
                    keterangan = edKeterangan.getText().toString();
                    harga = edHarga.getText().toString();

                    HashMap<String, String> val = new HashMap<>();
                    val.put("judul", judul);
                    val.put("keterangan", keterangan);
                    val.put("harga", harga);

                    dbhelp.insert(val);
                    Toast.makeText(getApplicationContext(), "Data Berhasil Ditambahkan", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(AddPengeluaran.this, DataPengeluaran.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}