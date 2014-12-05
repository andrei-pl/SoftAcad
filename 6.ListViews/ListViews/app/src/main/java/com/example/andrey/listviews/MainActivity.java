package com.example.andrey.listviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private final String[] sports = new String[]{"Rugby", "Football", "Boxing", "Tennis", "Basketball", "Sport dancing", "Swimming", "Rugby 2", "Football 2", "Tennis 2", "Rugby 2", "Football 2", "Tennis 2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);

        //ListView list = (ListView) findViewById(R.id.list);
        GridView list = (GridView) findViewById(R.id.gridView);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.sport_view, R.id.sportName, sports);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, sports);

        CustomAdapter customAdapter = new CustomAdapter(this);

        list.setAdapter(adapter);
//        if(customAdapter.getCount() > 50){
//            list.setFastScrollEnabled(true);
//        }

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, sports[position], Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, sports[position] + " - Long clicked", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
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
