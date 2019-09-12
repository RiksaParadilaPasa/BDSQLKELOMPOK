package com.example.bdsqlkel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LihatData extends AppCompatActivity implements View.OnClickListener,RecyclerViewAdapter.OnUserClickListener {

    RecyclerView reMahasiswa;
    RecyclerView.LayoutManager layoutManager;
    List<Entity_Mahasiswa> listMahasiswa;
    ImageButton btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        reMahasiswa=(RecyclerView)findViewById(R.id.reDataMahasiswa);
        layoutManager=new LinearLayoutManager(LihatData.this);
        reMahasiswa.setLayoutManager(layoutManager);
        setupRecyclerView();

        btnback = (ImageButton)findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LihatData.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void setupRecyclerView() {
        DatabaseHelper db=new DatabaseHelper(LihatData.this);
        listMahasiswa=db.selectMahasiswa();
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(LihatData.this,listMahasiswa,this);
        reMahasiswa.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        //diperlukan untuk constructor kosong
    }

    @Override
    public void onUserClick(Entity_Mahasiswa current, String action) {
        if (action == "update"){
            Intent intent = new Intent(LihatData.this , EditActivity.class);
            int idNya = current.getId();
            String id = String.valueOf(idNya);
            intent.putExtra("id",id);
            intent.putExtra("nama",current.getNama());
            intent.putExtra("tgl",current.getTgl_lahir());
            intent.putExtra("jenkel",current.getJenkel());
            intent.putExtra("alamat",current.getAlamat());
            startActivity(intent);
        }

        if (action == "delete"){
            DatabaseHelper db = new DatabaseHelper(this);
            int id = current.getId();
            String nama = current.getNama();
            db.delete(id);
            Toast.makeText(this, nama+" "+"berhasil Di hapus", Toast.LENGTH_SHORT).show();
            setupRecyclerView();

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        layoutManager=new LinearLayoutManager(LihatData.this);
        reMahasiswa.setLayoutManager(layoutManager);
        setupRecyclerView();
    }
}
