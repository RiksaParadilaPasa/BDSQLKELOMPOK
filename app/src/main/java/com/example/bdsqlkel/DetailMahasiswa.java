package com.example.bdsqlkel;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailMahasiswa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        TextView txtId = (TextView)findViewById(R.id.idMahasiswa);
        TextView txtNama = (TextView)findViewById(R.id.nama);
        TextView txtTgl = (TextView)findViewById(R.id.tgl_lahir);
        TextView txtJenkel = (TextView)findViewById(R.id.jenkel);
        TextView txtAlamat = (TextView)findViewById(R.id.alamat);

        String id = getIntent().getStringExtra("id");
        String nama = getIntent().getStringExtra("nama");
        String tgl = getIntent().getStringExtra("tgl");
        String jenkel = getIntent().getStringExtra("jenkel");
        String alamat = getIntent().getStringExtra("alamat");

        txtId.setText(id);
        txtNama.setText(nama);
        txtTgl.setText(tgl);
        txtJenkel.setText(jenkel);
        txtAlamat.setText(alamat);



    }
}
