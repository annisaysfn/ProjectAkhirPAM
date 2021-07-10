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
    //deklarasi variabel textinputedittext
    private TextInputEditText edJudul, edKeterangan, edHarga;
    //deklarasi button
    private Button btnupdate, btndelete;
    //deklarasi variabel string id, judul, keterangan, harga
    String id,judul,keterangan,harga;
    //deklarasi dbhelp dari class DatabaseHelper
    DatabaseHelper dbhelp = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pengeluaran); //mengeset layout activity_update_pengeluaran

        //menghubungkan edJudul dengan edjudul pada layout
        edJudul = findViewById(R.id.edjudul);
        //menghubungkan edKeterangan dengan edketerangan pada layout
        edKeterangan = findViewById(R.id.edketerangan);
        //menghubungkan edHarga dengan edharga pada layout
        edHarga = findViewById(R.id.edharga);
        //menghubungkan btnupdate dengan btnupdate pada layout
        btnupdate = findViewById(R.id.btnupdate);
        //menghubungkan btndelete dengan btndelete pada layout
        btndelete = findViewById(R.id.btndelete);

        //menangkap dan mengambil nilai id dari class pengirim
        id = getIntent().getStringExtra("id");
        //menangkap dan mengambil nilai judul dari class pengirim
        judul = getIntent().getStringExtra("judul");
        //menangkap dan mengambil nilai keterangan dari class pengirim
        keterangan = getIntent().getStringExtra("keterangan");
        //menangkap dan mengambil nilai harga dari class pengirim
        harga = getIntent().getStringExtra("harga");

        //menampilkan teks sebelumnya pada edJudul
        edJudul.setText(judul);
        //menampilkan teks sebelumnya pada edKeterangan
        edKeterangan.setText(keterangan);
        //menampilkan teks sebelumnya pada edHarga
        edHarga.setText(harga);

        //jika btnupdate di click
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jika textinputedittext judul dan harga kosong
                //maka akan menampilkan toast "Kolom Judul dan Harga wajib diisi"
                if (edJudul.getText().toString().equals("") || edHarga.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Kolom Judul dan Harga wajib diisi", Toast.LENGTH_SHORT).show();
                } else {
                    //membaca isi dari edittext judul
                    judul = edJudul.getText().toString();
                    //membaca isi dari edittext keterangan
                    keterangan = edKeterangan.getText().toString();
                    //membaca isi dari edittext harga
                    harga = edHarga.getText().toString();

                    //membuat objek val berbentuk array hashmap
                    HashMap<String, String> val = new HashMap<>();
                    //memasukkan data pada kolom id
                    val.put("id", id);
                    //memasukkan data pada kolom judul
                    val.put("judul", judul);
                    //memasukkan data pada kolom keterangan
                    val.put("keterangan", keterangan);
                    //memasukkan data pada kolom harga
                    val.put("harga", harga);

                    //memanggil method update dari class DatabaseHelper
                    dbhelp.update(val);
                    //menampikkan toast bahwa data berhasil diperbarui
                    Toast.makeText(getApplicationContext(), "Data Berhasil Diperbarui",
                            Toast.LENGTH_LONG).show();
                    //membuat objek intent untuk berpindah ke class DataPengeluaran
                    Intent intent = new Intent(UpdatePengeluaran.this, DataPengeluaran.class);
                    //memulai berpindah
                    startActivity(intent);
                    //mengakhiri activity
                    finish();
                }
            }
        });

        //jika btndelete di click
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //membuat alertdialog dengan nama builder
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePengeluaran.this);
                //membuat isi dari alert
                builder.setMessage("Anda yakin ingin menghapus data ini ?");
                //apabila klik Ya pada alert
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //membuat objek val berbentuk array hashmap
                        HashMap<String, String> val = new HashMap<>();
                        //memasukkan data pada kolom id
                        val.put("id", id);
                        //memanggil method delete dari class DatabaseHelper
                        dbhelp.delete(val);
                        //menampikkan toast bahwa data berhasil dihapus
                        Toast.makeText(getApplicationContext(), "Data Berhasil Dihapus", Toast.LENGTH_LONG).show();
                        //membuat objek intent untuk berpindah ke class DataPengeluaran
                        Intent intent = new Intent(UpdatePengeluaran.this, DataPengeluaran.class);
                        //memulai berpindah
                        startActivity(intent);
                        //mengakhiri activity
                        finish();
                    }
                });
                //apabila klik Tidak pada alert
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //menutup alert dialog dan tidak terjadi apa-apa
                    }
                });
                builder.create().show(); //membuat dan menampilkan alert
            }
        });
    }
}