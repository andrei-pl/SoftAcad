package com.example.andrey.loopers_handlers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Andrey on 5.2.2015 г..
 */
public class CustomAsyncTask extends AsyncTask<String, Void, Integer> {

    private Context context;

    CustomAsyncTask(Context context){
        this.context = context;
    }

    @Override
    protected Integer doInBackground(String... params) {
        HttpURLConnection connection;

        Toast.makeText(context, "text", Toast.LENGTH_SHORT).show();
        try {
            connection = (HttpURLConnection) new URL(params[0]).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }
}
