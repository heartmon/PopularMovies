package com.heartmon.android.popularmovies.ui.detailscreen;

import com.heartmon.android.popularmovies.data.MovieRepository;
import com.heartmon.android.popularmovies.data.model.MovieReview;
import com.heartmon.android.popularmovies.data.model.MovieReviewResult;
import com.heartmon.android.popularmovies.data.model.MovieVideoResult;
import com.heartmon.android.popularmovies.ui.base.BasePresenterImpl;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by heartmon on 4/27/2017.
 */

public class ReviewFragmentPresenter extends BasePresenterImpl<ReviewFragment> implements ReviewFragmentContract.Presenter {
    private MovieRepository mRepository;
    private Integer movieId;

    private MovieReviewResult movieReviewResult;
    private boolean isReviewLoading = false;

    public ReviewFragmentPresenter(MovieRepository repository) {
        mRepository = repository;
    }

    public void fetchUserReviews(Integer movieId) {
        mView.showLoading();
        if(isReviewLoading) {
            return;
        }
        if(movieReviewResult != null) {
            mView.showReview(movieReviewResult.getResults());
            mView.showComplete();
            return;
        }
        mRepository.getReviews(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<MovieReviewResult>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        isReviewLoading = false;
                        mView.showComplete();
                        return;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        return;
                    }

                    @Override
                    public void onNext(MovieReviewResult value) {
                        movieReviewResult = value;
                        mView.showReview(value.getResults());
                        return;
                    }
                });
    }

}
