package com.heartmon.android.popularmovies.ui.detailscreen;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;

import com.heartmon.android.popularmovies.data.MovieRepository;
import com.heartmon.android.popularmovies.data.model.MovieResult;
import com.heartmon.android.popularmovies.data.model.MovieVideoResult;
import com.heartmon.android.popularmovies.ui.base.BasePresenterImpl;
import com.heartmon.android.popularmovies.ui.mainscreen.MainScreenContract;
import com.heartmon.android.popularmovies.ui.mainscreen.MainScreenPresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static java.security.AccessController.getContext;

/**
 * Created by heartmon on 1/31/2017.
 */

public class DetailPresenter extends BasePresenterImpl<DetailActivity> implements DetailContract.Presenter {
    private final String LOG_TAG = DetailPresenter.class.getSimpleName();

    private MovieRepository movieRepository;
    private MovieVideoResult movieVideoResult;
    private Observable<Integer> videoId;

    private boolean isTrailerLoading = false;

    public DetailPresenter(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void onViewAttached(@NonNull DetailActivity view) {
        super.onViewAttached(view);
    }

    @Override
    public void fetchVideoTrailer(int videoId) {
//        mView.getSupportLoaderManager().initLoader(DetailContract.LOADER_TRAILER_ID, null, mLoaderCallbacks);
        if(isTrailerLoading) {
            return;
        }
        if(movieVideoResult != null) {
            mView.showTrailerButton();
            return;
        }
        isTrailerLoading = true;
        movieRepository.getVideoTrailer(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<MovieVideoResult>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        isTrailerLoading = false;
                        mView.showTrailerButton();
                        return;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        return;
                    }

                    @Override
                    public void onNext(MovieVideoResult value) {
                        movieVideoResult = value;
                        return;
                    }
                });
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        if(viewCreated) {
            Log.d("TESTTEST", "onStart from Presenter");
        }
    }

    @Override
    public void onStop() {
        Log.d("TESTTEST", "onStop from Presenter");
        super.onStop();
    }

    @Override
    public void onDestroyed() {

    }

    @Override
    public MovieVideoResult getTrailer() {
        return movieVideoResult;
    }

    @Override
    public void onPresenterDestroyed() {
        //
        super.onPresenterDestroyed();
    }

}
