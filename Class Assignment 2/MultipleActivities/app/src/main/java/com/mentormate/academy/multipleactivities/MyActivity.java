package com.mentormate.academy.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MyActivity extends Activity implements View.OnClickListener {

    private Button buttonRedActivity, buttonGreenActivity,
            buttonBlueActivity, buttonCustomColorActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        buttonRedActivity = (Button) findViewById(R.id.buttonRedActivity);
        buttonRedActivity.setOnClickListener(MyActivity.this);

        buttonGreenActivity = (Button) findViewById(R.id.buttonGreenActivity);
        buttonGreenActivity.setOnClickListener(MyActivity.this);

        buttonBlueActivity = (Button) findViewById(R.id.buttonBlueActivity);
        buttonBlueActivity.setOnClickListener(MyActivity.this);

        buttonCustomColorActivity = (Button) findViewById(R.id.buttonCustomColorActivity);
        buttonCustomColorActivity.setOnClickListener(MyActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonRedActivity:
                Intent intentRed = new Intent(MyActivity.this, RedActivity.class);
                startActivity(intentRed);
                break;
            case R.id.buttonGreenActivity:
                Intent intentGreen = new Intent(MyActivity.this, GreenActivity.class);
                startActivity(intentGreen);
                break;
            case R.id.buttonBlueActivity:
                Intent intentBlue = new Intent(MyActivity.this, BlueActivity.class);
                startActivity(intentBlue);
                break;
            case R.id.buttonCustomColorActivity:
                Intent intentCustomColor = new Intent(MyActivity.this, CustomColorActivity.class);
                startActivity(intentCustomColor);
                break;
        }
    }
}
