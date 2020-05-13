package com.example.a10117227_uts_akb;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

// Tanggal Pengerjaan   : 9-Mei-2020
// Nim                  : 10117227
// Nama                 : Mohamad Riyan Hidayat
// Kelas                : IF-7
public class ContactFragment extends Fragment {

    private DBContact MyDatabase;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList NamaList;
    private ArrayList EmailList;
    private ArrayList NomorList;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Contact");

        NamaList = new ArrayList<>();
        EmailList = new ArrayList<>();
        NomorList = new ArrayList<>();
        MyDatabase = new DBContact(getActivity().getBaseContext());

        RecyclerView recyclerView = getView().findViewById(R.id.recycler);
        getData();

        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(NamaList, EmailList, NomorList);

        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(adapter);
        //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);

//        button=view.findViewById(R.id.fab_add);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                goToAddUserActivity();
//            }
//        });
    }

    //Berisi Statement-Statement Untuk Mengambi Data dari Database
    @SuppressLint("Recycle")
    protected void getData(){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM "+ DBContact.MyColumns.NamaTabel,null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir

            NomorList.add(cursor.getString(0));//Menambil Data Dari Kolom 0 (NIM)
            NamaList.add(cursor.getString(1));//Menambil Data Dari Kolom 1 (Nama)
            EmailList.add(cursor.getString(2));//Menambil Data Dari Kolom 2 (Jurusan)
        }
    }



//    private DBContact MyDatabase;
//    private ArrayList<String> listItem;
//    private ArrayAdapter adapter;


    //
//        listItem = new ArrayList<>();
////        viewData();
//
//        String[] menuItem = {
//                    "Do Something",
//                    "Do Something else",
//                    "Do yet another thing!"
//        };
//
//        ListView listView = (ListView) rootView.findViewById(R.id.list);
//
//        ArrayAdapter<String> ListViewAdapter = new ArrayAdapter<String>(
//            getActivity(),
//                android.R.layout.simple_expandable_list_item_1,
//                menuItem
//        );
//
//        listView.setAdapter(ListViewAdapter);




//    private void viewData() {
//        Cursor cursor = MyDatabase.viewData();
//
//        if (cursor.getCount() == 0) {
//            //Toast.makeText(this, "There was an error in compiling", Toast.LENGTH_SHORT).show();
//        } else {
//            while (cursor.moveToNext()) {
//                listItem.add(cursor.getString(1));
//            }
////            adapter = new ArrayAdapter<String>((getActivity), android.R.layout.simple_expandable_list_item_1, listItem);
////            userlist.setAdapter(adapter);
//            adapter = new ArrayAdapter<String>(
//            getActivity(),
//                android.R.layout.simple_expandable_list_item_1,
//                listItem
//            );
//            userlist.setAdapter(adapter);
//        }
//    }

}
