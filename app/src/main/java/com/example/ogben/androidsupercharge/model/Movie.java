package com.example.ogben.androidsupercharge.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("title")
    private String title;

    @SerializedName("original_language")
    private String originalLanguage;

    public Movie(String posterPath, String releaseDate, String title, String originalLanguage) {
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.originalLanguage = originalLanguage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }
}
