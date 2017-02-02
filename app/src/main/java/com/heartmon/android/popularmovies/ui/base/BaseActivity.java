package com.heartmon.android.popularmovies.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.heartmon.android.popularmovies.AppComponent;
import com.heartmon.android.popularmovies.PopularMoviesApplication;

/**
 * Created by heartmon on 1/29/2017.
 */

/*
Code obtained from
https://github.com/frogermcs/GithubClient/blob/1bf53a2a36c8a85435e877847b987395e482ab4a/app/src/main/java/frogermcs/io/githubclient/ui/activity/BaseActivity.java
*/

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(PopularMoviesApplication.get(this).getAppComponent());
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);
}