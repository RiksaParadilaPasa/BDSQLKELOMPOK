package com.example.bdsqlkel;

public class Entity_Mahasiswa {

    int id;
    String nama , tgl_lahir , jenkel , alamat;

    public Entity_Mahasiswa(int id, String nama, String tgl_lahir, String jenkel, String alamat) {
        this.id = id;
        this.nama = nama;
        this.tgl_lahir = tgl_lahir;
        this.jenkel = jenkel;
        this.alamat = alamat;
    }

    public Entity_Mahasiswa(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getJenkel() {
        return jenkel;
    }

    public void setJenkel(String jenkel) {
        this.jenkel = jenkel;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
