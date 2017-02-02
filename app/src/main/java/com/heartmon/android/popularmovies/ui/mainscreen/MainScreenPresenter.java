package com.heartmon.android.popularmovies.ui.mainscreen;

import android.util.Log;

import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.data.MovieRepository;
import com.heartmon.android.popularmovies.data.model.MovieResult;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by heartmon on 1/22/2017.
 */

public class MainScreenPresenter implements MainScreenContract.Presenter {
    private final String LOG_TAG = MainScreenPresenter.class.getSimpleName();

    private MainScreenContract.View mainActivity;
    private MovieRepository movieRepository;

    @Inject
    public MainScreenPresenter(MainScreenContract.View mainActivity, MovieRepository movieRepository) {
        this.mainActivity = mainActivity;
        this.movieRepository = movieRepository;
    }

    @Override
    public void fetchPopularMovies() {
        mainActivity.showLoading();
        movieRepository.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.io())
            .subscribe(new Observer<MovieResult>() {

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onComplete() {
                    mainActivity.showComplete();
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    mainActivity.showError(e.getMessage());
                }

                @Override
                public void onNext(MovieResult value) {
                    mainActivity.showPosts(value);
                }
            });
    }

    @Override
    public void fetchTopRatedMovies() {
        mainActivity.showLoading();
        movieRepository.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.io())
            .subscribe(new Observer<MovieResult>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                mainActivity.showComplete();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mainActivity.showError(e.getMessage());
            }

            @Override
            public void onNext(MovieResult value) {
                mainActivity.showPosts(value);
            }
        });
    }
}