package com.example.bdsqlkel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final String id = getIntent().getStringExtra("id");
        String nama = getIntent().getStringExtra("nama");
        String tgl = getIntent().getStringExtra("tgl");
        String jenkel = getIntent().getStringExtra("jenkel");
        String alamat = getIntent().getStringExtra("alamat");

        final EditText editNama = (EditText)findViewById(R.id.editNama);
        final EditText editTgl = (EditText)findViewById(R.id.editTglLahir);
        final EditText editJenkel = (EditText)findViewById(R.id.editJenkel);
        final EditText editAlamat = (EditText)findViewById(R.id.editAlamat);

        editNama.setText(nama);
        editNama.requestFocus();

        editTgl.setText(tgl);
        editJenkel.setText(jenkel);
        editAlamat.setText(alamat);

        Button btnUpdate = (Button)findViewById(R.id.btnUpdateData);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(EditActivity.this);
                Entity_Mahasiswa mahasiswa = new Entity_Mahasiswa();

                int IdYangDiEdit = Integer.parseInt(id);
                String namaEdit = editNama.getText().toString();
                String tglEdit = editTgl.getText().toString();
                String jenkelEdit = editJenkel.getText().toString();
                String alamatEdit = editAlamat.getText().toString();

                mahasiswa.setId(IdYangDiEdit);
                mahasiswa.setNama(namaEdit);
                mahasiswa.setTgl_lahir(tglEdit);
                mahasiswa.setJenkel(jenkelEdit);
                mahasiswa.setAlamat(alamatEdit);

                db.updateMahasiswa(mahasiswa);
                finish();
                Toast.makeText(EditActivity.this, "Berhasil Edit Data", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
