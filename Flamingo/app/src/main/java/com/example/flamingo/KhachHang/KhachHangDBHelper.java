package com.example.flamingo.KhachHang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class KhachHangDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = UtilsKhachHang.DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;
    public KhachHangDBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_KH_TABLE = "CREATE TABLE " + UtilsKhachHang.TABLE_KH + "("
                + UtilsKhachHang.COLUMN_KH_IDKH + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UtilsKhachHang.COLUMN_KH_TENKH + " TEXT, "
                + UtilsKhachHang.COLUMN_KH_SDT + " TEXT, "
                + UtilsKhachHang.COLUMN_KH_CCCD + " TEXT"
                +")";
        sqLiteDatabase.execSQL(CREATE_KH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UtilsKhachHang.TABLE_KH);
        onCreate(sqLiteDatabase);
    }
}
