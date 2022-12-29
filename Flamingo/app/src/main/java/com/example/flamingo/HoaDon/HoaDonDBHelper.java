package com.example.flamingo.HoaDon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HoaDonDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = UtilsHD.DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;
    public HoaDonDBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_HD_TABLE = "CREATE TABLE " + UtilsHD.TABLE_HD + "("
                + UtilsHD.COLUMN_HD_MAHD + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UtilsHD.COLUMN_HD_SP + " TEXT, "
                + UtilsHD.COLUMN_HD_TENTHUE + " TEXT, "
                + UtilsHD.COLUMN_HD_GIOTHUE + " TEXT, "
                + UtilsHD.COLUMN_HD_NGAYTHUE + " TEXT, "
                + UtilsHD.COLUMN_HD_CMND + " TEXT, "
                + UtilsHD.COLUMN_HD_SDT + " TEXT"
                +")";
        sqLiteDatabase.execSQL(CREATE_HD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UtilsHD.TABLE_HD);
        onCreate(sqLiteDatabase);
    }
}
