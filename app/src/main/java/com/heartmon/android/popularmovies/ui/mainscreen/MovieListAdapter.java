package com.heartmon.android.popularmovies.ui.mainscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.heartmon.android.popularmovies.R;
import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.util.NetworkUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heartmon on 1/22/2017.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListAdapterViewHolder> {
    private String LOG_TAG = MovieListAdapter.class.getSimpleName();
    private List<Movie> mMovieList;
    private Context mContext;

    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    private final MovieListAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface MovieListAdapterOnClickHandler {
        void onClick(Movie movie);
    }

    public MovieListAdapter(Context context, MovieListAdapterOnClickHandler mClickHandler) {
        this.mContext = context;
        this.mClickHandler = mClickHandler;
    }

    public void setMovieList(List<Movie> movieList) {
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
    public MovieListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListAdapterViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
//        Log.d(LOG_TAG, movie.toString());
        Picasso.with(mContext).load(NetworkUtil.IMAGE_BASE_URL + movie.getPosterPath()).into(holder.mMovieImageView);
        holder.mMovieTitleTextView.setText(movie.getTitle());
    }

    public class MovieListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_movie_cover) ImageView mMovieImageView;
        @BindView(R.id.tv_movie_title) TextView mMovieTitleTextView;

        public MovieListAdapterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Movie movie = mMovieList.get(position);
            mClickHandler.onClick(movie);
        }
    }
}
