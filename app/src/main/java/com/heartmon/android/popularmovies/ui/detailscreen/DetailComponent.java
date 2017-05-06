package com.heartmon.android.popularmovies.ui.detailscreen;

import com.heartmon.android.popularmovies.data.MovieRepositoryComponent;
import com.heartmon.android.popularmovies.ui.mainscreen.MainActivity;
import com.heartmon.android.popularmovies.ui.mainscreen.MainActivityModule;
import com.heartmon.android.popularmovies.util.ActivityScope;

import dagger.Component;

/**
 * Created by heartmon on 1/31/2017.
 */

@ActivityScope
@Component(dependencies = {MovieRepositoryComponent.class}, modules = {DetailModule.class})
public interface DetailComponent {
    void inject(DetailActivity activity);
    void inject(DetailFragment fragment);
    void inject(ReviewFragment fragment);
}