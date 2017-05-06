package com.heartmon.android.popularmovies.ui.detailscreen;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import com.heartmon.android.popularmovies.data.MovieRepository;
import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.data.model.MovieReviewResult;
import com.heartmon.android.popularmovies.ui.base.BasePresenterImpl;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.operations.delete.DeleteResult;
import com.pushtorefresh.storio.contentresolver.operations.put.PutResult;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by heartmon on 4/26/2017.
 */

public class DetailFragmentPresenter extends BasePresenterImpl<DetailFragment> implements DetailFragmentContract.Presenter {
    private final String LOG_TAG = DetailFragmentPresenter.class.getSimpleName();
    private boolean isFavorited;
    private MovieRepository mRepository;

    public DetailFragmentPresenter(MovieRepository repository) {
        mRepository = repository;
    }

    @Override
    public void checkFavoriteFlag(Integer movieId) {
        mRepository.getFavoriteMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Movie> movies) {
                        if(movies.size() == 0) {
                            isFavorited = false;
                            return;
                        } else {
                            isFavorited = true;
                        }

                        if(mView != null) {
                            mView.setFavoriteState(isFavorited);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onViewAttached(@android.support.annotation.NonNull DetailFragment view) {
        super.onViewAttached(view);


    }

    @Override
    public void favoriteClick(Movie movie) {
        if(!isFavorited) {
            mRepository.addToFavorite(movie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(new Observer<PutResult>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull PutResult putResult) {
                            isFavorited = true;
                            if (putResult.wasInserted()) {
                                mView.showFavoriteToast(true);
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d(LOG_TAG, e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            mRepository.removeFromFavorite(movie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(new Observer<DeleteResult>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull DeleteResult deleteResult) {
                            isFavorited = false;
                            mView.setFavoriteState(isFavorited);
                            mView.showFavoriteToast(isFavorited);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d(LOG_TAG, e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}