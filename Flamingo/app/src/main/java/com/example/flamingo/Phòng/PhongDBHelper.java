package com.example.flamingo.Phòng;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhongDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = UtilsPhong.DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;
    public PhongDBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PHONG_TABLE = "CREATE TABLE " + UtilsPhong.TABLE_PHONG + "("
                + UtilsPhong.COLUMN_PHONG_IDPHONG + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UtilsPhong.COLUMN_PHONG_SP + " TEXT, "
                + UtilsPhong.COLUMN_PHONG_LOAIPHONG + " TEXT, "
                + UtilsPhong.COLUMN_PHONG_TINHTRANG + " TEXT, "
                + UtilsPhong.COLUMN_PHONG_TRANGTHAI + " TEXT"
                +")";
        sqLiteDatabase.execSQL(CREATE_PHONG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UtilsPhong.TABLE_PHONG);
        onCreate(sqLiteDatabase);
    }
}
