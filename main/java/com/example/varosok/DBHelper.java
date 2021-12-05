package com.example.varosok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
// https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "varosok.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "varosok";
    private static final String COL_ID = "id";
    private static final String COL_NEV = "nev";
    private static final String COL_ORSZAG = "orszag";
    private static final String COL_LAKOSSAG = "lakossag";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE_NAME +" (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COL_NEV + " TEXT NOT NULL, " +
                COL_ORSZAG + " TEXT NOT NULL, " +
                COL_LAKOSSAG + " INTEGER NOT NULL " +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean rogzites(String nev, String orszag, int lakossag){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NEV, nev);
        values.put(COL_ORSZAG, orszag);
        values.put(COL_LAKOSSAG, lakossag);
        return db.insert(TABLE_NAME, null, values) != -1;
    }

    public Cursor listaz() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, new String[]{COL_ID,COL_NEV,COL_ORSZAG,COL_LAKOSSAG},
                null,null,null,null,null);
    }


}
