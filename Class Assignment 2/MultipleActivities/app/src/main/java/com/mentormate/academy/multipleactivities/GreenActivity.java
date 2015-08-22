package com.mentormate.academy.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class GreenActivity extends Activity implements View.OnClickListener {

    private Button buttonRedActivity, buttonBlueActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.green);

        buttonRedActivity = (Button) findViewById(R.id.greenLayoutRedButton);
        buttonRedActivity.setOnClickListener(GreenActivity.this);

        buttonBlueActivity = (Button) findViewById(R.id.greenLayoutBlueButton);
        buttonBlueActivity.setOnClickListener(GreenActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.greenLayoutRedButton:
                Intent intentRed = new Intent(GreenActivity.this, RedActivity.class);
                startActivity(intentRed);
                break;
            case R.id.greenLayoutBlueButton:
                Intent intentBlue = new Intent(GreenActivity.this, BlueActivity.class);
                startActivity(intentBlue);
                break;
        }
    }
}
