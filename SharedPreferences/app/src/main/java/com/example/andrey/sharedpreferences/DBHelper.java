package com.example.andrey.sharedpreferences;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andrey on 15.1.2015 г..
 */
public class DBHelper extends SQLiteOpenHelper implements Constants {

    private static  final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ( " +
            Constants.DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Constants.DB_NAME + " TEXT NOT NULL, " +
            Constants.DB_BIRTHDAY + " TEXT NOT NULL);";


    public DBHelper(Context context) {
        super(context, Constants.DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
