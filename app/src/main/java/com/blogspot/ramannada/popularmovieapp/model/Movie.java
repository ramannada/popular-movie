package com.blogspot.ramannada.popularmovieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramannada on 11/25/2017.
 */

public class Movie {
    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("belongs_to_collection")
    private MovieCollection belongsToCollection;
    @SerializedName("budget")
    private long budget;
    @SerializedName("genres")
    private List genres = new ArrayList();
    @SerializedName("homepage")
    private String homePage;
    @SerializedName("id")
    private long id;
    @SerializedName("imdb_id")
    private long imdbId;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("production_companies")
    private List productionCompanies = new ArrayList();
    @SerializedName("production_countries")
    private List productionCountries = new ArrayList();
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("revenue")
    private long revenue;
    @SerializedName("runtime")
    private int runtime;
    @SerializedName("spoken_languages")
    private List spokenLanguages = new ArrayList();
    @SerializedName("status")
    private String status;
    @SerializedName("tag_line")
    private String tagLine;
    @SerializedName("title")
    private String title;
    @SerializedName("video")
    private boolean video;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("vote_count")
    private long voteCount;

    public Movie(Boolean adult, String backdropPath, MovieCollection belongsToCollection,
                 List genres, String homePage, long id, long imdbId, String originalTitle,
                 String overview, Double popularity, String posterPath, List productionCompanies,
                 String releaseDate, String title, double voteAverage) {
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.belongsToCollection = belongsToCollection;
        this.genres = genres;
        this.homePage = homePage;
        this.id = id;
        this.imdbId = imdbId;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompanies = productionCompanies;
        this.releaseDate = releaseDate;
        this.title = title;
        this.voteAverage = voteAverage;
    }

    public Movie(String title) {
        this.title = title;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public MovieCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(MovieCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public List getGenres() {
        return genres;
    }

    public void setGenres(List genres) {
        this.genres = genres;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getImdbId() {
        return imdbId;
    }

    public void setImdbId(long imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }



}
