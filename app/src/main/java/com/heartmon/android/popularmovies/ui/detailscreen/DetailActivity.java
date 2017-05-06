package com.heartmon.android.popularmovies.ui.detailscreen;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Property;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heartmon.android.popularmovies.AppComponent;
import com.heartmon.android.popularmovies.R;
import com.heartmon.android.popularmovies.data.MovieRepositoryModule;
import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.data.model.MovieVideo;
import com.heartmon.android.popularmovies.data.model.MovieVideoResult;
import com.heartmon.android.popularmovies.loader.PresenterFactory;
import com.heartmon.android.popularmovies.loader.PresenterLoader;
import com.heartmon.android.popularmovies.ui.base.BaseActivity;
import com.heartmon.android.popularmovies.ui.base.BaseLoaderActivity;
import com.heartmon.android.popularmovies.ui.base.BasePresenter;
import com.heartmon.android.popularmovies.ui.mainscreen.DaggerMainScreenComponent;
import com.heartmon.android.popularmovies.ui.mainscreen.MainActivityModule;
import com.heartmon.android.popularmovies.ui.mainscreen.MainScreenContract;
import com.heartmon.android.popularmovies.util.NetworkUtil;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.id;

public class DetailActivity extends BaseLoaderActivity<DetailPresenter, DetailActivity> implements DetailContract.View {
    private String LOG_TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.iv_hero_cover) ImageView mImageViewCover;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
//    @BindView(R.id.tv_overview) TextView mOverViewTextView;
//    @BindView(R.id.iv_movie_thumb) ImageView mMovieThumbImageView;
//    @BindView(R.id.tv_rating) TextView mRatingTextView;
//    @BindView(R.id.tv_released_date) TextView mReleaseDateTextView;
//    @BindView(R.id.tv_movie_title) TextView mMovieTitleTextView;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.ib_trailer_button)
    Button mTrailerButton;

    @Inject
    PresenterFactory<DetailPresenter> mPresenterFactory;

    private Movie movie;

    @Override
    protected void setupComponent(@NonNull AppComponent appComponent) {
        DaggerDetailComponent.builder()
                .movieRepositoryComponent(appComponent.plus(new MovieRepositoryModule()))
                .detailModule(new DetailModule())
                .build().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DDD", "Detail Activity Create");
        super.onCreate(savedInstanceState);
        Log.d("DDD", "Before set content view");
        setContentView(R.layout.activity_detail);
        Log.d("DetailActivity", "ssssssssssssssss");
        ButterKnife.bind(this);

        //view setup
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d("DetailActivity", "set support action bar");

        if(getIntent()!=null) {
            Intent intent = getIntent();
            movie = intent.getParcelableExtra("Movie");
            Log.d("DetailActivity", "Parcelable");
//            Toast.makeText(this, movie.toString(), Toast.LENGTH_LONG).show();
        }

        if(movie != null) {
            Picasso.with(this).load(NetworkUtil.IMAGE_BASE_URL + movie.getBackdropPath()).into(mImageViewCover);
            mCollapsingToolbar.setTitle(movie.getTitle());
//            Picasso.with(this).load(NetworkUtil.IMAGE_BASE_URL + movie.getPosterPath()).into(mMovieThumbImageView);
//            mOverViewTextView.setText(movie.getOverview());
//            mRatingTextView.setText(movie.getVoteAverage() + " / 10");
//            DateTime d = new DateTime(movie.getReleaseDate().getTime());
//            mReleaseDateTextView.setText(d.getYear()+"");
//            mMovieTitleTextView.setText(movie.getTitle());
            Log.d("DetailActivity", "Set view");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(movie.getTitle());
        }

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);



    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle bundle = new Bundle();
        bundle.putParcelable("Movie", movie);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        ReviewFragment reviewFragment = new ReviewFragment();
        reviewFragment.setArguments(bundle);

        adapter.addFragment(detailFragment, "DETAIL");
        adapter.addFragment(reviewFragment, "REVIEW");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // specific code
        mPresenter.fetchVideoTrailer(movie.getId());
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

    @NonNull
    @Override
    protected PresenterFactory<DetailPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    public void onTrailerButtonClick(View v) {
        MovieVideoResult mvr = mPresenter.getTrailer();
        if(mvr == null || mvr.getResults().size() == 0) {
            return;
        }
        MovieVideo mv = mvr.getResults().get(mvr.getResults().size()-1);
        // call
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + mv.getKey()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + mv.getKey()));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }

    public void onFavoriteButtonClick(View v) {

    }

    @Override
    public void showTrailerButton() {
        mTrailerButton.setVisibility(View.VISIBLE);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
