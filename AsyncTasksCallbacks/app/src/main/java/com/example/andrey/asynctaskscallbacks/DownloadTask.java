package com.example.andrey.asynctaskscallbacks;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Andrey on 12.1.2015 г..
 */
public class DownloadTask extends AsyncTask <String, Void, String> {
    @Override
    protected void onPreExecute() {
        Log.d("DOWNLOAD TASK", "Pre execute");
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("DOWNLOAD TASK", "Post execute. The result is: " + result);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";

        try {
            URL url = new URL(params[0]);

            HttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
            HttpGet httpGet = new HttpGet((String.valueOf(url)));
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();

            InputStream is = httpEntity.getContent();

            InputStreamReader inputStreamReader = new InputStreamReader(is, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader,8);

            StringBuilder stringBuilder = new StringBuilder();
            String readLine = null;

            while((readLine = bufferedReader.readLine()) != null){
                stringBuilder.append(readLine);
                stringBuilder.append("\n");
            }

            result = stringBuilder.toString();

            if(is != null){
                is.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
