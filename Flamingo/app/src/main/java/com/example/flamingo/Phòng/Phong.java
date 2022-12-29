package com.example.flamingo.Phòng;

public class Phong {
    int idPhong;
    String soPhong;
    String tinhTrang;
    String loaiPhong;
    String trangThai;

    public Phong(int idPhong, String soPhong, String tinhTrang,  String loaiPhong, String trangThai) {
        this.idPhong = idPhong;
        this.soPhong = soPhong;
        this.tinhTrang = tinhTrang;
        this.trangThai = trangThai;
        this.loaiPhong = loaiPhong;
    }


    public int getIdPhong() {
        return idPhong;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
