package com.example.flamingo.HoaDon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class HoaDonDataQuery {
    public static long insert(Context context, HoaDon hd)
    {
        HoaDonDBHelper hoaDonDBHelper = new HoaDonDBHelper(context);
        SQLiteDatabase sqLiteDatabase = hoaDonDBHelper.getReadableDatabase();
        ContentValues values = new ContentValues();


        values.put(UtilsHD.COLUMN_HD_SP,hd.soPhong);
        values.put(UtilsHD.COLUMN_HD_TENTHUE,hd.hoTenKH);
        values.put(UtilsHD.COLUMN_HD_GIOTHUE,hd.gio);
        values.put(UtilsHD.COLUMN_HD_NGAYTHUE,hd.ngayThang);
        values.put(UtilsHD.COLUMN_HD_CMND,hd.cmnd);
        values.put(UtilsHD.COLUMN_HD_SDT,hd.sdt);

        long rs = sqLiteDatabase.insert(UtilsHD.TABLE_HD,null,values);
        return(rs);
    }
    public static ArrayList<HoaDon> getAll(Context context)
    {
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        HoaDonDBHelper hoaDonDBHelper = new HoaDonDBHelper(context);
        SQLiteDatabase db = hoaDonDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + UtilsHD.TABLE_HD,null);
        cs.moveToFirst();
        while(!cs.isAfterLast())
        {
            int maHD = cs.getInt(0);
            String soPhong = cs.getString(1);
            String hoTenKH = cs.getString(2);

            String gio = cs.getString(3);
            String ngayThang = cs.getString(4);
            String cmnd = cs.getString(5);
            String sdt = cs.getString(6);


            lstHoaDon.add(new HoaDon(maHD,soPhong,hoTenKH,gio,ngayThang,cmnd,sdt));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstHoaDon;
    }
    public static boolean delete (Context context, int maHD)
    {
        HoaDonDBHelper hoaDonDBHelper = new HoaDonDBHelper(context);
        SQLiteDatabase sqLiteDatabase = hoaDonDBHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(UtilsHD.TABLE_HD,UtilsHD.COLUMN_HD_MAHD+"=?",new String[]{String.valueOf(maHD)});
        return (rs>0);
    }
    public static int update (Context context,HoaDon hd)
    {
        HoaDonDBHelper hoaDonDBHelper = new HoaDonDBHelper(context);
        SQLiteDatabase sqLiteDatabase = hoaDonDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UtilsHD.COLUMN_HD_SP,hd.soPhong);
        values.put(UtilsHD.COLUMN_HD_TENTHUE,hd.hoTenKH);

        values.put(UtilsHD.COLUMN_HD_GIOTHUE,hd.gio);
        values.put(UtilsHD.COLUMN_HD_NGAYTHUE,hd.ngayThang);
        values.put(UtilsHD.COLUMN_HD_CMND,hd.cmnd);
        values.put(UtilsHD.COLUMN_HD_SDT,hd.sdt);



        int rs = sqLiteDatabase.update(UtilsHD.TABLE_HD,values,UtilsHD.COLUMN_HD_MAHD+"=?",new String[]{String.valueOf(hd.maHD)});
        return (rs);
    }
}