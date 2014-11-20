package com.example.mentormate.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;


public class ColorPickerActivity extends Activity {

    SeekBar seekR;
    SeekBar seekG;
    SeekBar seekB;

    Layout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        seekR = (SeekBar) findViewById(R.id.seekBar);
        seekG = (SeekBar) findViewById(R.id.seekBar2);
        seekB = (SeekBar) findViewById(R.id.seekBar3);

//        seekR.setProgress(0);
//        seekR.incrementProgressBy(1);
//        seekR.setMax(255);
//
//        seekG.setProgress(0);
//        seekG.incrementProgressBy(1);
//        seekG.setMax(255);
//
//        seekB.setProgress(0);
//        seekB.incrementProgressBy(1);
//        seekB.setMax(255);
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
}
