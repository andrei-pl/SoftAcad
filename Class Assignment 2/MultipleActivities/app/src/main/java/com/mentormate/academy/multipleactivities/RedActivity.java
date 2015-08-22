package com.mentormate.academy.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class RedActivity extends Activity implements View.OnClickListener {

    private Button buttonGreenActivity, buttonBlueActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.red);

        buttonGreenActivity = (Button) findViewById(R.id.redLayoutGreenButton);
        buttonGreenActivity.setOnClickListener(RedActivity.this);

        buttonBlueActivity = (Button) findViewById(R.id.redLayoutBlueButton);
        buttonBlueActivity.setOnClickListener(RedActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.redLayoutGreenButton:
                Intent intentGreen = new Intent(RedActivity.this, GreenActivity.class);
                startActivity(intentGreen);
                break;
            case R.id.redLayoutBlueButton:
                Intent intentBlue = new Intent(RedActivity.this, BlueActivity.class);
                startActivity(intentBlue);
                break;
        }
    }
}
