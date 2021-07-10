package com.example.projectakhirpam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //deklarasi button
    Button lihatbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //mengeset layout activity_main

        //menghubungkan lihatbtn dengan Lihatbtn pada layout
        lihatbtn = findViewById(R.id.Lihatbtn);

        //jika lihatbtn di click
        lihatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //membuat objek intent untuk berpindah ke class DataPengeluaran
                Intent intent = new Intent(MainActivity.this, DataPengeluaran.class);
                //memulai berpindah
                startActivity(intent);
            }
        });
    }
}