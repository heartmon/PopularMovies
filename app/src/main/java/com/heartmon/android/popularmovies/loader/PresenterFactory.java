package com.heartmon.android.popularmovies.loader;

import com.heartmon.android.popularmovies.ui.base.BasePresenter;

/**
 * Created by heartmon on 4/25/2017.
 */

public interface PresenterFactory<T extends BasePresenter> {
    T create();
}

