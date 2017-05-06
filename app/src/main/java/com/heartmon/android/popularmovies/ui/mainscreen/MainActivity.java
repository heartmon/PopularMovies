package com.heartmon.android.popularmovies.ui.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.heartmon.android.popularmovies.AppComponent;
import com.heartmon.android.popularmovies.ui.base.BaseActivity;
import com.heartmon.android.popularmovies.R;
import com.heartmon.android.popularmovies.data.MovieRepositoryModule;
import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.data.model.MovieResult;
import com.heartmon.android.popularmovies.ui.detailscreen.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.OPTIONS;

public class MainActivity extends BaseActivity implements MainScreenContract.View, MovieListAdapter.MovieListAdapterOnClickHandler {
    @BindView(R.id.rv_movie_list)
    RecyclerView mMovieRecyclerView;
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mProgressBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    MainScreenPresenter presenter;

    private String LOG_TAG = MainActivity.class.getSimpleName();
    private ArrayList<Movie> mMovieList;
    private MovieListAdapter mMovieListAdapter;
    private Integer mCurrentSelected = R.id.action_sort_popular;
    private String KEY_CHOICE = "choices";

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainScreenComponent.builder()
                .movieRepositoryComponent(appComponent.plus(new MovieRepositoryModule()))
                .mainActivityModule(new MainActivityModule(this))
                .build().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        //Restore instance state
        if(savedInstanceState != null) {
            mCurrentSelected = savedInstanceState.getInt(KEY_CHOICE);
        }

        mMovieList = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        mMovieRecyclerView.setLayoutManager(layoutManager);
//        mMovieRecyclerView.setHasFixedSize();
        mMovieListAdapter = new MovieListAdapter(MainActivity.this, this);
        mMovieRecyclerView.setAdapter(mMovieListAdapter);
        //call
        fetchMovies();
    }

    @Override
    public void onClick(Movie movie) {
        //get intent to the next activity
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Movie", movie);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mCurrentSelected == R.id.action_sort_favorite) {
            fetchMovies();
        }
    }

    @Override
    public void showPosts(List<Movie> movies) {
        //Loop through the posts and get the title of the post and add it to our list object
//        mMovieList = new ArrayList<Movie>(movieResult.getResults());
        mMovieListAdapter.setMovieList(movies);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mMovieRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String message) {
        //Show error message Toast
        Toast.makeText(getApplicationContext(), R.string.error_message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showComplete() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mMovieRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mCurrentSelected = item.getItemId();
        switch(item.getItemId()) {
            case R.id.action_sort_popular:
                presenter.fetchPopularMovies();
                return true;
            case R.id.action_sort_top_rated:
                presenter.fetchTopRatedMovies();
                return true;
            case R.id.action_sort_favorite:
                presenter.fetchFavoriteMovies();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CHOICE, mCurrentSelected);
    }

    private void fetchMovies() {
        Log.d(LOG_TAG, mCurrentSelected+"");
        switch (mCurrentSelected) {
            case R.id.action_sort_top_rated:
                presenter.fetchTopRatedMovies();
                break;
            case R.id.action_sort_favorite:
                presenter.fetchFavoriteMovies();
                break;
            default:
                presenter.fetchPopularMovies();
        }
    }
}
