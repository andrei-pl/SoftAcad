package com.mentormate.academy.lecture14;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by Stefan.Doychev on 13.01.2015.
 */
public class BirthProvider extends ContentProvider {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {

        Context context = getContext();
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();

        if(database == null)
            return false;
        else
            return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(dbHelper.getTableName());

        if (sortOrder == null || sortOrder == ""){
            // No sorting-> sort on names by default
            sortOrder = Constants.DB_NAME;
        }

        /*
        SQLiteDatabase db = dbName, the table name to compile the query against.
        String[] projection = columnNames, a list of which table columns to return. Passing "null" will return all.
        String selection = whereClause, filter for the selection of data, null will select all data.
        String[] selectionArgs = you may include ?s in the "whereClause"". These placeholders will get replaced by the values from the selectionArgs array.
        String[] groupBy = a filter declaring how to group rows, null will cause the rows to not be grouped.
        String[] having	= filter for the groups, null means no filter.
        String sortOrder = orderBy, table columns which will be used to order the data, null means no ordering.
        */
        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        // Register to watch a content URI for changes
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {

        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long row = database.insert(dbHelper.getTableName(), "", values);

        // If record is added successfully
        if(row > 0) {
            Uri newUri = ContentUris.withAppendedId(Constants.CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Fail to add a new record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int count = database.delete(dbHelper.getTableName(), selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int count = 0;
        count = database.update(dbHelper.getTableName(), values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
