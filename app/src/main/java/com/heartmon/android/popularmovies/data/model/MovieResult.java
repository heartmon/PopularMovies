package com.heartmon.android.popularmovies.data.model;

import java.util.List;

/**
 * Created by heartmon on 1/22/2017.
 */

public class MovieResult {
    private final Integer page;
    private final List<Movie> results;

    public MovieResult(Integer page, List<Movie> results) {
        this.page = page;
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }
}
