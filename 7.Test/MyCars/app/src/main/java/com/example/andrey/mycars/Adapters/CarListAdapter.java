package com.example.andrey.mycars.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andrey.mycars.Models.Car;
import com.example.andrey.mycars.R;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by Andrey on 12/15/2014.
 */
public class CarListAdapter extends BaseAdapter {

    private Context context;
    private int resource;
    private ArrayList<Car> cars;
    private CarHolderItem holder;

    public CarListAdapter(Context context, int resource, ArrayList<Car> cars) {
        this.context = context;
        this.resource = resource;
        this.cars = cars;
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Car getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("MyCars");

        if (convertView == null) {
            holder = new CarHolderItem();

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resource, parent, false);
            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            holder.model = (TextView) convertView.findViewById(R.id.tvCarName);
            holder.year = (TextView) convertView.findViewById(R.id.tvYear);
            holder.condition = (TextView) convertView.findViewById(R.id.tvCondition);

            convertView.setTag(holder);
        } else {
            holder = (CarHolderItem) convertView.getTag();
        }

        holder.image.setImageBitmap(getItem(position).getImage());
        holder.model.setText(getItem(position).getModel());
        holder.year.setText(getItem(position).getYear());
        holder.condition.setText(getItem(position).getCondition());

        return convertView;
    }
}
