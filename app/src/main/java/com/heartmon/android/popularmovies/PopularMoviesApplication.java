package com.heartmon.android.popularmovies;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.joda.time.LocalDateTime;


/**
 * Created by heartmon on 1/28/2017.
 */

/*
Use code from
 https://github.com/frogermcs/GithubClient/blob/1bf53a2a36c8a85435e877847b987395e482ab4a/app/src/main/java/frogermcs/io/githubclient/GithubClientApplication.java
as starter code
*/

public class PopularMoviesApplication extends Application {
//    private MovieRepositoryComponent mMovieRepositoryComponent;

    private AppComponent mAppComponent;

    public static PopularMoviesApplication get(Context context) {
        return (PopularMoviesApplication) context.getApplicationContext();
    }

    static {
        // Build the local caches inside Joda Time immediately instead of lazily
        new LocalDateTime();
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
