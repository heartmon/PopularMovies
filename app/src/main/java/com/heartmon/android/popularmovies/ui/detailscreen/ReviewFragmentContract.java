package com.heartmon.android.popularmovies.ui.detailscreen;

import com.heartmon.android.popularmovies.data.model.MovieReview;
import com.heartmon.android.popularmovies.ui.base.BasePresenter;
import com.heartmon.android.popularmovies.ui.base.BaseView;

import java.util.List;

/**
 * Created by heartmon on 4/27/2017.
 */

public class ReviewFragmentContract {
    interface View extends BaseView<ReviewFragment> {
        void showReview(List<MovieReview> movieReviews);
        void showComplete();
        void showLoading();
    }

    interface Presenter extends BasePresenter<ReviewFragment> {

    }
}
