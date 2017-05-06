package com.heartmon.android.popularmovies.data.model;

/**
 * Created by heartmon on 5/6/2017.
 */

public class MovieVideo {
    private final Integer _id;
    private final String key;
    private final String site;

    public MovieVideo(Integer _id, String key, String site) {
        this._id = _id;
        this.key = key;
        this.site = site;
    }

    public Integer get_id() {
        return _id;
    }

    public String getKey() {
        return key;
    }

    public String getSite() {
        return site;
    }

    public String getFullUrl() {
        return "https://www.youtube.com/watch?v=" + key;
    }
}
