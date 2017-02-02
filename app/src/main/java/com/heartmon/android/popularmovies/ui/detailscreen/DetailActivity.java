package com.heartmon.android.popularmovies.ui.detailscreen;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Property;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heartmon.android.popularmovies.R;
import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.util.NetworkUtil;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    private String LOG_TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.iv_hero_cover) ImageView mImageViewCover;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.tv_overview) TextView mOverViewTextView;
    @BindView(R.id.iv_movie_thumb) ImageView mMovieThumbImageView;
    @BindView(R.id.tv_rating) TextView mRatingTextView;
    @BindView(R.id.tv_released_date) TextView mReleaseDateTextView;
    @BindView(R.id.tv_movie_title) TextView mMovieTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        //view setup
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if(getIntent()!=null) {
            Intent intent = getIntent();
            Movie movie = intent.getParcelableExtra("Movie");
//            Toast.makeText(this, movie.toString(), Toast.LENGTH_LONG).show();

            Picasso.with(this).load(NetworkUtil.IMAGE_BASE_URL + movie.getBackdropPath()).into(mImageViewCover);
            mCollapsingToolbar.setTitle(movie.getTitle());
            Picasso.with(this).load(NetworkUtil.IMAGE_BASE_URL + movie.getPosterPath()).into(mMovieThumbImageView);
            mOverViewTextView.setText(movie.getOverview());
            mRatingTextView.setText(movie.getVoteAverage() + " / 10");
            DateTime d = new DateTime(movie.getReleaseDate().getTime());
            mReleaseDateTextView.setText(d.getYear()+"");
            mMovieTitleTextView.setText(movie.getTitle());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
