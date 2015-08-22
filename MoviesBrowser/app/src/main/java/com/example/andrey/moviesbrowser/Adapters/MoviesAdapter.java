package com.example.andrey.moviesbrowser.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andrey.moviesbrowser.Models.MovieInfo;
import com.example.andrey.moviesbrowser.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Andrey on 29.1.2015 г..
 */
public class MoviesAdapter extends BaseAdapter{
    private Context context;
    private int resource;
    private ArrayList<MovieInfo> movies;
    private MoviesHolderItem holder;

    public MoviesAdapter(Context context, int resource, ArrayList<MovieInfo> movies) {
        this.context = context;
        this.resource = resource;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public MovieInfo getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new MoviesHolderItem();

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource, parent, false);
            holder.image = (ImageView) convertView.findViewById(R.id.picMovie);
            holder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.year = (TextView) convertView.findViewById(R.id.tvYear);

            convertView.setTag(holder);
        } else {
            holder = (MoviesHolderItem) convertView.getTag();
        }

        String imageUrl = getItem(position).getImageUrl();
        //Bitmap myBitmap = getBitmapFromUrl(imageUrl);
        //holder.image.setImageBitmap(myBitmap);
        holder.title.setText(getItem(position).getTitle());
        holder.year.setText(getItem(position).getYear());

        return convertView;
    }

    private Bitmap getBitmapFromUrl(String urlStr){
        Bitmap bitmap;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            // Log exception
            return null;
        }

        return bitmap;
    }
}
