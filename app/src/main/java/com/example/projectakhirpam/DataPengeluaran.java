package com.example.projectakhirpam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;

public class DataPengeluaran extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Pengeluaran> pengeluaranArrayList;
    DatabaseHelper dbhelp = new DatabaseHelper(this);
    private FloatingActionButton buttonadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengeluaran);

        recyclerView = findViewById(R.id.recyclerView);
        buttonadd = findViewById(R.id.floatingBtn);

        getData();
        adapter = new Adapter(pengeluaranArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataPengeluaran.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataPengeluaran.this, AddPengeluaran.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        ArrayList<HashMap<String, String>> daftarPengeluaran = dbhelp.getAllPengeluaran();
        pengeluaranArrayList = new ArrayList<>();
        for (int i = 0; i < daftarPengeluaran.size(); i++) {
            Pengeluaran pengeluaran = new Pengeluaran();

            pengeluaran.setId(daftarPengeluaran.get(i).get("id").toString());
            pengeluaran.setJudul(daftarPengeluaran.get(i).get("judul").toString());
            pengeluaran.setKeterangan(daftarPengeluaran.get(i).get("keterangan").toString());
            pengeluaran.setHarga(daftarPengeluaran.get(i).get("harga").toString());
            pengeluaranArrayList.add(pengeluaran);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.itemdelete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.deleteic){
            confirmAlert();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("HAPUS SEMUA CATATAN");
        builder.setMessage("Anda yakin ingin menghapus semua catatan?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper dbhelp = new DatabaseHelper(DataPengeluaran.this);
                dbhelp.deleteall();
                Intent intent = new Intent(DataPengeluaran.this, DataPengeluaran.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}