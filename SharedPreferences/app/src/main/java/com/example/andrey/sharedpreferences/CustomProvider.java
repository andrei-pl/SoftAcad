package com.example.andrey.sharedpreferences;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by Andrey on 15.1.2015 г..
 */
public class CustomProvider extends ContentProvider {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    @Override
    public boolean onCreate() {

        Context context = getContext();
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();

        if(database == null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(Constants.TABLE_NAME);

        if(sortOrder == null || sortOrder == ""){
            sortOrder = Constants.DB_ID;
        }

        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri();

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long row = database.insert(Constants.TABLE_NAME, "", values);

        Uri responseUri = ContentUris.withAppendedId(Constants.URI, row);
        getContext().getContentResolver().notifyChange(responseUri, null);

        return responseUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        count = database.delete(Constants.TABLE_NAME, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        count = database.update(Constants.TABLE_NAME, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
    }
}
