package com.example.andrey.mycars;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andrey.mycars.Models.Car;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class DetailInfoActivity extends ActionBarActivity implements View.OnClickListener {
    private final int TEXT_COLOR = 0xFF333333;
    private final int LAYOUT_BACKGROUND_COLOR = 0xFFFFFF00;

    LinearLayout linear;

    TextView txtModel;
    ViewGroup.LayoutParams txtModelParams;

    TextView txtYear;
    ViewGroup.LayoutParams txtYearParams;

    TextView txtCondition;
    ViewGroup.LayoutParams txtConditionParams;

    ImageView image;
    ViewGroup.LayoutParams imageParams;
    ArrayList<Car> cars;
    String carObject;
    int position;
    Car carInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parse.initialize(this, "sCCxWbG55eT387mS6E2Drim79L1FLX2D5OCWTLYA", "qTBkWsIihPxk2l9qeNLu2YIawgjen3XgaHrV96Vr");
        cars = new ArrayList<Car>();
        linear = new LinearLayout(this.getApplicationContext());
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setBackgroundColor(LAYOUT_BACKGROUND_COLOR);

        carObject = getResources().getString(R.string.car_object);
        position = getIntent().getIntExtra(carObject, 0);
        getCarsFromParse();
    }

    private void addInfo(){
        txtModelParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtModel = new TextView(this.getApplicationContext());
        txtModel.setLayoutParams(txtModelParams);
        txtModel.setTextSize(34);
        txtModel.setText(carInfo.getModel());
        txtModel.setTextColor(TEXT_COLOR);
        txtModel.setGravity(1);

        txtYearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtYear = new TextView(this.getApplicationContext());
        txtYear.setLayoutParams(txtYearParams);
        txtYear.setTextSize(34);
        txtYear.setText(carInfo.getYear());
        txtYear.setTextColor(TEXT_COLOR);
        txtYear.setGravity(1);

        txtConditionParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtCondition = new TextView(this.getApplicationContext());
        txtCondition.setLayoutParams(txtConditionParams);
        txtCondition.setTextSize(34);
        txtCondition.setText(carInfo.getCondition());
        txtCondition.setTextColor(TEXT_COLOR);
        txtCondition.setGravity(1);

        imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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

    private void getCarsFromParse() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("MyCars");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> carsObject, ParseException e) {
                if (e == null) {
                    for (ParseObject obj : carsObject) {
                        Car car = new Car(obj.getString("model"), obj.getString("year"), obj.getString("condition"));
                        ParseFile parseImage = obj.getParseFile("image");

                        byte[] data = new byte[0];
                        try {
                            data = parseImage.getData();
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }

                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        car.setImage(bitmap);

                        cars.add(car);
                    }
                    carInfo = cars.get(position);

                    addInfo();
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
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

    @Override
    public void onClick(View v) {

    }
}
