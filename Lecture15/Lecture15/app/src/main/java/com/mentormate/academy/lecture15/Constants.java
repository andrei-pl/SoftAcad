package com.mentormate.academy.lecture15;

import android.net.Uri;

/**
 * Created by Stefan.Doychev on 13.01.2015.
 */
public interface Constants {

    // Constants for the content provider
    public static final String AUTHORITY            = "com.mentormate.academy.lecture14.BirthProvider";
    public static final String DATABASE_NAME        = "Birthday.db";
    public static final String URL                  = "content://" + AUTHORITY + "/" + DATABASE_NAME;
    public static final Uri CONTENT_URI             = Uri.parse(URL);

    public final static String LOCAL_DATABASE_PATH          = "/data/com.mentormate.academy.lecture15.app/databases/";
    public final static String IMPORTED_DATABASE_PATH       = "/Import/";

    // Constants for the database
    public static final String DB_ID                = "id";
    public static final String DB_NAME              = "name";
    public static final String DB_BIRTHDAY          = "birthday";

    // Constants used in content URI
    public static final int FRIENDS                 = 1;
    public static final int FRIENDS_ID              = 2;
}
