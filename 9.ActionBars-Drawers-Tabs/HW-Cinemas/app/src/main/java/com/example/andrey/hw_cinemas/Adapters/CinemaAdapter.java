package com.example.andrey.hw_cinemas.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andrey.hw_cinemas.Models.Cinema;
import com.example.andrey.hw_cinemas.R;

import java.util.ArrayList;

/**
 * Created by Andrey on 31.12.2014 г..
 */
public class CinemaAdapter extends BaseAdapter {

    private Context context;
    private int resource;
    private ArrayList<Cinema> cinemas;
    private CinemaHolderItem holder;

    public CinemaAdapter(Context context, int resource, ArrayList<Cinema> cinemas) {
        this.context = context;
        this.resource = resource;
        this.cinemas = cinemas;
    }

    @Override
    public int getCount() {
        return cinemas.size();
    }

    @Override
    public Cinema getItem(int position) {
        return cinemas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            holder = new CinemaHolderItem();

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource, parent, false);
            holder.cinemaPic = (ImageView) convertView.findViewById(R.id.cinemaPic);
            holder.name = (TextView) convertView.findViewById(R.id.txtCinemaName);

            convertView.setTag(holder);
        } else {
            holder = (CinemaHolderItem) convertView.getTag();
        }

        holder.cinemaPic.setImageBitmap(getItem(position).getPicCinema1());
        holder.name.setText(getItem(position).getName());

        return convertView;
    }
}
