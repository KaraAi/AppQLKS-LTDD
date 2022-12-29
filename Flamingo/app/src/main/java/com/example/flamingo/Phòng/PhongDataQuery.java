package com.example.flamingo.Phòng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PhongDataQuery {
    public static long insert(Context context, Phong phong)
    {
        PhongDBHelper phongDBHelper = new PhongDBHelper(context);
        SQLiteDatabase sqLiteDatabase = phongDBHelper.getReadableDatabase();
        ContentValues values = new ContentValues();


        values.put(UtilsPhong.COLUMN_PHONG_SP,phong.soPhong);
        values.put(UtilsPhong.COLUMN_PHONG_LOAIPHONG,phong.loaiPhong);
        values.put(UtilsPhong.COLUMN_PHONG_TINHTRANG,phong.tinhTrang);
        values.put(UtilsPhong.COLUMN_PHONG_TRANGTHAI,phong.trangThai);




        long rs = sqLiteDatabase.insert(UtilsPhong.TABLE_PHONG,null,values);
        return(rs);
    }
    public static ArrayList<Phong> getAll(Context context)
    {
        ArrayList<Phong> lstPhong = new ArrayList<>();
        PhongDBHelper phongDBHelper = new PhongDBHelper(context);
        SQLiteDatabase db = phongDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + UtilsPhong.TABLE_PHONG,null);
        cs.moveToFirst();
        while(!cs.isAfterLast())
        {
            int idPhong = cs.getInt(0);
            String soPhong = cs.getString(1);
            String loaiPhong = cs.getString(2);
            String tinhTrang = cs.getString(3);
            String trangThai = cs.getString(4);

            lstPhong.add(new Phong(idPhong,soPhong,loaiPhong,tinhTrang,trangThai));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstPhong;
    }
    public static boolean delete (Context context, int idPhong)
    {
        PhongDBHelper phongDBHelper = new PhongDBHelper(context);
        SQLiteDatabase sqLiteDatabase = phongDBHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(UtilsPhong.TABLE_PHONG,UtilsPhong.COLUMN_PHONG_IDPHONG+"=?",new String[]{String.valueOf(idPhong)});
        return (rs>0);
    }
    public static int update (Context context,Phong phong)
    {
        PhongDBHelper phongDBHelper = new PhongDBHelper(context);
        SQLiteDatabase sqLiteDatabase = phongDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UtilsPhong.COLUMN_PHONG_SP,phong.soPhong);
        values.put(UtilsPhong.COLUMN_PHONG_LOAIPHONG,phong.loaiPhong);
        values.put(UtilsPhong.COLUMN_PHONG_TINHTRANG,phong.tinhTrang);
        values.put(UtilsPhong.COLUMN_PHONG_TRANGTHAI,phong.trangThai);




        int rs = sqLiteDatabase.update(UtilsPhong.TABLE_PHONG,values,UtilsPhong.COLUMN_PHONG_IDPHONG+"=?",new String[]{String.valueOf(phong.idPhong)});
        return (rs);
    }
}