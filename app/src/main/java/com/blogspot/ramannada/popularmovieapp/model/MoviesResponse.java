package com.blogspot.ramannada.popularmovieapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by ramannada on 11/27/2017.
 */

public class MoviesResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("total_result")
    private int totalResult;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
