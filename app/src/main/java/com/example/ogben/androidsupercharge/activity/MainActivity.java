package com.example.ogben.androidsupercharge.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView mainOverview;
    private ArrayAdapter<Movie> adapter;
    private Retrofit retrofit;
    private Button filterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainListView = findViewById(R.id.main_list_view);
        loadButton = findViewById(R.id.load_movies_button);
        mainOverview = findViewById(R.id.main_text_view);
        filterButton = findViewById(R.id.filter_button);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinAPIAndGetMovies();
                Toast.makeText(MainActivity.this, "API Connected", Toast.LENGTH_SHORT).show();
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(getApplicationContext(), FilterActivity.class);
                startActivity(filterIntent);
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
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Movie movie = (Movie) parent.getAdapter().getItem(position);
                        Toast.makeText(MainActivity.this, "Popularity: " + movie.getPopularity(), Toast.LENGTH_LONG).show();
                        mainOverview.setText(movie.getOverview());
                    }
                });
                listView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<MoviePage> call, Throwable throwable) {
                Log.e(MainActivity.class.getSimpleName(), throwable.toString());
            }
        });
    }
}
