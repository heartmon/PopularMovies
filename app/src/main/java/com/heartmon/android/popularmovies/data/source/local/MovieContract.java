package com.heartmon.android.popularmovies.data.source.local;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by heartmon on 5/5/2017.
 */

public class MovieContract {
    private MovieContract() {
    }

    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "com.heartmon.android.popularmovies";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_FAVORITES = "favorites";

    public static class FavoriteEntry implements BaseColumns {
        public static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/" + PATH_FAVORITES;
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITES).build();

        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_POSTER_PATH = "poster_path";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_OVERVIEW = "overview";
        public static final String COLUMN_NAME_BACKDROP_PATH = "backdrop_path";
    }
}
