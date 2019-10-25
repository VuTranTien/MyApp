package com.example.myapp;

public class One_line_message {
    protected String Name;
    protected String Lastmess;
    protected int Img;
    protected boolean status;

    public One_line_message(String name, String lastmess, int img, boolean status) {
        Name = name;
        Lastmess = lastmess;
        Img = img;
        this.status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastmess() {
        return Lastmess;
    }

    public void setLastmess(String lastmess) {
        Lastmess = lastmess;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
