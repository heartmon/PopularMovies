package com.heartmon.android.popularmovies.ui.detailscreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heartmon.android.popularmovies.AppComponent;
import com.heartmon.android.popularmovies.R;
import com.heartmon.android.popularmovies.data.MovieRepositoryModule;
import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.loader.PresenterFactory;
import com.heartmon.android.popularmovies.ui.base.BaseFragment;
import com.heartmon.android.popularmovies.ui.base.BaseLoaderActivity;
import com.heartmon.android.popularmovies.util.NetworkUtil;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heartmon on 4/26/2017.
 */

public class DetailFragment extends BaseFragment<DetailFragmentPresenter, DetailFragment> implements DetailFragmentContract.View, View.OnClickListener {
    private String LOG_TAG = DetailActivity.class.getSimpleName();
    private static final String MOVIE_KEY = "Movie";

    @BindView(R.id.tv_overview)
    TextView mOverViewTextView;
    @BindView(R.id.iv_movie_thumb) ImageView mMovieThumbImageView;
    @BindView(R.id.tv_rating) TextView mRatingTextView;
    @BindView(R.id.tv_released_date) TextView mReleaseDateTextView;
    @BindView(R.id.tv_movie_title) TextView mMovieTitleTextView;
    @BindView(R.id.b_favorite)
    Button mFavoriteButton;

    private Movie movie;

    @Inject
    PresenterFactory<DetailFragmentPresenter> mPresenterFactory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        //bundle
        movie = (Movie) getArguments().getParcelable(MOVIE_KEY);

        if(mPresenter != null) {
            Log.d("DetailFragment", "Presenter is here! :: onCreateView");
        }

        mFavoriteButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(mPresenter != null) {
            Log.d("DetailFragment", "Presenter is here! :: onActivityCreate");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if(mPresenter != null) {
            Log.d("DetailFragment", "Presenter is here! :: onStart");
            if(movie != null) {
                mPresenter.checkFavoriteFlag(movie.getId());
            }
        }
        //set value to view
        if(movie != null) {
            Picasso.with(this.getContext()).load(NetworkUtil.IMAGE_BASE_URL + movie.getPosterPath()).into(mMovieThumbImageView);
            mOverViewTextView.setText(movie.getOverview());
            mRatingTextView.setText(movie.getVoteAverage() + " / 10");
            DateTime d = new DateTime(movie.getReleaseDate().getTime());
            mReleaseDateTextView.setText(d.getYear()+"");
            mMovieTitleTextView.setText(movie.getTitle());
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        if(mPresenter != null) {
            Log.d("DetailFragment", "Presenter is here! :: onResume");
        }
    }

    @NonNull
    @Override
    protected PresenterFactory<DetailFragmentPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected void setupComponent(@NonNull AppComponent appComponent) {
        DaggerDetailComponent.builder()
                .movieRepositoryComponent(appComponent.plus(new MovieRepositoryModule()))
                .detailModule(new DetailModule())
                .build().inject(this);
    }

    @Override
    public void setFavoriteState(boolean state) {
        if(state) {
            mFavoriteButton.setText(R.string.remove_from_favorite);
        } else {
            mFavoriteButton.setText(R.string.add_to_favorite);
        }
    }

    @Override
    public void showFavoriteToast(boolean state) {
        if(state) {
            Toast.makeText(this.getContext(), R.string.add_to_fav_success, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getContext(), R.string.remove_from_fav_success, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        Log.d("DetailFragment", "favorite clicked");
        mPresenter.favoriteClick(movie);
    }
}
