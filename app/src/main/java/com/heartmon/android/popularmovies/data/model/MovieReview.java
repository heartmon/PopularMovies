package com.heartmon.android.popularmovies.data.model;

/**
 * Created by heartmon on 5/4/2017.
 */

public class MovieReview {
    private final String _id;
    private final String author;
    private final String content;
    private final String url;

    public MovieReview(String _id, String author, String content, String url) {
        this._id = _id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    public String get_id() {
        return _id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}