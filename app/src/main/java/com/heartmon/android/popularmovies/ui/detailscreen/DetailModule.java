package com.heartmon.android.popularmovies.ui.detailscreen;

import android.app.Application;

import com.heartmon.android.popularmovies.data.MovieRepository;
import com.heartmon.android.popularmovies.loader.PresenterFactory;
import com.heartmon.android.popularmovies.ui.mainscreen.MainScreenContract;
import com.heartmon.android.popularmovies.ui.mainscreen.MainScreenPresenter;
import com.heartmon.android.popularmovies.util.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by heartmon on 1/31/2017.
 */

@Module
public class DetailModule {

    @Provides
    public DetailFragment providerDetailFragment() {
        return new DetailFragment();
    }

    @Provides
    public PresenterFactory<DetailPresenter> providerPresenterFactory(final MovieRepository movieRepository) {
        return new PresenterFactory<DetailPresenter>() {
            @Override
            public DetailPresenter create() {
                return new DetailPresenter(movieRepository);
            }
        };
    }

    @Provides
    public PresenterFactory<ReviewFragmentPresenter> provideReviewFragmentPresenterFactory(final MovieRepository movieRepository) {
        return new PresenterFactory<ReviewFragmentPresenter>() {
            @Override
            public ReviewFragmentPresenter create() {
                return new ReviewFragmentPresenter(movieRepository);
            }
        };
    }

    @Provides
    public PresenterFactory<DetailFragmentPresenter> providerDetailFragmentPresenterFactory(final MovieRepository movieRepository) {
        return new PresenterFactory<DetailFragmentPresenter>() {
            @Override
            public DetailFragmentPresenter create() {
                return new DetailFragmentPresenter(movieRepository);
            }
        };
    }

}
