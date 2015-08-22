package com.example.andrey.sharedpreferences;

import android.net.Uri;

/**
 * Created by Andrey on 15.1.2015 г..
 */
public interface Constants {

    public  static  final String AUTHORITY = "com.example.andrey.sharedpreferences.CustomProvider";
    public  static  final String DATABASE_NAME = "Birthday.db";
    public  static  final String URL = "content://" + AUTHORITY + "/" + DATABASE_NAME;
    public  static  final Uri URI = Uri.parse((URL));

    public  static  final String DB_ID = "id";
    public  static  final String DB_BIRTHDAY = "birthday";
    public  static  final String DB_NAME = "name";

    public static final int DATABASE_VERSION = 1;
    public static  final String TABLE_NAME = "BirthdayTable";
}
