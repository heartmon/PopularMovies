package com.heartmon.android.popularmovies.ui.detailscreen;

import com.heartmon.android.popularmovies.data.model.MovieVideoResult;
import com.heartmon.android.popularmovies.ui.base.BasePresenter;
import com.heartmon.android.popularmovies.ui.base.BaseView;

/**
 * Created by heartmon on 1/31/2017.
 */

public class DetailContract {
    public static final int LOADER_TRAILER_ID = 1000;

    interface View extends BaseView<DetailActivity> {
        void showTrailerButton();
    }

    interface Presenter extends BasePresenter<DetailActivity> {
        void fetchVideoTrailer(int videoId);
        MovieVideoResult getTrailer();
    }
}
