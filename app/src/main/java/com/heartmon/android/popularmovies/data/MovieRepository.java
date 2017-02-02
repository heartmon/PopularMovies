package com.heartmon.android.popularmovies.data;

import com.heartmon.android.popularmovies.data.model.MovieResult;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by heartmon on 1/28/2017.
 */

public class MovieRepository implements MovieDataSource {
    private final MovieDataSource mMovieRemoteDataSource;

    @Inject
    public MovieRepository(MovieDataSource mMovieRemoteDataSource) {
        this.mMovieRemoteDataSource = mMovieRemoteDataSource;
    }

    @Override
    public Observable<MovieResult> getPopularMovies() {
        return mMovieRemoteDataSource.getPopularMovies();
    }

    @Override
    public Observable<MovieResult> getTopRatedMovies() {
        return mMovieRemoteDataSource.getTopRatedMovies();
    }
}
