package com.example.projectakhirpam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class UpdatePengeluaran extends AppCompatActivity {
    private TextInputEditText edJudul, edKeterangan, edHarga;
    private Button btnupdate, btndelete;
    String id,judul,keterangan,harga;
    DatabaseHelper dbhelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pengeluaran);

        edJudul = findViewById(R.id.edjudul);
        edKeterangan = findViewById(R.id.edketerangan);
        edHarga = findViewById(R.id.edharga);
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);

        id = getIntent().getStringExtra("id");
        judul = getIntent().getStringExtra("judul");
        keterangan = getIntent().getStringExtra("keterangan");
        harga = getIntent().getStringExtra("harga");

        edJudul.setText(judul);
        edKeterangan.setText(keterangan);
        edHarga.setText(harga);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edJudul.getText().toString().equals("") || edHarga.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Kolom Judul dan Harga wajib diisi", Toast.LENGTH_SHORT).show();
                } else {
                    judul = edJudul.getText().toString();
                    keterangan = edKeterangan.getText().toString();
                    harga = edHarga.getText().toString();

                    HashMap<String, String> val = new HashMap<>();
                    val.put("id", id);
                    val.put("judul", judul);
                    val.put("keterangan", keterangan);
                    val.put("harga", harga);

                    dbhelp.update(val);
                    Toast.makeText(getApplicationContext(), "Data Berhasil Diperbarui",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UpdatePengeluaran.this, DataPengeluaran.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePengeluaran.this);
                builder.setMessage("Anda yakin ingin menghapus data ini ?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        HashMap<String, String> val = new HashMap<>();
                        val.put("id", id);
                        dbhelp.delete(val);
                        Toast.makeText(getApplicationContext(), "Data Berhasil Dihapus", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(UpdatePengeluaran.this, DataPengeluaran.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
                builder.create().show();
            }
        });
    }
}