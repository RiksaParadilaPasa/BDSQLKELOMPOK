package com.example.bdsqlkel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "db_mahasiswa";
    public static final int id = 0;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS tbl_mahasiswa(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                "nama varchar(200) NOT NULL," +
                "tgl_lahir datetime NOT NULL," +
                "jenkel varchar(200) NOT NULL," +
                "alamat varchar(200) NOT NULL" +
                ");";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = ("drop table if exists tbl_mahasiswa");
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Entity_Mahasiswa mahasiswa){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nama" , mahasiswa.getNama());
        cv.put("tgl_lahir" , mahasiswa.getTgl_lahir());
        cv.put("jenkel" , mahasiswa.getJenkel());
        cv.put("alamat" , mahasiswa.getAlamat());

        db.insert("tbl_mahasiswa",null,cv);
    }


    public List<Entity_Mahasiswa> selectMahasiswa() {
        ArrayList<Entity_Mahasiswa> userList = new ArrayList<Entity_Mahasiswa>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"*"};
        Cursor c = db.query("tbl_mahasiswa", columns, null, null, null, null, null);
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String nama = c.getString(1);
            String tgl_lahir = c.getString(2);
            String jenkel = c.getString(3);
            String alamat = c.getString(4);


            Entity_Mahasiswa mahasiswa = new Entity_Mahasiswa();
            mahasiswa.setId(id);
            mahasiswa.setNama(nama);
            mahasiswa.setTgl_lahir(tgl_lahir);
            mahasiswa.setJenkel(jenkel);
            mahasiswa.setAlamat(alamat);
            userList.add(mahasiswa);
        }
        return userList;
    }

    public void updateMahasiswa(Entity_Mahasiswa mahasiswa){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nama",mahasiswa.getNama());
        cv.put("tgl_lahir",mahasiswa.getTgl_lahir());
        cv.put("jenkel",mahasiswa.getJenkel());
        cv.put("alamat",mahasiswa.getAlamat());

        String where = "id = "+mahasiswa.getId()+"";

        db.update("tbl_mahasiswa",cv,where,null);
    }

    public void delete(int id){
        SQLiteDatabase db = getWritableDatabase();
        String where = "id = '"+id+"' ";
        db.delete("tbl_mahasiswa",where,null);
    }


}
