package com.heartmon.android.popularmovies.data.source.remote;

import android.util.Log;

import com.heartmon.android.popularmovies.data.MovieDataSource;
import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.data.model.MovieResult;
import com.heartmon.android.popularmovies.data.model.MovieReviewResult;
import com.heartmon.android.popularmovies.data.model.MovieService;
import com.heartmon.android.popularmovies.data.model.MovieVideoResult;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by heartmon on 1/28/2017.
 */

public class MovieRemoteDataSource implements MovieDataSource {
    private final String LOG_TAG = MovieRemoteDataSource.class.getSimpleName();

    private Retrofit retrofit;

    @Inject
    public MovieRemoteDataSource(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<MovieResult> getPopularMovies() {
        return retrofit.create(MovieService.class).getPopularMovieList();
    }

    @Override
    public Observable<MovieResult> getTopRatedMovies() {
        return retrofit.create(MovieService.class).getTopRatedMovies();
    }

    @Override
    public Observable<MovieVideoResult> getVideoTrailer(int videoId) {
        return retrofit.create(MovieService.class).getVideoTrailer(videoId);
    }

    @Override
    public Observable<MovieReviewResult> getReviews(int videoId) {
        return retrofit.create(MovieService.class).getReviews(videoId);
    }

    @Override
    public Observable addToFavorite(Movie movie) {
        return null;
    }

    @Override
    public Observable removeFromFavorite(Movie movie) {
            return null;
    }

    @Override
    public Observable getFavoriteMovie() {
        return null;
    }

    @Override
    public Observable getFavoriteMovie(Integer movieId) {
        return null;
    }

    private interface MovieService {
        @GET("/3/movie/popular")
        Observable<MovieResult> getPopularMovieList();

        @GET("/3/movie/top_rated")
        Observable<MovieResult> getTopRatedMovies();

        @GET("/3/movie/{id}/videos")
        Observable<MovieVideoResult> getVideoTrailer(@Path("id") int videoId);

        @GET("/3/movie/{id}/reviews")
        Observable<MovieReviewResult> getReviews(@Path("id") int videoId);

    }
}
