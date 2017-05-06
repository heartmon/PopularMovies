package com.heartmon.android.popularmovies.ui.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by heartmon on 4/25/2017.
 */

public abstract class BasePresenterImpl<V> implements BasePresenter<V>
{
    @Nullable
    protected V mView;

    @Override
    public void onViewAttached(@NonNull V view)
    {
        mView = view;
    }

    @Override
    public void onViewDetached()
    {
        mView = null;
    }

    @Override
    public void onStart(boolean viewCreated)
    {

    }

    @Override
    public void onStop()
    {

    }

    @Override
    public void onPresenterDestroyed()
    {

    }

    @Override
    public void onDestroyed() {

    }
}
