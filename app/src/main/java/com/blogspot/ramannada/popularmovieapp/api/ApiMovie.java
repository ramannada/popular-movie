package com.blogspot.ramannada.popularmovieapp.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ramannada on 11/27/2017.
 */

public class ApiMovie {
    private static String BASE_MOVIE_URL = "https://api.themoviedb.org/3/movie/";
    public static String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";
    private static Retrofit apiMovie = null;
    public static final String API_KEY = "b26733daf3a5f7fd722800d1110e79b8";

    public static Retrofit getMovieClient() {
        if (apiMovie == null) {
            apiMovie = new Retrofit.Builder()
                    .client(new OkHttpClient())
                    .baseUrl(BASE_MOVIE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return apiMovie;
    }

    public static ApiMovieService getMovieService() {
        return getMovieClient().create(ApiMovieService.class);
    }
}
