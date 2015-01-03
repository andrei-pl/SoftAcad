package com.example.andrey.hw_cinemas.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andrey.hw_cinemas.Models.Cinema;
import com.example.andrey.hw_cinemas.Models.Movie;
import com.example.andrey.hw_cinemas.R;

import java.util.ArrayList;

/**
 * Created by Andrey on 3.1.2015 г..
 */
public class MovieAdapter extends BaseAdapter {

    private Context context;
    private int resource;
    private ArrayList<Movie> movies;
    private MovieHolderItem holder;

    public MovieAdapter(Context context, int resource, ArrayList<Movie> movies) {
        this.context = context;
        this.resource = resource;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            holder = new MovieHolderItem();

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource, parent, false);
            holder.moviePic = (ImageView) convertView.findViewById(R.id.moviePic);
            holder.name = (TextView) convertView.findViewById(R.id.txtMovieName);

            convertView.setTag(holder);
        } else {
            holder = (MovieHolderItem) convertView.getTag();
        }

        holder.moviePic.setImageBitmap(getItem(position).getPicMovie());
        holder.name.setText(getItem(position).getName());

        return convertView;
    }
}