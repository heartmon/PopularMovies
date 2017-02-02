package com.heartmon.android.popularmovies.ui.mainscreen;

import com.heartmon.android.popularmovies.util.ActivityScope;
import com.heartmon.android.popularmovies.data.MovieRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by heartmon on 1/22/2017.
 */

@Module
public class MainActivityModule {

    private MainScreenContract.View mainActivity;

    public MainActivityModule(MainScreenContract.View mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainScreenContract.View provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    MainScreenContract.Presenter provideMainScreenPresenter(MovieRepository movieRepository) {
        return new MainScreenPresenter(mainActivity, movieRepository);
    }

}