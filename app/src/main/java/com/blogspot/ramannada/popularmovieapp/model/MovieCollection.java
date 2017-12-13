package com.blogspot.ramannada.popularmovieapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ramannada on 11/27/2017.
 */

public class MovieCollection {
    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;

    public MovieCollection(Long id, String name, String posterPath, String backdropPath) {
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

}
