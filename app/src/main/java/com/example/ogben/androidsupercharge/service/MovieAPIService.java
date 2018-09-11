package com.example.ogben.androidsupercharge.service;

import com.example.ogben.androidsupercharge.model.MoviePage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPIService {

    @GET("movie/top_rated")
    Call<MoviePage> getTopRatedMovies(@Query("api_key") String apiKey);

}
