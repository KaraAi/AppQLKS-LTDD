package com.example.flamingo.QuanLyXuatNhap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class XuatNhapDataQuery {
    public static long insert(Context context, XuatNhap xn)
    {
        XuatNhapDBHelper xuatNhapDBHelper = new XuatNhapDBHelper(context);
        SQLiteDatabase sqLiteDatabase = xuatNhapDBHelper.getReadableDatabase();
        ContentValues values = new ContentValues();



        values.put(UtilsXuatNhap.COLUMN_XN_XN,xn.xuatNhap);
        values.put(UtilsXuatNhap.COLUMN_XN_TENVP,xn.tenVatPham);
        values.put(UtilsXuatNhap.COLUMN_XN_NGAY,xn.ngayXN);
        values.put(UtilsXuatNhap.COLUMN_XN_SOLUONG,xn.soLuong);




        long rs = sqLiteDatabase.insert(UtilsXuatNhap.TABLE_XN,null,values);
        return(rs);
    }
    public static ArrayList<XuatNhap> getAll(Context context)
    {
        ArrayList<XuatNhap> lstXN = new ArrayList<>();
        XuatNhapDBHelper xuatNhapDBHelper = new XuatNhapDBHelper(context);
        SQLiteDatabase db = xuatNhapDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + UtilsXuatNhap.TABLE_XN,null);
        cs.moveToFirst();
        while(!cs.isAfterLast())
        {
            int idXN = cs.getInt(0);
            String xuatNhap = cs.getString(1);
            String tenVatPham = cs.getString(2);
            String ngayXN = cs.getString(3);
            String soLuong = cs.getString(4);

            lstXN.add(new XuatNhap(idXN,xuatNhap,tenVatPham,ngayXN,soLuong));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstXN;
    }
    public static boolean delete (Context context, int idXN)
    {
        XuatNhapDBHelper xuatNhapDBHelper = new XuatNhapDBHelper(context);
        SQLiteDatabase sqLiteDatabase = xuatNhapDBHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(UtilsXuatNhap.TABLE_XN,UtilsXuatNhap.COLUMN_XN_IDXN+"=?",new String[]{String.valueOf(idXN)});
        return (rs>0);
    }
    public static int update (Context context,XuatNhap xuatNhap)
    {
        XuatNhapDBHelper xuatNhapDBHelper = new XuatNhapDBHelper(context);
        SQLiteDatabase sqLiteDatabase = xuatNhapDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UtilsXuatNhap.COLUMN_XN_XN,xuatNhap.xuatNhap);
        values.put(UtilsXuatNhap.COLUMN_XN_TENVP,xuatNhap.tenVatPham);
        values.put(UtilsXuatNhap.COLUMN_XN_NGAY,xuatNhap.ngayXN);
        values.put(UtilsXuatNhap.COLUMN_XN_SOLUONG,xuatNhap.soLuong);




        int rs = sqLiteDatabase.update(UtilsXuatNhap.TABLE_XN,values,UtilsXuatNhap.COLUMN_XN_IDXN+"=?",new String[]{String.valueOf(xuatNhap.idXN)});
        return (rs);
    }
}