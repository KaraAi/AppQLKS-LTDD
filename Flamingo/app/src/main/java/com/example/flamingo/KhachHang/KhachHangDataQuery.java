package com.example.flamingo.KhachHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class KhachHangDataQuery {
    public static long insert(Context context, KhachHang phong)
    {
        KhachHangDBHelper phongDBHelper = new KhachHangDBHelper(context);
        SQLiteDatabase sqLiteDatabase = phongDBHelper.getReadableDatabase();
        ContentValues values = new ContentValues();


        values.put(UtilsKhachHang.COLUMN_KH_TENKH,phong.tenKH);
        values.put(UtilsKhachHang.COLUMN_KH_SDT,phong.sDT);
        values.put(UtilsKhachHang.COLUMN_KH_CCCD,phong.cCCD);

        long rs = sqLiteDatabase.insert(UtilsKhachHang.TABLE_KH,null,values);
        return(rs);
    }
    public static ArrayList<KhachHang> getAll(Context context)
    {
        ArrayList<KhachHang> lstPhong = new ArrayList<>();
        KhachHangDBHelper phongDBHelper = new KhachHangDBHelper(context);
        SQLiteDatabase db = phongDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + UtilsKhachHang.TABLE_KH,null);
        cs.moveToFirst();
        while(!cs.isAfterLast())
        {
            int idKH = cs.getInt(0);
            String tenKH = cs.getString(1);
            String sdt = cs.getString(2);
            String cCCD = cs.getString(3);


            lstPhong.add(new KhachHang(idKH,tenKH,sdt,cCCD));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstPhong;
    }
    public static boolean delete (Context context, int idKH)
    {
        KhachHangDBHelper phongDBHelper = new KhachHangDBHelper(context);
        SQLiteDatabase sqLiteDatabase = phongDBHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(UtilsKhachHang.TABLE_KH,UtilsKhachHang.COLUMN_KH_IDKH+"=?",new String[]{String.valueOf(idKH)});
        return (rs>0);
    }
    public static int update (Context context,KhachHang phong)
    {
        KhachHangDBHelper phongDBHelper = new KhachHangDBHelper(context);
        SQLiteDatabase sqLiteDatabase = phongDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UtilsKhachHang.COLUMN_KH_TENKH,phong.tenKH);
        values.put(UtilsKhachHang.COLUMN_KH_SDT,phong.sDT);
        values.put(UtilsKhachHang.COLUMN_KH_CCCD,phong.cCCD);





        int rs = sqLiteDatabase.update(UtilsKhachHang.COLUMN_KH_CCCD,values,UtilsKhachHang.COLUMN_KH_IDKH+"=?",new String[]{String.valueOf(phong.idKH)});
        return (rs);
    }
}
