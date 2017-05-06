package com.heartmon.android.popularmovies.loader;

import com.heartmon.android.popularmovies.ui.base.BasePresenter;

/**
 * Created by heartmon on 4/25/2017.
 */

/*
Code obtained from
https://github.com/benoitletondor/Android-Studio-MVP-template
https://medium.com/@czyrux/presenter-surviving-orientation-changes-with-loaders-6da6d86ffbbf
*/

public interface PresenterFactory<T extends BasePresenter> {
    T create();
}

