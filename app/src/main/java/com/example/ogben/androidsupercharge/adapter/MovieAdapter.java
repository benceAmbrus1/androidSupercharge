package com.example.ogben.androidsupercharge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ogben.androidsupercharge.R;
import com.example.ogben.androidsupercharge.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private int listItemLayout;
    public Context context;
    private String IMAGEBASEPATH="http://image.tmdb.org/t/p/w342";

    public MovieAdapter(Context context, int layoutId, List<Movie> movies) {
        super(context, layoutId, movies);
        this.listItemLayout = layoutId;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        MovieViewHolder viewHolder;

        if ( convertView == null){
            viewHolder = new MovieViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(listItemLayout, parent, false);
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.releaseDate = convertView.findViewById(R.id.release_date);
            viewHolder.language = convertView.findViewById(R.id.language);
            viewHolder.movieImage = convertView.findViewById(R.id.movie_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MovieViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(movie.getTitle());
        viewHolder.releaseDate.setText(movie.getReleaseDate());
        viewHolder.language.setText(movie.getOriginalLanguage());
        String image_url = IMAGEBASEPATH + movie.getPosterPath();
        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .into(viewHolder.movieImage);

        return convertView;
    }

    private static class MovieViewHolder {
        TextView title;
        TextView releaseDate;
        TextView language;
        ImageView movieImage;
    }
}
