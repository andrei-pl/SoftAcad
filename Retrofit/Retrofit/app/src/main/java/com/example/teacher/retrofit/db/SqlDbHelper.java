package com.example.teacher.retrofit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.teacher.retrofit.api.models.LocationInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teacher on 1/19/2015.
 */
public class SqlDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_SUNRISE_AND_SUNSET = "SunriseAndSunset";

    public SqlDbHelper(Context context) {
        super(context, "SunriseSunsetDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_SUNRISE_AND_SUNSET + "("+
                "sunrise text," +
                "sunset text," +
                "_id integer primary key autoincrement" +
         " )");

    }

    public long insert(LocationInfo data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues(2);
        cv.put("sunrise", data.getSunrise());
        cv.put("sunset", data.getSunset());
        return db.insert(TABLE_SUNRISE_AND_SUNSET, null, cv);
    }

    public List<LocationInfo> getAllData() {
        List<LocationInfo> results = new ArrayList<LocationInfo>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(false, TABLE_SUNRISE_AND_SUNSET, new String[] {"sunrise", "sunset"}, null, null, null, null, null, null);
        while(c.moveToNext()) {
            results.add(new LocationInfo(c.getString(0), c.getString(1)));
        }
        c.close();
        db.close();
        return results;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
