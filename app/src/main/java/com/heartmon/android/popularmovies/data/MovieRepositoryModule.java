package com.heartmon.android.popularmovies.data;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Network;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.heartmon.android.popularmovies.data.source.local.MovieLocalDataSource;
import com.heartmon.android.popularmovies.util.DataScope;
import com.heartmon.android.popularmovies.data.source.remote.MovieRemoteDataSource;
import com.heartmon.android.popularmovies.util.Local;
import com.heartmon.android.popularmovies.util.NetworkUtil;
import com.heartmon.android.popularmovies.util.Remote;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by heartmon on 1/28/2017.
 */

@Module
public class MovieRepositoryModule {

    @DataScope
    @Provides
    @Local
    MovieDataSource provideMovieLocalDataSource(Application application) {
        return new MovieLocalDataSource(application.getApplicationContext());
    }

    @DataScope
    @Provides
    @Remote
    MovieDataSource provideMovieRemoteDataSource(Retrofit retrofit) {
        return new MovieRemoteDataSource(retrofit);
    }

    @Provides
    @DataScope
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @DataScope
    OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        /*
        Code obtained from https://futurestud.io/tutorials/retrofit-2-how-to-add-query-parameters-to-every-request
        */
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter(NetworkUtil.PARAM_MOVIE_DB_API_KEY, NetworkUtil.MOVIE_DB_API_KEY)
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        client.addInterceptor(logging);

        return client.build();
    }

    @DataScope
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(NetworkUtil.MOVIE_BASE_URL)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

//    @Provides @Singleton
//    SqlBrite provideSqlBrite() {
//        return new SqlBrite.Builder()
//                .logger(new SqlBrite.Logger() {
//                    @Override public void log(String message) {
//                        Log.d("Database",message);
//                    }
//                })
//                .build();
//    }
//
//    @Provides @Singleton
//    BriteDatabase provideDatabase(SqlBrite sqlBrite, SQLiteOpenHelper helper) {
//        BriteDatabase db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
//        db.setLoggingEnabled(true);
//        return db;
//    }


}
