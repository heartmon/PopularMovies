package com.heartmon.android.popularmovies.data.model;

import java.util.List;

/**
 * Created by heartmon on 4/24/2017.
 */

public class MovieVideoResult {
    private final Integer id;
    private final List<MovieVideo> results;

    public MovieVideoResult(Integer id, List<MovieVideo> results) {
        this.id = id;
        this.results = results;
    }

    public Integer get_id() {
        return id;
    }

    public List<MovieVideo> getResults() {
        return results;
    }
}

