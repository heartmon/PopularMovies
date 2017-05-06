package com.heartmon.android.popularmovies.ui.base;

import android.view.View;

/*
Code obtained from
https://github.com/benoitletondor/Android-Studio-MVP-template
*/

public interface BasePresenter<V> {
    void onViewAttached(V view);
    void onViewDetached();
    void onDestroyed();
    void onStart(boolean viewCreated);
    void onStop();
    void onPresenterDestroyed();
}
