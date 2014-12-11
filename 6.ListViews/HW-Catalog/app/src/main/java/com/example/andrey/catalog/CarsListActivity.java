package com.example.andrey.catalog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.example.andrey.catalog.Adapters.CarListAdapter;
import com.example.andrey.catalog.Models.Car;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class CarsListActivity extends Activity implements View.OnClickListener {
    private final int BUTTON_CARS_LIST_ID = 99;
    private final int GREY_COLOR = 0xFF333333;
    private final int YELLOW_COLOR = 0xFFFFFF00;
    private final int BUTTON_TEXT_COLOR = 0xFFFFFFFF;

    LinearLayout linear;

    TextView titleText;
    ViewGroup.LayoutParams titleTextParams;

    ListView listView;
    ViewGroup.LayoutParams listViewParams;

    Button btnAddCar;
    ViewGroup.LayoutParams btnAddCarParams;

    ArrayList<Car> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parse.initialize(this, "sCCxWbG55eT387mS6E2Drim79L1FLX2D5OCWTLYA", "qTBkWsIihPxk2l9qeNLu2YIawgjen3XgaHrV96Vr");

        linear = new LinearLayout(this.getApplicationContext());
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setBackgroundColor(YELLOW_COLOR);

        titleTextParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        titleText = new TextView(this.getApplicationContext());
        titleText.setLayoutParams(titleTextParams);
        titleText.setTextSize(34);
        titleText.setText("All my cars");
        titleText.setTextColor(GREY_COLOR);

        btnAddCarParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        btnAddCar = new Button(getApplicationContext());
        btnAddCar.setLayoutParams(btnAddCarParams);
        btnAddCar.setBackgroundColor(GREY_COLOR);
        btnAddCar.setTextColor(BUTTON_TEXT_COLOR);
        btnAddCar.setTextSize(32);
        btnAddCar.setText("Add new Car");
        btnAddCar.setId(BUTTON_CARS_LIST_ID);

        btnAddCar.setOnClickListener(CarsListActivity.this);

        listViewParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        listView = new ListView(this.getApplicationContext());
        listView.setLayoutParams(listViewParams);

        linear.addView(titleText);
        linear.addView(btnAddCar);
        linear.addView(listView);
        setContentView(linear);

        //setContentView(R.layout.activity_cars_list);

//        btnAddCar = (Button) findViewById(R.id.btnAddCar);
//        btnAddCar.setOnClickListener(this);
//
//        listView = (ListView) this.findViewById(R.id.listViewCars);

        cars = new ArrayList<Car>();

        getCarsFromParse();
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

                    populateCars();
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }

    private void populateCars() {
        CarListAdapter carAdapter = new CarListAdapter(this,R.layout.car_view, cars);
        listView.setAdapter(carAdapter);

        if(carAdapter.getCount() > 20){
            listView.setFastScrollEnabled(true);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String carInfo = getResources().getString(R.string.car_object);

                //using Gson to pass "Car" object to another activity
                //if you have compilation error, you must add a reference to it
                Gson gson = new Gson();
                String myJson = gson.toJson(cars.get(position));

                Intent intent = new Intent(CarsListActivity.this, DetailInfoActivity.class);
                intent.putExtra(carInfo, myJson);
                startActivity(intent);
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

    @Override
    public void onClick(View v) {
        int view = v.getId();
        Intent intent = new Intent();

        if(view == 99){
            intent = new Intent(CarsListActivity.this, NewCarActivity.class);
        }

        startActivity(intent);
    }
}
