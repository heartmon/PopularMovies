package com.heartmon.android.popularmovies;

import android.app.Application;
import android.content.Context;
import android.util.Log;

//import com.heartmon.android.popularmovies.data.DaggerMovieRepositoryComponent;
//import com.heartmon.android.popularmovies.data.MovieRepositoryComponent;
//import com.heartmon.android.popularmovies.data.MovieRepositoryModule;

/**
 * Created by heartmon on 1/28/2017.
 */

public class PopularMoviesApplication extends Application {
//    private MovieRepositoryComponent mMovieRepositoryComponent;

    private AppComponent mAppComponent;

    public static PopularMoviesApplication get(Context context) {
        return (PopularMoviesApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        mMovieRepositoryComponent = DaggerMovieRepositoryComponent.builder()
//                .movieRepositoryModule(new MovieRepositoryModule())
//                .build();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

//    public MovieRepositoryComponent getMovieRepositoryComponent() {
//        return mMovieRepositoryComponent;
//    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
