package com.candra.football_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.candra.football_player.Database.MyDatabaseHelper;

public class UbahActivity extends AppCompatActivity
{
    private EditText etNama, etNomor, etKlub;
    private Button btnUbah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        etKlub = findViewById(R.id.et_klub);

        btnUbah = findViewById(R.id.btn_ubah);

        Intent gIntent = getIntent();
        String id = gIntent.getStringExtra("varId");
        String nama = gIntent.getStringExtra("varNama");
        String nomor = gIntent.getStringExtra("varNomor");
        String klub = gIntent.getStringExtra("varKlub");

        etNama.setText(nama);
        etNomor.setText(nomor);
        etKlub.setText(klub);

        btnUbah.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                String getNama, getNomor, getKlub;

                getNama = etNama.getText().toString();
                getNomor = etNomor.getText().toString();
                getKlub = etKlub.getText().toString();

                if(getNama.trim().equals(""))
                {
                    etNama.setError("Nama player tidak boleh kosong !");
                    etNama.setFocusable(true);
                }
                else if(getNomor.trim().equals(""))
                {
                    etNomor.setError("Nomor punggung tidak boleh kosong !");
                    etNomor.setFocusable(true);
                }
                else if(getKlub.trim().equals(""))
                {
                    etKlub.setError("Klub tidak boleh kosong !");
                    etKlub.setFocusable(true);
                }
                else
                {
                    long execute = myDB.ubahPlayer(id, getNama, getNomor, getKlub);

                    if(execute == -1)
                    {
                        Toast.makeText(UbahActivity.this, "Gagal mengubah data player !", Toast.LENGTH_SHORT).show();
                        etNama.requestFocus();
                    }
                    else
                    {
                        Toast.makeText(UbahActivity.this, "Berhasil mengubah data player !", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });





    }
}