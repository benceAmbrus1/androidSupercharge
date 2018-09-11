package com.example.ogben.androidsupercharge.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("title")
    private String title;

    public Movie(String posterPath, String releaseDate, String title) {
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
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
}
