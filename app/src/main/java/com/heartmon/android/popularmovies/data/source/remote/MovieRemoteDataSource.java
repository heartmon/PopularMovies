package com.heartmon.android.popularmovies.data.source.remote;

import android.util.Log;

import com.heartmon.android.popularmovies.data.MovieDataSource;
import com.heartmon.android.popularmovies.data.model.MovieResult;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;

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

    private interface MovieService {
        @GET("/3/movie/popular")
        Observable<MovieResult> getPopularMovieList();

        @GET("/3/movie/top_rated")
        Observable<MovieResult> getTopRatedMovies();
    }
}
