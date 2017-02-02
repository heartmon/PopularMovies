package com.heartmon.android.popularmovies.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.heartmon.android.popularmovies.AppComponent;
import com.heartmon.android.popularmovies.PopularMoviesApplication;

/**
 * Created by heartmon on 1/29/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(PopularMoviesApplication.get(this).getAppComponent());
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);
}