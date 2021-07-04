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

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private ArrayList<Pengeluaran> listpengeluaran;
    private Context context;

    public Adapter(ArrayList<Pengeluaran> listpengeluaran) {
        this.listpengeluaran = listpengeluaran;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_pengeluaran,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String id, judul, keterangan, harga;

        id = listpengeluaran.get(position).getId();
        judul = listpengeluaran.get(position).getJudul();
        keterangan = listpengeluaran.get(position).getKeterangan();
        harga = listpengeluaran.get(position).getHarga();
        DatabaseHelper dbhelp = new DatabaseHelper(context);

        holder.judul.setText(judul);
        holder.keterangan.setText(keterangan);
        holder.harga.setText(harga);

        holder.cardku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdatePengeluaran.class);
                intent.putExtra("id", id);
                intent.putExtra("judul", judul);
                intent.putExtra("keterangan", keterangan);
                intent.putExtra("harga", harga);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listpengeluaran != null)?listpengeluaran.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView judul, keterangan, harga;
        public MyViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.Kartu);
            judul = (TextView) view.findViewById(R.id.tv_judul);
            keterangan = (TextView) view.findViewById(R.id.tv_keterangan);
            harga = (TextView) view.findViewById(R.id.tv_harga);
        }
    }
}