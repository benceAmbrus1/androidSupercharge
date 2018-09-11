package com.example.ogben.androidsupercharge.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviePage {

    @SerializedName("results")
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }
}
