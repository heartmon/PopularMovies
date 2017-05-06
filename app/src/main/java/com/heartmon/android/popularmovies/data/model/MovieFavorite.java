package com.heartmon.android.popularmovies.data.model;

import static android.R.attr.id;

/**
 * Created by heartmon on 5/5/2017.
 */

public class MovieFavorite {
    private final Integer movieId;
    private final String title;
    private final String posterPath;

    public MovieFavorite(Integer movieId, String title, String posterPath) {
        this.movieId = movieId;
        this.title = title;
        this.posterPath = posterPath;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
