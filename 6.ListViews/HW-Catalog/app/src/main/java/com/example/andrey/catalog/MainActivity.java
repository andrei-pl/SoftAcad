package com.example.andrey.catalog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {
    private final int BUTTON_CARS_LIST_ID = 0;
    private final int BUTTON_ADD_CAR_ID = 1;
    private final int BUTTON_BACKGROUND_COLOR = 0xFF333333;
    private final int BUTTON_TEXT_COLOR = 0xFFFFFFFF;

    RelativeLayout relative;

    Button btnCarsListActivity;
    RelativeLayout.LayoutParams btnCarsListParams;

    Button btnAddCarActivity;
    RelativeLayout.LayoutParams btnAddCarParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btnCarsListActivity = (Button) findViewById(R.id.btnAllCarsActivity);
//        btnCarsListActivity.setOnClickListener(this);
//        btnAddCarActivity = (Button) findViewById(R.id.btnNewCarActivity);
//        btnAddCarActivity.setOnClickListener(this);
        relative = new RelativeLayout(this.getApplicationContext());
        relative.setBackgroundColor(0xFF8888FF);

        btnCarsListParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        btnCarsListParams.setMargins(0, 50, 0, 0);
        btnCarsListActivity = new Button(getApplicationContext());
        btnCarsListActivity.setLayoutParams(btnCarsListParams);
        btnCarsListActivity.setBackgroundColor(BUTTON_BACKGROUND_COLOR);
        btnCarsListActivity.setTextColor(BUTTON_TEXT_COLOR);
        btnCarsListActivity.setTextSize(32);
        btnCarsListActivity.setText("My Cars List");
        btnCarsListActivity.setId(BUTTON_CARS_LIST_ID);

        btnAddCarParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        btnAddCarParams.setMargins(0, 200, 0, 0);
        btnAddCarActivity = new Button(getApplicationContext());
        btnAddCarActivity.setLayoutParams(btnAddCarParams);
        btnAddCarActivity.setBackgroundColor(BUTTON_BACKGROUND_COLOR);
        btnAddCarActivity.setTextColor(BUTTON_TEXT_COLOR);
        btnAddCarActivity.setTextSize(32);
        btnAddCarActivity.setText("Add New Car");
        btnAddCarActivity.setId(BUTTON_ADD_CAR_ID);

        relative.addView(btnCarsListActivity);
        relative.addView(btnAddCarActivity);

        setContentView(relative);

        Toast.makeText(MainActivity.this, "Sorry for my late. I tried to make it better despite the problems I had with my laptop.", Toast.LENGTH_LONG).show();
        btnCarsListActivity.setOnClickListener(MainActivity.this);
        btnAddCarActivity.setOnClickListener(MainActivity.this);
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

    @Override
    public void onClick(View v) {
        int viewID = v.getId();
        Intent intent = new Intent();

        if(viewID == 0){
            intent = new Intent(MainActivity.this, CarsListActivity.class);
        } else if(viewID == 1){
            intent = new Intent(MainActivity.this, NewCarActivity.class);
        }

        startActivity(intent);
    }
}
