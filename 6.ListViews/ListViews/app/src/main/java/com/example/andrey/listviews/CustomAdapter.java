package com.example.andrey.listviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andrey on 4.12.2014 г..
 */
public class CustomAdapter extends BaseAdapter {

    ArrayList<Sport> sportList = new ArrayList<Sport>();
    private Context context;

    public CustomAdapter(Context context){
        this.context = context;
        this.generateSports();
    }

    private void generateSports(){
        for (int i = 0; i < 100; i++) {
            Sport sport  = new Sport();
            sport.setId(i);
            sport.setName("Sport name " + i);
            sport.setParticipants(i);

            sportList.add(sport);
        }
    }
    @Override
    public int getCount() {
        return sportList.size();
    }

    @Override
    public Sport getItem(int position) {
        return sportList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return sportList.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView sportName;
        Button sportButton;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.sport_view, parent, false);
            sportName = (TextView) convertView.findViewById(R.id.sportName);
            sportButton = (Button) convertView.findViewById(R.id.btnSport);

            convertView.setTag(R.id.sportName, sportName);
            convertView.setTag(R.id.btnSport, sportButton);

        } else {
            sportName = (TextView) convertView.getTag(R.id.sportName);
            sportButton = (Button) convertView.getTag(R.id.btnSport);
        }

        sportName.setText("" + getItem(position).getName());
        sportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SPORT IS", "" + sportList.get(position).getName());
            }
        });

        return convertView;
    }
}
