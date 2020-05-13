package com.example.a10117227_uts_akb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Tanggal Pengerjaan   : 11-Mei-2020
// Nim                  : 10117227
// Nama                 : Mohamad Riyan Hidayat
// Kelas                : IF-7

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList namaList;
    private ArrayList emailList;
    private ArrayList nomorList;
    private Context context;

    //Membuat Konstruktor pada Class RecyclerViewAdapter
    RecyclerViewAdapter(ArrayList namaList, ArrayList emailList, ArrayList nomorList){
        this.namaList = namaList;
        this.emailList = emailList;
        this.nomorList = nomorList;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Nama, Email, Nomor;
        private ImageButton Overflow;

        ViewHolder(View itemView) {
            super(itemView);

            //Mendapatkan Context dari itemView yang terhubung dengan Activity ViewData
            context = itemView.getContext();

            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            Nama = itemView.findViewById(R.id.name);
            Email = itemView.findViewById(R.id.email);
            Overflow = itemView.findViewById(R.id.overflow);
            Nomor = itemView.findViewById(R.id.nomor_id);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //Memanggil Nilai/Value Pada View-View Yang Telah Dibuat pada Posisi Tertentu
        final String Nama = (String) namaList.get(position);
        final String Email = (String) emailList.get(position);
        final String Nomor = (String) nomorList.get(position);
        holder.Nama.setText(Nama);
        holder.Email.setText(Email);
        holder.Nomor.setText(Nomor);

        //Mengimplementasikan Menu Popup pada Overflow (ImageButton)
        holder.Overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //Membuat Instance/Objek dari PopupMenu
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.delete:
                                //Menghapus Data Dari Database
                                DBContact getDatabase = new DBContact(view.getContext());
                                SQLiteDatabase DeleteData = getDatabase.getWritableDatabase();
                                //Menentukan di mana bagian kueri yang akan dipilih
                                String selection = DBContact.MyColumns.Nomor + " LIKE ?";
                                //Menentukan Nama Dari Data Yang Ingin Dihapus
                                String[] selectionArgs = {holder.Nomor.getText().toString()};
                                DeleteData.delete(DBContact.MyColumns.NamaTabel, selection, selectionArgs);

                                //Menghapus Data pada List dari Posisi Tertentu
                                int position = nomorList.indexOf(Nomor);
                                nomorList.remove(position);
                                notifyItemRemoved(position);
                                Toast.makeText(view.getContext(),"Data Dihapus",Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.update:
                                //Statement Update
                                Intent dataForm = new Intent(view.getContext(), UpdateActivity.class);
                                dataForm.putExtra("SendNIM", holder.Nomor.getText().toString());
                                dataForm.putExtra("SendNama", holder.Nama.getText().toString());
                                dataForm.putExtra("SendEmail", holder.Email.getText().toString());
                                context.startActivity(dataForm);
                                ((Activity)context).finish();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return nomorList.size();
    }

}
