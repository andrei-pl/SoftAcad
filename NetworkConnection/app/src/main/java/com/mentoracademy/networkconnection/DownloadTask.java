package com.mentoracademy.networkconnection;

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
import java.net.URL;

/**
 * Created by Stefan.Doychev on 12.01.2015.
 */

/*  An asynchronous task is defined by 3 generic types, called Params, Progress and Result
        and 4 steps, called onPreExecute, doInBackground, onProgressUpdate and onPostExecute.
    Params, the type of the parameters sent to the task upon execution.
    Progress, the type of the progress units published during the background computation.
    Result, the type of the result of the background computation.
*/

public class DownloadTask extends AsyncTask<String, Void, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("DOWNLOAD", "Starting now...");
    }

    @Override
    protected String doInBackground(String... params) {

        String resultString = "";

        try {

            URL url = new URL(params[0]);

            HttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
            HttpGet httpGet = new HttpGet(String.valueOf(url));
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            InputStream inputStream = entity.getContent();
            InputStreamReader byteReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(byteReader, 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            resultString = sb.toString();

            if(inputStream != null) {
                inputStream.close();
            }

        } catch (IOException e) {
            return e.toString();
        }

        return resultString;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("DOWNLOAD", "Just finished - " + result);
    }
}
