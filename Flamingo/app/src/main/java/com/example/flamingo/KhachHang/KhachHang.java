package com.example.flamingo.KhachHang;

public class KhachHang {
    int idKH;
    String tenKH;
    String sDT;
    String cCCD;
    public KhachHang(int idKH, String tenKH, String sDT, String cCCD) {
        this.idKH = idKH;
        this.tenKH = tenKH;
        this.sDT = sDT;
        this.cCCD = cCCD;
    }

    public int getIdKH() {
        return idKH;
    }


    public String getTenKH() {
        return tenKH;
    }

    public String getsDT() {
        return sDT;
    }

    public String getcCCD() {
        return cCCD;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public void setcCCD(String cCCD) {
        this.cCCD = cCCD;
    }
}
