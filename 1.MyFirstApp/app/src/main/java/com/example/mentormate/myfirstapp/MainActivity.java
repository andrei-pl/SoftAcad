package com.example.mentormate.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    Button btnOK;
   // AnalogClock clock;
    EditText text;
    EditText year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOK = (Button) findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);
       // clock = (AnalogClock) findViewById(R.id.analogClock2);
        text = (EditText) findViewById(R.id.editText);
        year = (EditText) findViewById(R.id.edtYear);
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
        String strName = text.getText().toString();

        if(v.getId() == R.id.btnOK) {
            String yearStr = year.getText().toString();
            if (!yearStr.equals("")) {

                int yearBorn = Integer.parseInt(year.getText().toString());
                int age = 2014 - yearBorn;

                Toast.makeText(MainActivity.this, getString(R.string.hello_message) + ", " + strName + "! Your age is " + age, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, getString(R.string.hello_message) + ", " + strName + "!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
