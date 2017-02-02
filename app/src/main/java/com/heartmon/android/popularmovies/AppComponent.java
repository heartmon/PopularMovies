package com.heartmon.android.popularmovies;

import android.app.Application;

import com.heartmon.android.popularmovies.data.MovieRepository;
import com.heartmon.android.popularmovies.data.MovieRepositoryComponent;
import com.heartmon.android.popularmovies.data.MovieRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by heartmon on 1/28/2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    //Dependencies below should be visible out of the component
    Application getApplication();

    // Subcomponent
    MovieRepositoryComponent plus(MovieRepositoryModule movieRepositoryModule);

//    void inject(MainActivity activity);
//    void inject(MovieRemoteDataSource appRemoteDataStore);
}
