package com.example.flamingo.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DuLieuUSERS.db";
    public static final String TABLE_NAME = "DuLieuUSERS";
    public static final int VERSION = 1;
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_SEX = "sex";
    private static final String COLUMN_CHUCVU = "chucvu";
    private static final String TABLE_CREATE = "create table DuLieuUSERS (username text primary key not null, " +
            "password text not null, email text not null, phone text not null, sex int, chucvu text not null);";

    SQLiteDatabase db;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME, null, VERSION);
    }

    public void insertUser(user kh)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,kh.getUserName());
        values.put(COLUMN_PASSWORD,kh.getPassWord());
        values.put(COLUMN_EMAIL,kh.getEmail());
        values.put(COLUMN_PHONE,kh.getPhone());
        values.put(COLUMN_SEX,kh.getSex());
        values.put(COLUMN_CHUCVU,kh.getChucvu());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public boolean searchTK(String tk)
    {
        db = this.getWritableDatabase();
        String query = "select username from " + TABLE_NAME;
        Cursor cs = db.rawQuery(query,null);
        String username;
        if (cs.moveToFirst())
        {
            do{
                username = cs.getString(0);
                if(username.equals(tk)){
                    db.close();
                    return true;
                }
            } while(cs.moveToNext());
        }
        db.close();
        return false;
    }
    public String searchpass(String tk)
    {
        db = this.getWritableDatabase();
        String query = "select username, password from " + TABLE_NAME;
        Cursor cs = db.rawQuery(query,null);
        String username;
        String password = "not found";
        if (cs.moveToFirst())
        {
            do{
                username = cs.getString(0);
                if(username.equals(tk)){
                    password = cs.getString(1);
                }
            } while(cs.moveToNext());
        }
        db.close();
        return password;
    }

}
