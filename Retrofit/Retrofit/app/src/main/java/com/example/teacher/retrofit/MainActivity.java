package com.example.teacher.retrofit;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.teacher.retrofit.api.GitHubService;
import com.example.teacher.retrofit.api.models.LocationSet;
import com.example.teacher.retrofit.db.SqlDbHelper;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {
    private SqlDbHelper dbHelper;
    private TextView tvCustomDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCustomDbHelper = (TextView) findViewById(R.id.tv_custom_db_helper);

        dbHelper = new SqlDbHelper(this);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://api.sunrise-sunset.org/")
                .build();

        GitHubService service = restAdapter.create(GitHubService.class);
        service.listRepos(new Callback<LocationSet>() {
            @Override
            public void success(LocationSet locationSet, Response response) {
                Log.d("RESULT", locationSet.getResults().getDayLength() + "");
                dbHelper.insert(locationSet.getResults());

                Log.d("DATABASE", dbHelper.getAllData().get(0).getSunset());
                tvCustomDbHelper.setText("Retrofit result: Sunrise: " + locationSet.getResults().getSunrise() + ", Sunset:" + locationSet.getResults().getSunset() +
                        " ||||| DB Result Sunset: " + dbHelper.getAllData().get(0).getSunset() + ", Sunrise: " + dbHelper.getAllData().get(0).getSunrise());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

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
