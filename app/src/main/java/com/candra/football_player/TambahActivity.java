package com.candra.football_player;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.candra.football_player.Database.MyDatabaseHelper;

public class TambahActivity extends AppCompatActivity
{

    private EditText etNama, etNomor, etKlub;
    private Button btnTambah;
    private MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        myDB = new MyDatabaseHelper(TambahActivity.this);
        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        etKlub = findViewById(R.id.et_klub);
        btnTambah = findViewById(R.id.btn_tambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String nama, nomor, klub;
                nama = etNama.getText().toString();
                nomor = etNomor.getText().toString();
                klub = etKlub.getText().toString();

                if(nama.trim().equals(""))
                {
                    etNama.setError("Nama player tidak boleh kosong !");
                    etNama.setFocusable(true);
                }
                else if(nomor.trim().equals(""))
                {
                    etNomor.setError("Nomor punggung tidak boleh kosong !");
                    etNomor.setFocusable(true);
                }
                else if(klub.trim().equals(""))
                {
                    etKlub.setError("Klub tidak boleh kosong !");
                    etKlub.setFocusable(true);
                }
                else
                {
                    long execute = myDB.tambahPlayer(nama, nomor, klub);

                    if(execute == -1)
                    {
                        Toast.makeText(TambahActivity.this, "Gagal menambahkan data player !", Toast.LENGTH_SHORT).show();
                        etNama.requestFocus();
                    }
                    else
                    {
                        Toast.makeText(TambahActivity.this, "Berhasil menambahkan data player !", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}