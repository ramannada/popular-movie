package com.blogspot.ramannada.popularmovieapp.api;


import com.blogspot.ramannada.popularmovieapp.model.MovieResponse;
import com.blogspot.ramannada.popularmovieapp.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ramannada on 11/27/2017.
 */

public interface ApiMovieService {

    @GET("{filter}")
    Call<MoviesResponse> getListMovies(@Path("filter") String filter,
                                       @Query("page") int page,
                                       @Query("api_key") String apiKey);

    @GET("{id}")
    Call<MovieResponse> getMovieDetail(@Path("id") long id, @Query("api_key") String apiKey);
}
