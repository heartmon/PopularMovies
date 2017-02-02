package com.heartmon.android.popularmovies.data;

import com.heartmon.android.popularmovies.util.DataScope;

import dagger.Subcomponent;

/**
 * Created by heartmon on 1/28/2017.
 */

@DataScope
@Subcomponent(modules = {MovieRepositoryModule.class})
public interface MovieRepositoryComponent {
    MovieRepository getMovieRepository();
}
