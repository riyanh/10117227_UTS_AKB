package com.example.a10117227_uts_akb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
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

public class UpdateActivity extends AppCompatActivity {

    private DBContact MyDatabase;
    private EditText NewNomor, NewNama, NewEmail;
    private String getNewNomor, getNewNama, getNewEmail;
    private Button Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setTitle("Edit Data");
        MyDatabase = new DBContact(getBaseContext());
        NewNomor = findViewById(R.id.nomor_id);
        NewNama = findViewById(R.id.nama);
        NewEmail = findViewById(R.id.email);

        NewNama.setText(getIntent().getExtras().getString("SendNama"));
        NewNomor.setText(getIntent().getExtras().getString("SendNIM"));
        NewEmail.setText(getIntent().getExtras().getString("SendEmail"));

        Update = findViewById(R.id.edit);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpdateData();
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    private void setUpdateData(){
        getNewNomor = NewNomor.getText().toString();
        getNewNama = NewNama.getText().toString();
        getNewEmail = NewEmail.getText().toString();


        SQLiteDatabase database = MyDatabase.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContact.MyColumns.Nama, getNewNama);
        values.put(DBContact.MyColumns.Email, getNewEmail);
        values.put(DBContact.MyColumns.Nomor, getNewNomor);

        //Untuk Menentukan Data/Item yang ingin diubah, berdasarkan NIM
        String selection = DBContact.MyColumns.Nomor + " LIKE ?";
        String[] selectionArgs = {getNewNomor};
        database.update(DBContact.MyColumns.NamaTabel, values, selection, selectionArgs);
        Toast.makeText(getApplicationContext(), "Edit Successed", Toast.LENGTH_SHORT).show();
    }

}
