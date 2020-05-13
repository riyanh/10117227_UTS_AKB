package com.example.a10117227_uts_akb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


// Tanggal Pengerjaan   : 11-Mei-2020
// Nim                  : 10117227
// Nama                 : Mohamad Riyan Hidayat
// Kelas                : IF-7
public class InputActivity extends AppCompatActivity {

    private EditText Nomor, Nama, Email;

    //Variable Untuk Menyimpan Input Dari Ueer
    private String setNomor, setNama, setEmail;

    //Variable Untuk Inisialisasi Database DBMahasiswa
    private DBContact dbContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        getSupportActionBar().setTitle("Add Data");
        Button save = findViewById(R.id.save);
        Button cancel = findViewById(R.id.cancel);
        Nomor = findViewById(R.id.nomor_id);
        Nama = findViewById(R.id.nama);
        Email = findViewById(R.id.email);

        //inisialisasi dan mendapatkan konteks dari DBMahasiswa
        dbContact = new DBContact(getBaseContext());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                saveData();
                Toast.makeText(getApplicationContext(),"Successfully", Toast.LENGTH_SHORT).show();
                clearData();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                saveData();
                Toast.makeText(getApplicationContext(),"Successfully", Toast.LENGTH_SHORT).show();
                clearData();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearData();
            }
        });
    }

    //Berisi Statement-Statement Untuk Mendapatkan Input Dari User
    private void setData(){
        setNomor = Nomor.getText().toString();
        setNama = Nama.getText().toString();
        setEmail = Email.getText().toString();

    }

    private void saveData(){
        //Mendapatkan Repository dengan Mode Menulis
        SQLiteDatabase create = dbContact.getWritableDatabase();

        //Membuat Map Baru, Yang Berisi Nama Kolom dan Data Yang Ingin Dimasukan
        ContentValues values = new ContentValues();
        values.put(DBContact.MyColumns.Nomor, setNomor);
        values.put(DBContact.MyColumns.Nama, setNama);
        values.put(DBContact.MyColumns.Email, setEmail);

        //Menambahkan Baris Baru, Berupa Data Yang Sudah Diinputkan pada Kolom didalam Database
        create.insert(DBContact.MyColumns.NamaTabel, null, values);
    }

    private void clearData(){
        Nomor.setText("");
        Nama.setText("");
        Email.setText("");
    }
}
