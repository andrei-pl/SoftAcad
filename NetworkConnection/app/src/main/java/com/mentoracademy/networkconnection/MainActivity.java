package com.mentoracademy.networkconnection;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //READ JSON DATA ---------------------------------------------------------------------------
        String jsonString = null;
        InputStream is = null;
        try {
            is = getAssets().open("lecture13.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray countriesArray = jsonObject.getJSONArray("countries");
            for (int i = 0; i < countriesArray.length(); i++) {
                try {
                    JSONObject country = countriesArray.getJSONObject(i);
                    Log.d("JSON READING", country.getString("name")
                            + "'s population is " + country.getString("inhabitants"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            JSONArray citiesArray = jsonObject.getJSONArray("cities");
            for (int i = 0; i < citiesArray.length(); i++) {
                try {
                    JSONObject city = citiesArray.getJSONObject(i);
                    Log.d("JSON READING", city.getString("cityName")
                            + "'s population is " + city.getString("inhabitants"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //READ JSON DATA END -----------------------------------------------------------------------

        //DOWNLOAD FROM THE WEB --------------------------------------------------------------------
        DownloadTask downloadFromWeb = new DownloadTask();
        //downloadFromWeb.execute("http://www.google.com");
        downloadFromWeb.execute("http://www.omdbapi.com/?t=the&y=2014&plot=full&r=json");
        //downloadFromWeb.execute("http://api.openweathermap.org/data/2.5/forecast/daily?lat=42.69751&lon=23.32415&cnt=10&mode=json&units=metric");
        //DOWNLOAD FROM THE WEB END ----------------------------------------------------------------

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
