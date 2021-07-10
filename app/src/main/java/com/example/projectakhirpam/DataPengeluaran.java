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
    //deklarasi variabel recyclerview
    private RecyclerView recyclerView;
    //deklarasi adapter
    private Adapter adapter;
    //deklarasi arraylist dari class Pengeluaran
    private ArrayList<Pengeluaran> pengeluaranArrayList;
    //deklarasi dbhelp dari class DatabaseHelper
    DatabaseHelper dbhelp = new DatabaseHelper(this);
    //deklarasi floatingactionbutton
    private FloatingActionButton buttonadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengeluaran); //mengeset layout activity_data_pengeluaran

        //menghubungkan recyclerView dengan recyclerView pada layout
        recyclerView = findViewById(R.id.recyclerView);
        //menghubungkan buttonadd dengan floatingBtn pada layout
        buttonadd = findViewById(R.id.floatingBtn);

        //memanggil method getData
        getData();
        //membuat objek adapter berupa arraylist
        adapter = new Adapter(pengeluaranArrayList);
        //membuat objek layoutmanager berupa linearlayout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataPengeluaran.this);
        //mengeset layoutmanager
        recyclerView.setLayoutManager(layoutManager);
        //mengeset adapter
        recyclerView.setAdapter(adapter);
        //apabila button add di click
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //membuat objek intent untuk berpindah ke class AddPengeluaran
                Intent intent = new Intent(DataPengeluaran.this, AddPengeluaran.class);
                //memulai berpindah
                startActivity(intent);
            }
        });
    }

    private void getData() {
        //membuat objek daftarPengeluaran dari class DatabaseHelper
        ArrayList<HashMap<String, String>> daftarPengeluaran = dbhelp.getAllPengeluaran();
        pengeluaranArrayList = new ArrayList<>();
        //perulangan untuk membaca indeks dari daftarPengeluaran
        for (int i = 0; i < daftarPengeluaran.size(); i++) {
            //membuat objek pengeluaran
            Pengeluaran pengeluaran = new Pengeluaran();

            //mengisi dan mengambil data Id
            pengeluaran.setId(daftarPengeluaran.get(i).get("id").toString());
            //mengisi dan mengambil data Judul
            pengeluaran.setJudul(daftarPengeluaran.get(i).get("judul").toString());
            //mengisi dan mengambil data Keterangan
            pengeluaran.setKeterangan(daftarPengeluaran.get(i).get("keterangan").toString());
            //mengisi dan mengambil data Harga
            pengeluaran.setHarga(daftarPengeluaran.get(i).get("harga").toString());
            //memindahkan semua yang ada pada pengeluaran ke pengeluaranArrayList
            pengeluaranArrayList.add(pengeluaran);
        }
    }

    //membuat option menu delete
    public boolean onCreateOptionsMenu(Menu menu) {
        //membuat objek inflater
        MenuInflater inflater = getMenuInflater();
        //method untuk menampilkan menu
        inflater.inflate(R.menu.itemdelete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //apabila menu di klik
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //apabila menu delete di klik
        if(item.getItemId() == R.id.deleteic){
            //memanggil method confirmAlert
            confirmAlert();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmAlert(){
        //membuat alertdialog dengan nama builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //membuat judul pada alert
        builder.setTitle("HAPUS SEMUA CATATAN");
        //membuat isi dari alert
        builder.setMessage("Anda yakin ingin menghapus semua catatan?");
        //apabila klik Ya pada alert
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //memanggil class DatabaseHelper
                DatabaseHelper dbhelp = new DatabaseHelper(DataPengeluaran.this);
                //memanggil method deleteall pada DatabaseHelper
                dbhelp.deleteall();
                //membuat objek intent untuk berpindah ke class DataPengeluaran
                Intent intent = new Intent(DataPengeluaran.this, DataPengeluaran.class);
                //memulai berpindah
                startActivity(intent);
                //mengakhiri activity
                finish();
            }
        });
        //apabila klik Tidak pada alert
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //menutup alert dialog dan tidak terjadi apa-apa
            }
        });
        builder.create().show(); //membuat dan menampilkan alert
    }
}