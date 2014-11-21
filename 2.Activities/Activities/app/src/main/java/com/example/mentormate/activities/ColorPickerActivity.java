package com.example.mentormate.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;


public class ColorPickerActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    Button btnBack;
    SeekBar seekR, seekG, seekB;
    EditText txtRed, txtGreen, txtBlue;
    int redC, greenC, blueC;

    RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        mainLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        btnBack = (Button) findViewById(R.id.btnBackColorPicker);
        btnBack.setOnClickListener(ColorPickerActivity.this);

        txtRed = (EditText) findViewById(R.id.editText);
        txtBlue = (EditText) findViewById(R.id.editText2);
        txtGreen = (EditText) findViewById(R.id.editText3);

        seekR = (SeekBar) findViewById(R.id.seekBar);
        seekG = (SeekBar) findViewById(R.id.seekBar2);
        seekB = (SeekBar) findViewById(R.id.seekBar3);

        txtRed.setText("0");
        seekR.setProgress(0);
        seekR.incrementProgressBy(1);
        seekR.setMax(255);
        seekR.setOnSeekBarChangeListener(ColorPickerActivity.this);

        txtGreen.setText("0");
        seekG.setProgress(0);
        seekG.incrementProgressBy(1);
        seekG.setMax(255);
        seekG.setOnSeekBarChangeListener(ColorPickerActivity.this);

        txtBlue.setText("0");
        seekB.setProgress(0);
        seekB.incrementProgressBy(1);
        seekB.setMax(255);
        seekB.setOnSeekBarChangeListener(ColorPickerActivity.this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_picker, menu);
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
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        redC = seekR.getProgress();
        greenC = seekG.getProgress();
        blueC = seekB.getProgress();
        mainLayout.setBackgroundColor(Color.rgb(redC, blueC, greenC));

        txtRed.setText(String.format("%s",redC));
        txtGreen.setText(String.format("%s",greenC));
        txtBlue.setText(String.format("%s",blueC));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnBackColorPicker){
            Intent intent = new Intent(ColorPickerActivity.this, MainActivity1.class);
            startActivity(intent);
        }
    }
}
