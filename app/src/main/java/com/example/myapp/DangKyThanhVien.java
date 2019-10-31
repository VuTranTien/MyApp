package com.example.myapp;

public class DangKyThanhVien {
    private String Name;
    private String Email;
    private String Pass;
    private String UID;

    public DangKyThanhVien() {
        //Input Data
    }

    public DangKyThanhVien(String name, String email, String pass, String UID) {
        Name = name;
        Email = email;
        Pass = pass;
        this.UID = UID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
