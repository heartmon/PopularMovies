package com.heartmon.android.popularmovies.data;

import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.data.model.MovieResult;
import com.heartmon.android.popularmovies.data.model.MovieReviewResult;
import com.heartmon.android.popularmovies.data.model.MovieVideoResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by heartmon on 1/28/2017.
 */

public interface MovieDataSource {
    Observable<MovieResult> getPopularMovies();
    Observable<MovieResult> getTopRatedMovies();
    Observable<MovieVideoResult> getVideoTrailer(int videoId);
    Observable<MovieReviewResult> getReviews(int videoId);

    Observable addToFavorite(Movie movie);
    Observable removeFromFavorite(Movie movie);
    Observable getFavoriteMovie();
    Observable getFavoriteMovie(Integer movieId);
}
