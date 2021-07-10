package com.example.projectakhirpam;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//class adapter untuk recyclerview yang memiliki MyViewHolder untuk menampilkan isinya
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    //deklarasi variabel listpengeluaran dari class Pengeluaran
    private ArrayList<Pengeluaran> listpengeluaran;
    //deklarasi variabel context
    private Context context;

    //membuat konstruktor pada class Adapter
    public Adapter(ArrayList<Pengeluaran> listpengeluaran) {
        this.listpengeluaran = listpengeluaran;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //membuat objek viewholder
        //mendapatkan context dari parent
        context = parent.getContext();
        //membuat objek layoutInf berjenis LayoutInflater dari context
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        //untuk memanggil tampilan dari layout row_pengeluaran
        View view = layoutInf.inflate(R.layout.row_pengeluaran,parent,false);
        //pengembalian
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) { //menghubungkan data yang ada pada viewholder
        //deklarasi variabel string
        String id, judul, keterangan, harga;

        //mengambil data id sesuai posisi yang telah ditentukan
        id = listpengeluaran.get(position).getId();
        //mengambil data judul sesuai posisi yang telah ditentukan
        judul = listpengeluaran.get(position).getJudul();
        //mengambil data keterangan sesuai posisi yang telah ditentukan
        keterangan = listpengeluaran.get(position).getKeterangan();
        //mengambil data harga sesuai posisi yang telah ditentukan
        harga = listpengeluaran.get(position).getHarga();
        DatabaseHelper dbhelp = new DatabaseHelper(context);

        //holder menampilkan teks judul
        holder.judul.setText(judul);
        //holder menampilkan teks keterangan
        holder.keterangan.setText(keterangan);
        //holder menampilkan teks harga
        holder.harga.setText(harga);

        //jika cardku diklik
        holder.cardku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //membuat objek intent untuk berpindah ke class UpdatePengeluaran
                Intent intent = new Intent(context, UpdatePengeluaran.class);
                //mengirimkan data id ke class yang dituju
                intent.putExtra("id", id);
                //mengirimkan data judul ke class yang dituju
                intent.putExtra("judul", judul);
                //mengirimkan data keterangan ke class yang dituju
                intent.putExtra("keterangan", keterangan);
                //mengirimkan data harga ke class yang dituju
                intent.putExtra("harga", harga);
                //memulai berpindah
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { //menentukan jumlah item yang akan ditampilkan
        //Jika listpengeluaran tidak kosong, maka tampilkan jumlah datanya
        return (listpengeluaran != null)?listpengeluaran.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder { //item yang akan ditampilkan pada recyclerview
        //deklarasi cardview
        private CardView cardku;
        //deklarasi textview
        private TextView judul, keterangan, harga;
        public MyViewHolder(View view) {
            super(view);
            //menghubungkan cardview dari java class pada layout
            cardku = (CardView) view.findViewById(R.id.Kartu);
            //menghubungkan textview judul dari java class pada layout
            judul = (TextView) view.findViewById(R.id.tv_judul);
            //menghubungkan textview keterangan dari java class pada layout
            keterangan = (TextView) view.findViewById(R.id.tv_keterangan);
            //menghubungkan textview harga dari java class pada layout
            harga = (TextView) view.findViewById(R.id.tv_harga);
        }
    }
}