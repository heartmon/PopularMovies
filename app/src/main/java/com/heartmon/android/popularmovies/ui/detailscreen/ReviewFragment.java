package com.heartmon.android.popularmovies.ui.detailscreen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.heartmon.android.popularmovies.AppComponent;
import com.heartmon.android.popularmovies.R;
import com.heartmon.android.popularmovies.data.MovieRepositoryModule;
import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.data.model.MovieResult;
import com.heartmon.android.popularmovies.data.model.MovieReview;
import com.heartmon.android.popularmovies.data.model.MovieReviewResult;
import com.heartmon.android.popularmovies.loader.PresenterFactory;
import com.heartmon.android.popularmovies.ui.base.BaseFragment;
import com.heartmon.android.popularmovies.ui.mainscreen.MainActivity;
import com.heartmon.android.popularmovies.ui.mainscreen.MovieListAdapter;
import com.heartmon.android.popularmovies.util.NetworkUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heartmon on 4/27/2017.
 */

public class ReviewFragment extends BaseFragment<ReviewFragmentPresenter, ReviewFragment> implements ReviewFragmentContract.View {
    private static final String MOVIE_KEY = "Movie";

    @Inject
    PresenterFactory<ReviewFragmentPresenter> factory;

    @BindView(R.id.rv_movie_review) RecyclerView mMovieReviewRecycleView;
    @BindView(R.id.pb_loading_review)
    ProgressBar mLoadingReview;

    private Movie movie;
    private ReviewListAdapter mReviewListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        ButterKnife.bind(this, view);
        //bundle
        movie = (Movie) getArguments().getParcelable(MOVIE_KEY);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        mMovieReviewRecycleView.setLayoutManager(layoutManager);
        mReviewListAdapter = new ReviewListAdapter(this.getContext());
        mMovieReviewRecycleView.setAdapter(mReviewListAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void showReview(List<MovieReview> movieReviews) {
        Log.d("ReviewFragment", movieReviews.toString());
        //Loop through the posts and get the title of the post and add it to our list object
//        mMovieList = new ArrayList<Movie>(movieResult.getResults());
        mReviewListAdapter.setMovieReviewList(movieReviews);
    }

    @Override
    public void showComplete() {
        mMovieReviewRecycleView.setVisibility(View.VISIBLE);
        mLoadingReview.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading() {
        mMovieReviewRecycleView.setVisibility(View.INVISIBLE);
        mLoadingReview.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        if(movie != null && mPresenter != null) {
            mPresenter.fetchUserReviews(movie.getId());
        }
    }

    @NonNull
    @Override
    protected PresenterFactory<ReviewFragmentPresenter> getPresenterFactory() {
        return factory;
    }

    @Override
    protected void setupComponent(@NonNull AppComponent appComponent) {
        DaggerDetailComponent.builder()
                .movieRepositoryComponent(appComponent.plus(new MovieRepositoryModule()))
                .detailModule(new DetailModule())
                .build().inject(this);
    }


}

class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewListAdapterViewHolder> {
    private String LOG_TAG = MovieListAdapter.class.getSimpleName();
    private List<MovieReview> mMovieList;
    private Context mContext;


    public ReviewListAdapter(Context context) {
        this.mContext = context;
//        this.mClickHandler = mClickHandler;
    }

    public void setMovieReviewList(List<MovieReview> movieList) {
        this.mMovieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(this.mMovieList == null) {
            return 0;
        } else {
            return this.mMovieList.size();
        }
    }

    @Override
    public ReviewListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.review_item, parent, false);
        return new ReviewListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewListAdapterViewHolder holder, int position) {
        MovieReview mr = mMovieList.get(position);
        Log.d(LOG_TAG, mr.toString());
        holder.mReviewAuthor.setText(mr.getAuthor());
        holder.mReviewContent.setText(mr.getContent());
    }

    public class ReviewListAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_review_author)
        TextView mReviewAuthor;
        @BindView(R.id.tv_review_content)
        TextView mReviewContent;

        public ReviewListAdapterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
