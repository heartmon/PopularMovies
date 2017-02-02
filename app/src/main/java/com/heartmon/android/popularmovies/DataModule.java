//package com.heartmon.android.popularmovies;
//
//import android.app.Application;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//
//import com.google.gson.FieldNamingPolicy;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.heartmon.android.popularmovies.data.source.remote.MovieRemoteDataSource;
//import com.heartmon.android.popularmovies.util.NetworkUtil;
//import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//
//import java.io.IOException;
//
//import javax.inject.Named;
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//import okhttp3.Cache;
//import okhttp3.HttpUrl;
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//import static android.R.attr.y;
//
///**
// * Created by heartmon on 1/28/2017.
// */
//
//@Module
//public class DataModule {
//
//
//
//    @Provides
//    @Singleton
//    SharedPreferences providesSharedPreferences(Application application) {
//        return PreferenceManager.getDefaultSharedPreferences(application);
//    }
//
//    @Provides
//    @Singleton
//    Cache provideHttpCache(Application application) {
//        int cacheSize = 10 * 1024 * 1024;
//        Cache cache = new Cache(application.getCacheDir(), cacheSize);
//        return cache;
//    }
//
//
//
//
//}
