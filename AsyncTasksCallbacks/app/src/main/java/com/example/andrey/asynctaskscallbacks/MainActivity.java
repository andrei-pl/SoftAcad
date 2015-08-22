package com.example.andrey.asynctaskscallbacks;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

        String jsonString = null;
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("countries.json");
            int size = inputStream.available();
            byte[] byteArray = new byte[size];
            inputStream.read(byteArray);
            inputStream.close();
            jsonString = new String(byteArray, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray countries = jsonObject.getJSONArray("countries");

            for (int i=0; i < countries.length(); i++){
                JSONObject country = countries.getJSONObject(i);
                Log.d("JSON Reading", "Country: " + country.getString("name") + " with code: " + country.getString("code"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String urlAddress = "http://www.omdbapi.com/?t=the&y=2014&plot=short&r=json";
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(urlAddress);

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
