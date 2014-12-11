package com.example.andrey.catalog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andrey.catalog.Models.Car;
import com.google.gson.Gson;

import org.w3c.dom.Text;


public class DetailInfoActivity extends Activity {
    private final int TEXT_COLOR = 0xFF333333;
    private final int LAYOUT_BACKGROUND_COLOR = 0xFFFFFF00;

    LinearLayout linear;

    TextView txtModel;
    LayoutParams txtModelParams;

    TextView txtYear;
    LayoutParams txtYearParams;

    TextView txtCondition;
    LayoutParams txtConditionParams;

    ImageView image;
    LayoutParams imageParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail_info);
//
//        String carObject = getResources().getString(R.string.car_object);
//        String jsonCar = (getIntent().getStringExtra(carObject));
//        Gson gson = new Gson();
//        Car carInfo = gson.fromJson(jsonCar, Car.class);
//
//        txtModel = (TextView) findViewById(R.id.txtModel);
//        txtYear = (TextView) findViewById(R.id.txtYear);
//        txtCondition = (TextView) findViewById(R.id.txtCondition);
//        image = (ImageView) findViewById(R.id.imgInfo);
//
//        txtModel.setText(carInfo.getModel());
//        txtYear.setText(carInfo.getYear());
//        txtCondition.setText(carInfo.getCondition());
//        image.setImageBitmap(carInfo.getImage());

        linear = new LinearLayout(this.getApplicationContext());
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setBackgroundColor(LAYOUT_BACKGROUND_COLOR);

        String carObject = getResources().getString(R.string.car_object);
        String jsonCar = (getIntent().getStringExtra(carObject));
        Gson gson = new Gson();
        Car carInfo = gson.fromJson(jsonCar, Car.class);

        txtModelParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        txtModel = new TextView(this.getApplicationContext());
        txtModel.setLayoutParams(txtModelParams);
        txtModel.setTextSize(34);
        txtModel.setText(carInfo.getModel());
        txtModel.setTextColor(TEXT_COLOR);
        txtModel.setGravity(1);

        txtYearParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        txtYear = new TextView(this.getApplicationContext());
        txtYear.setLayoutParams(txtYearParams);
        txtYear.setTextSize(34);
        txtYear.setText(carInfo.getYear());
        txtYear.setTextColor(TEXT_COLOR);
        txtYear.setGravity(1);

        txtConditionParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        txtCondition = new TextView(this.getApplicationContext());
        txtCondition.setLayoutParams(txtConditionParams);
        txtCondition.setTextSize(34);
        txtCondition.setText(carInfo.getCondition());
        txtCondition.setTextColor(TEXT_COLOR);
        txtCondition.setGravity(1);

        imageParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        image = new ImageView(this.getApplicationContext());
        image.setLayoutParams(imageParams);
        image.setImageBitmap(carInfo.getImage());
        image.setMinimumHeight(300);
        image.setMinimumWidth(400);
        image.setMaxHeight(300);
        image.setMaxWidth(400);

        linear.addView(image);
        linear.addView(txtModel);
        linear.addView(txtYear);
        linear.addView(txtCondition);

        setContentView(linear);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_info, menu);
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
