package com.heartmon.android.popularmovies.ui.mainscreen;

import com.heartmon.android.popularmovies.util.ActivityScope;
import com.heartmon.android.popularmovies.data.MovieRepositoryComponent;

import dagger.Component;

/**
 * Created by heartmon on 1/22/2017.
 */

@ActivityScope
@Component(dependencies = {MovieRepositoryComponent.class}, modules = {MainActivityModule.class})
public interface MainScreenComponent {
    void inject(MainActivity activity);
}