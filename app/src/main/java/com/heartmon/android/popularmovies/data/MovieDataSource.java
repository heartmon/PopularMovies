package com.heartmon.android.popularmovies.data;

import com.heartmon.android.popularmovies.data.model.MovieResult;

import io.reactivex.Observable;

/**
 * Created by heartmon on 1/28/2017.
 */

public interface MovieDataSource {
    Observable<MovieResult> getPopularMovies();
    Observable<MovieResult> getTopRatedMovies();
}
