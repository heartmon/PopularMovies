package com.heartmon.android.popularmovies.ui.detailscreen;

import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.data.model.MovieVideoResult;
import com.heartmon.android.popularmovies.ui.base.BasePresenter;
import com.heartmon.android.popularmovies.ui.base.BaseView;

/**
 * Created by heartmon on 4/26/2017.
 */

public class DetailFragmentContract {
    interface View extends BaseView<DetailFragment> {
        public void setFavoriteState(boolean state);
        public void showFavoriteToast(boolean state);
    }

    interface Presenter extends BasePresenter<DetailFragment> {
        public void checkFavoriteFlag(Integer movieId);
        public void favoriteClick(Movie movie);
    }
}
