package com.mentormate.academy.multipleactivities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class CustomColorActivity extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private SeekBar redBar, greenBar, blueBar;
    private TextView redLabel, greenLabel, blueLabel;
    private RelativeLayout customColorLayout;

    public int redValue = 0;
    public int greenValue = 0;
    public int blueValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customcolor);

        Button setCustomColorButton = (Button) findViewById(R.id.buttonSetCustomColor);
        setCustomColorButton.setOnClickListener(this);

        redBar = (SeekBar) findViewById(R.id.seekBarRed);
        redBar.setOnSeekBarChangeListener(this);
        greenBar = (SeekBar) findViewById(R.id.seekBarGreen);
        greenBar.setOnSeekBarChangeListener(this);
        blueBar = (SeekBar) findViewById(R.id.seekBarBlue);
        blueBar.setOnSeekBarChangeListener(this);

        redLabel = (TextView) findViewById(R.id.labelRed);
        greenLabel = (TextView) findViewById(R.id.labelGreen);
        blueLabel = (TextView) findViewById(R.id.labelBlue);

        customColorLayout = (RelativeLayout) findViewById(R.id.customColorLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonSetCustomColor:
                updateBackgroundColor();
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        switch (seekBar.getId()) {

            case R.id.seekBarRed:
                redValue = redBar.getProgress();
                redLabel.setText(String.valueOf(redValue));
                break;
            case R.id.seekBarGreen:
                greenValue = greenBar.getProgress();
                greenLabel.setText(String.valueOf(greenValue));
                break;
            case R.id.seekBarBlue:
                blueValue = blueBar.getProgress();
                blueLabel.setText(String.valueOf(blueValue));
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

        //TODO
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        //TODO
    }

    public void updateBackgroundColor() {

        String backgroundColor = createRGBColor(redValue, greenValue, blueValue);
        String formattedBgrColor = "#" + backgroundColor;
        customColorLayout.setBackgroundColor(Color.parseColor(formattedBgrColor));
    }

    private String createRGBColor(int redValue, int greenValue, int blueValue) {

        String redColor = Integer.toHexString(redValue);
        String greenColor = Integer.toHexString(greenValue);
        String blueColor = Integer.toHexString(blueValue);
        String RGBColor = adjustHexValue(redColor) + adjustHexValue(greenColor) + adjustHexValue(blueColor);
        return RGBColor;
    }

    private String adjustHexValue(String inputColor) {

        StringBuilder sb = new StringBuilder();
        sb.append(inputColor);
        if (sb.length() < 2) {
            sb.insert(0, '0');  //Color value has to become 00 if was single digit only to become valid hex color.
        }
        return sb.toString();
    }
}
