package com.blogspot.ramannada.popularmovieapp.base;

import android.app.Application;

import com.blogspot.ramannada.popularmovieapp.data.SharedData;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by ramannada on 11/26/2017.
 */

public class App extends Application {
    private static OkHttpClient okHttpClient;
    @Override
    public void onCreate() {
        super.onCreate();
        SharedData.init(this);

        okHttpClient = new OkHttpClient();
    }

    public static OkHttpClient getClient() {
        return okHttpClient;
    }
}
