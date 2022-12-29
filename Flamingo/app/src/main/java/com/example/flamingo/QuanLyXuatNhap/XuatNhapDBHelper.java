package com.example.flamingo.QuanLyXuatNhap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class XuatNhapDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = UtilsXuatNhap.DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;
    public XuatNhapDBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_XN_TABLE = "CREATE TABLE " + UtilsXuatNhap.TABLE_XN + "("
                + UtilsXuatNhap.COLUMN_XN_IDXN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UtilsXuatNhap.COLUMN_XN_XN + " TEXT, "
                + UtilsXuatNhap.COLUMN_XN_TENVP + " TEXT, "
                + UtilsXuatNhap.COLUMN_XN_NGAY + " TEXT, "
                + UtilsXuatNhap.COLUMN_XN_SOLUONG + " TEXT"
                +")";
        sqLiteDatabase.execSQL(CREATE_XN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UtilsXuatNhap.TABLE_XN);
        onCreate(sqLiteDatabase);
    }
}
