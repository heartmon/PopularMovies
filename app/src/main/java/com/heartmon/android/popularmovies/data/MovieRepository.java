package com.heartmon.android.popularmovies.data;

import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.data.model.MovieResult;
import com.heartmon.android.popularmovies.data.model.MovieReviewResult;
import com.heartmon.android.popularmovies.data.model.MovieVideoResult;
import com.heartmon.android.popularmovies.data.source.local.MovieLocalDataSource;
import com.heartmon.android.popularmovies.util.Local;
import com.heartmon.android.popularmovies.util.Remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by heartmon on 1/28/2017.
 */

public class MovieRepository implements MovieDataSource {
    private final MovieDataSource mMovieRemoteDataSource;
    private final MovieDataSource mMovieLocalDataSource;

    @Inject
    public MovieRepository(@Remote MovieDataSource mMovieRemoteDataSource, @Local MovieDataSource mMovieLocalDataSource) {
        this.mMovieRemoteDataSource = mMovieRemoteDataSource;
        this.mMovieLocalDataSource = mMovieLocalDataSource;
    }

    @Override
    public Observable<MovieResult> getPopularMovies() {
        return mMovieRemoteDataSource.getPopularMovies();
    }

    @Override
    public Observable<MovieResult> getTopRatedMovies() {
        return mMovieRemoteDataSource.getTopRatedMovies();
    }

    @Override
    public Observable<MovieVideoResult> getVideoTrailer(int videoId) {
        return mMovieRemoteDataSource.getVideoTrailer(videoId);
    }

    @Override
    public Observable<MovieReviewResult> getReviews(int videoId) {
        return mMovieRemoteDataSource.getReviews(videoId);
    }

    @Override
    public Observable addToFavorite(Movie movie) {
        return mMovieLocalDataSource.addToFavorite(movie);
    }

    @Override
    public Observable removeFromFavorite(Movie movie) {
        return mMovieLocalDataSource.removeFromFavorite(movie);
    }

    @Override
    public Observable getFavoriteMovie() {
        return mMovieLocalDataSource.getFavoriteMovie();
    }

    @Override
    public Observable getFavoriteMovie(Integer movieId) {
        return mMovieLocalDataSource.getFavoriteMovie(movieId);
    }
}
