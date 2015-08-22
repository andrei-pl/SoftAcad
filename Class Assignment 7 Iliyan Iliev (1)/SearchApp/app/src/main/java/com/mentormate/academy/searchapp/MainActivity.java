package com.mentormate.academy.searchapp;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ArrayList<String> items;
    private  ListView listview;
    public View headerView;
    private ArrayAdapter<String> itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateData();

       itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(itemsAdapter);

        Intent intent = getIntent();
        handleIntent(intent);
    }

    private void populateData() {
        items = new ArrayList<>();
        for(int i=0;i<150;i++){
            if(i%3==0){
                items.add("b"+i);
            }
            else if(i%5==0){
                items.add("c"+i);
            }
            else{
                items.add("a"+i);
            }
        }
    }

    private void handleIntent(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    private void doMySearch(String query) {
        ArrayList<String> filteredItems = new ArrayList<>();

        for(String item: items){
            if(item.toLowerCase().contains(query.toLowerCase())){
                filteredItems.add(item);
            }
        }

        ArrayAdapter<String> filteredItemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredItems);
        listview.setAdapter(filteredItemsAdapter);

        headerView = View.inflate(this, R.layout.custom_header, null);
        if(listview.getHeaderViewsCount()==0){
            listview.addHeaderView(headerView);
        }

        headerView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listview.setAdapter(itemsAdapter);
                listview.removeHeaderView(headerView);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
       handleIntent(intent);
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
        else if(id == R.id.action_search){
            onSearchRequested();
        }

        return super.onOptionsItemSelected(item);
    }
}
