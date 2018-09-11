package com.example.ogben.androidsupercharge.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ogben.androidsupercharge.R;
import com.example.ogben.androidsupercharge.adapter.MovieAdapter;
import com.example.ogben.androidsupercharge.model.Movie;
import com.example.ogben.androidsupercharge.model.MoviePage;
import com.example.ogben.androidsupercharge.service.MovieAPIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public static final String BASEURL = "https://api.themoviedb.org/3/";
    private final static String API_KEY = "92290e0f78d9487afe3e01292483c2e3";
    private ListView mainListView;
    private Button loadButton;
    private TextView textView;
    private ArrayAdapter<Movie> adapter;
    private static Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainListView = findViewById(R.id.main_list_view);
        loadButton = findViewById(R.id.load_movies_button);
        textView = findViewById(R.id.main_text_view);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinAPIAndGetMovies();
                textView.setText("Api connected");
            }
        });
    }

    private void joinAPIAndGetMovies(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        MovieAPIService movieService = retrofit.create(MovieAPIService.class);
        Call<MoviePage> call = movieService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviePage>() {
            @Override
            public void onResponse(Call<MoviePage> call, Response<MoviePage> response) {
                List<Movie> movies = response.body().getResults();
                adapter = new MovieAdapter(getApplicationContext(), R.layout.movie_layout,  new ArrayList<>(movies));
                ListView listView = findViewById(R.id.main_list_view);
                listView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<MoviePage> call, Throwable throwable) {
                Log.e(MainActivity.class.getSimpleName(), throwable.toString());
            }
        });
    }
}
