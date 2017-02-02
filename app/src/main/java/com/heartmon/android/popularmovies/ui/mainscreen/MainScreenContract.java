package com.heartmon.android.popularmovies.ui.mainscreen;

import com.heartmon.android.popularmovies.ui.base.BaseView;
import com.heartmon.android.popularmovies.data.model.MovieResult;

/**
 * Created by heartmon on 1/22/2017.
 */

public interface MainScreenContract {
    interface View extends BaseView<MainScreenContract.Presenter> {
        void showPosts(MovieResult movieResult);

        void showError(String message);

        void showComplete();

        void showLoading();
    }

    interface Presenter {
        void fetchPopularMovies();
        void fetchTopRatedMovies();

    }
}
