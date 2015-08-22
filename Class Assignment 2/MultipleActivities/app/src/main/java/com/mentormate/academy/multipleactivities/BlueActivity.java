package com.mentormate.academy.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class BlueActivity extends Activity implements View.OnClickListener {

    private Button buttonRedActivity, buttonGreenActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blue);

        buttonRedActivity = (Button) findViewById(R.id.blueLayoutRedButton);
        buttonRedActivity.setOnClickListener(BlueActivity.this);

        buttonGreenActivity = (Button) findViewById(R.id.blueLayoutGreenButton);
        buttonGreenActivity.setOnClickListener(BlueActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.blueLayoutRedButton:
                Intent intentRed = new Intent(BlueActivity.this, RedActivity.class);
                startActivity(intentRed);
                break;
            case R.id.blueLayoutGreenButton:
                Intent intentGreen = new Intent(BlueActivity.this, GreenActivity.class);
                startActivity(intentGreen);
                break;
        }
    }
}
