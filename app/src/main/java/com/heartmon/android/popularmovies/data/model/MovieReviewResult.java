package com.heartmon.android.popularmovies.data.model;

import java.util.List;

/**
 * Created by heartmon on 4/29/2017.
 */

public class MovieReviewResult {
    private final Integer _id;
    private final Integer page;
    private final Integer totalPages;
    private final Integer totalReults;
    private List<MovieReview> results;

    public MovieReviewResult(Integer _id, Integer page, Integer totalPages, Integer totalReults, List<MovieReview> results) {
        this._id = _id;
        this.page = page;
        this.totalPages = totalPages;
        this.totalReults = totalReults;
        this.results = results;
    }

    public Integer get_id() {
        return _id;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getTotalReults() {
        return totalReults;
    }

    public List<MovieReview> getResults() {
        return results;
    }
}

