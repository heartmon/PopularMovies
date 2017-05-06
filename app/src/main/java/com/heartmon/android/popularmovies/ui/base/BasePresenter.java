package com.heartmon.android.popularmovies.ui.base;

import android.view.View;

/**
 * Created by heartmon on 1/28/2017.
 */

public interface BasePresenter<V> {
    void onViewAttached(V view);
    void onViewDetached();
    void onDestroyed();
    void onStart(boolean viewCreated);
    void onStop();
    void onPresenterDestroyed();
}
