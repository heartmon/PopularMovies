package com.heartmon.android.popularmovies.data.model;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by heartmon on 1/22/2017.
 */

public interface MovieService {
    @GET("/3/movie/popular")
    Observable<MovieResult> getPopularMovieList();
}
