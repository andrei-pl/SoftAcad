package com.example.andrey.countrieslist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andrey on 4.12.2014 г..
 */
public class CustomAdapter extends BaseAdapter{
    private final String[] countries = new String[]{"Austria", "Belgium", "Boxing", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "United Kingdom"};

    private ArrayList<Country> countryList = new ArrayList<Country>();
    private Context context;


    public CustomAdapter(Context context){
        this.context = context;
        this.generateCountries();
    }

    private void generateCountries() {
        for (int i = 0; i < countries.length; i++) {
            Country country = new Country();

            country.setName(countries[i]);
            country.setId(i);

            countryList.add(country);
        }
    }

    @Override
    public int getCount() {
        return countryList.size();
    }

    @Override
    public Country getItem(int position) {
        return countryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return countryList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView countryName;
        //ImageView imgFlag;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.countries_list, parent, false);
            countryName = (TextView) convertView.findViewById(R.id.countryName);
            //imgFlag = (Button) convertView.findViewById(R.id.btnSport);

            convertView.setTag(R.id.countryName, countryName);
            //convertView.setTag(R.id.btnSport, imgFlag);

        } else {
            countryName = (TextView) convertView.getTag(R.id.countryName);
            //imgFlag = (Button) convertView.getTag(R.id.btnSport);
        }

        countryName.setText("" + getItem(position).getName());
        //imgFlag.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("SPORT IS", "" + sportList.get(position).getName());
//            }
//        });

        return convertView;
    }
}
