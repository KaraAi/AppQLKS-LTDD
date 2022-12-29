package com.example.flamingo.HoaDon;

public class HoaDon {
    int maHD;
    String soPhong;
    String hoTenKH;
    String cmnd;
    String sdt;
    String gio;
    String ngayThang;

    public HoaDon(int maHD, String soPhong, String hoTenKH,  String gio, String ngayThang,String cmnd, String sdt) {
        this.maHD = maHD;
        this.soPhong = soPhong;
        this.hoTenKH = hoTenKH;

        this.gio = gio;
        this.ngayThang = ngayThang;
        this.cmnd = cmnd;
        this.sdt = sdt;
    }


    public int getMaHD() {
        return maHD;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public String getHoTenKH() {
        return hoTenKH;
    }

    public void setHoTenKH(String hoTenKH) {
        this.hoTenKH = hoTenKH;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }
}
