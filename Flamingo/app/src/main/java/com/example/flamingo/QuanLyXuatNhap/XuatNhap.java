package com.example.flamingo.QuanLyXuatNhap;

public class XuatNhap {
    int idXN;
    String xuatNhap;
    String tenVatPham;
    String ngayXN;
    String soLuong;

    public XuatNhap(int idXN, String xuatNhap, String tenVatPham,  String ngayXN, String soLuong) {
        this.idXN = idXN;
        this.xuatNhap = xuatNhap;
        this.tenVatPham = tenVatPham;
        this.ngayXN = ngayXN;
        this.soLuong = soLuong;
    }


    public int getIdXN() {
        return idXN;
    }

    public String getXuatNhap() {
        return xuatNhap;
    }

    public String getTenVatPham() {
        return tenVatPham;
    }

    public String getNgayXN() {
        return ngayXN;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setXuatNhap(String xuatNhap) {
        this.xuatNhap = xuatNhap;
    }

    public void setTenVatPham(String tenVatPham) {
        this.tenVatPham = tenVatPham;
    }

    public void setNgayXN(String ngayXN) {
        this.ngayXN = ngayXN;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }
}
