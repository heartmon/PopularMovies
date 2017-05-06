package com.heartmon.android.popularmovies.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.heartmon.android.popularmovies.data.source.local.MovieContract.FavoriteEntry;

/**
 * Created by heartmon on 5/5/2017.
 */

public class MovieDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "Movie.db";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FavoriteEntry.TABLE_NAME + " (" +
                    FavoriteEntry.COLUMN_NAME_ID + " LONG NOT NULL PRIMARY KEY, " +
                    FavoriteEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FavoriteEntry.COLUMN_NAME_POSTER_PATH + " TEXT," +
                    FavoriteEntry.COLUMN_NAME_BACKDROP_PATH + " TEXT," +
                    FavoriteEntry.COLUMN_NAME_OVERVIEW + " TEXT," +
                    FavoriteEntry.COLUMN_NAME_RATING + " DECIMAL(3,1)," +
                    FavoriteEntry.COLUMN_NAME_YEAR + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FavoriteEntry.TABLE_NAME;

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
