package com.heartmon.android.popularmovies.data.source.local;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.heartmon.android.popularmovies.data.MovieDataSource;
import com.heartmon.android.popularmovies.data.model.Movie;
import com.heartmon.android.popularmovies.data.model.MovieResult;
import com.heartmon.android.popularmovies.data.model.MovieReviewResult;
import com.heartmon.android.popularmovies.data.model.MovieVideoResult;
import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio.contentresolver.operations.delete.DeleteResolver;
import com.pushtorefresh.storio.contentresolver.operations.delete.DeleteResult;
import com.pushtorefresh.storio.contentresolver.operations.get.DefaultGetResolver;
import com.pushtorefresh.storio.contentresolver.operations.get.GetResolver;
import com.pushtorefresh.storio.contentresolver.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.contentresolver.queries.DeleteQuery;
import com.pushtorefresh.storio.contentresolver.queries.InsertQuery;
import com.pushtorefresh.storio.contentresolver.queries.Query;
import com.pushtorefresh.storio.contentresolver.queries.UpdateQuery;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.id;

/**
 * Created by heartmon on 5/5/2017.
 */

public class MovieLocalDataSource implements MovieDataSource {
    private StorIOContentResolver mStorIOContentResolver;

    @Inject
    public MovieLocalDataSource(@NonNull Context context) {
        mStorIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(context.getContentResolver())
                .addTypeMapping(Movie.class, ContentResolverTypeMapping.<Movie>builder()
                    .putResolver(new MoviePutResolver())
                    .getResolver(new DefaultGetResolver<Movie>() {
                        @Override
                        @NonNull
                        public Movie mapFromCursor(@NonNull Cursor cursor) {
                            Movie object = new Movie();

                            object.backdropPath = cursor.getString(cursor.getColumnIndex("backdrop_path"));
                            object.overview = cursor.getString(cursor.getColumnIndex("overview"));
                            if (!cursor.isNull(cursor.getColumnIndex("rating"))) {
                                object.voteAverage = cursor.getFloat(cursor.getColumnIndex("rating"));
                            }
                            if (!cursor.isNull(cursor.getColumnIndex("id"))) {
                                object.id = cursor.getInt(cursor.getColumnIndex("id"));
                            }
                            object.title = cursor.getString(cursor.getColumnIndex("title"));
                            object.posterPath = cursor.getString(cursor.getColumnIndex("poster_path"));
                            if (!cursor.isNull(cursor.getColumnIndex(MovieContract.FavoriteEntry.COLUMN_NAME_YEAR))) {
                                object.releaseDate = new Date(cursor.getLong(cursor.getColumnIndex(MovieContract.FavoriteEntry.COLUMN_NAME_YEAR)));
                            }

                            return object;
                        }
                    })
                    .deleteResolver(new DefaultDeleteResolver<Movie>() {
                        @Override @NonNull public DeleteQuery mapToDeleteQuery(@NonNull Movie object) {
                            Uri uri = ContentUris.withAppendedId(MovieContract.FavoriteEntry.CONTENT_URI, object.getId());
                            return DeleteQuery.builder()
                                    .uri(uri)
                                    .build();
                        }
                    })
                    .build()
                )
                .build();
//

                       // .getResolver(new MovieStorIOContentResolverGetResolver())
//                        .deleteResolver(new MovieStorIOContentResolverDeleteResolver())
//                        .build()
    }

    @Override
    public Observable<MovieResult> getPopularMovies() {
        return null;
    }

    @Override
    public Observable<MovieResult> getTopRatedMovies() {
        return null;
    }

    @Override
    public Observable<MovieVideoResult> getVideoTrailer(int videoId) {
        return null;
    }

    @Override
    public Observable<MovieReviewResult> getReviews(int videoId) {
        return null;
    }

    @Override
    public Observable addToFavorite(Movie movie) {
        return RxJavaInterop.toV2Observable(mStorIOContentResolver
                .put()
                .object(movie)
                .prepare()
                .asRxObservable());
    }

    @Override
    public Observable removeFromFavorite(Movie movie) {
        return RxJavaInterop.toV2Observable(mStorIOContentResolver
                .delete()
                .object(movie)
                .prepare()
                .asRxObservable()
        );
    }

    @Override
    public Observable getFavoriteMovie() {
        Log.d("LOCAL","Loaded from local");
        return RxJavaInterop.toV2Observable(mStorIOContentResolver
                .get()
                .listOfObjects(Movie.class)
                .withQuery(Query.builder()
                        .uri(MovieContract.FavoriteEntry.CONTENT_URI)
                        .build())
                .prepare()
                .asRxObservable());
    }

    @Override
    public Observable getFavoriteMovie(Integer movieId) {
        return RxJavaInterop.toV2Observable(mStorIOContentResolver
                .get()
                .listOfObjects(Movie.class)
                .withQuery(Query.builder()
                        .uri(MovieContract.FavoriteEntry.CONTENT_URI)
                        .where("id = ?")
                        .whereArgs(movieId)
                        .build())
                .prepare()
                .asRxObservable());
    }

    class MoviePutResolver extends DefaultPutResolver<Movie> {
        @NonNull
        @Override
        protected InsertQuery mapToInsertQuery(@NonNull Movie object) {
            return InsertQuery.builder()
                    .uri(MovieContract.FavoriteEntry.CONTENT_URI)
                    .build();
        }

        @NonNull
        @Override
        protected UpdateQuery mapToUpdateQuery(@NonNull Movie object) {
            return UpdateQuery.builder()
                    .uri(MovieContract.FavoriteEntry.CONTENT_URI)
                    .where("id = ?")
                    .whereArgs(object.getId())
                    .build();
//            Uri uri = ContentUris.withAppendedId(MovieContract.FavoriteEntry.CONTENT_URI, object.getId());
//            return UpdateQuery.builder()
//                    .uri(uri)
//                    .build();
        }

        @NonNull
        @Override
        protected ContentValues mapToContentValues(@NonNull Movie object) {
            final ContentValues contentValues = new ContentValues();
            // fill with fields from object
            contentValues.put(MovieContract.FavoriteEntry.COLUMN_NAME_ID, object.getId());
            contentValues.put(MovieContract.FavoriteEntry.COLUMN_NAME_TITLE, object.getTitle());
            contentValues.put(MovieContract.FavoriteEntry.COLUMN_NAME_BACKDROP_PATH, object.getBackdropPath());
            contentValues.put(MovieContract.FavoriteEntry.COLUMN_NAME_OVERVIEW, object.getOverview());
            contentValues.put(MovieContract.FavoriteEntry.COLUMN_NAME_POSTER_PATH, object.getPosterPath());
            contentValues.put(MovieContract.FavoriteEntry.COLUMN_NAME_RATING, object.getVoteAverage());
            contentValues.put(MovieContract.FavoriteEntry.COLUMN_NAME_YEAR, object.getReleaseDate().getTime());

            return contentValues;
        }
    }
}
