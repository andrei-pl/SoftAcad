package com.mentoracademy.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class FirstActivity extends Activity implements View.OnClickListener {

    //UNIT testing -> http://rexstjohn.com/unit-testing-with-android-studio/

    private final String TAG = getClass().getSimpleName();
    Button incrementCount, newActivity, closeApp;
    static int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.d(TAG, "onCreate()");

        count = 0;

        Button incrementCountButton = (Button) findViewById(R.id.button);
        incrementCountButton.setOnClickListener(this);
        Button newActivityButton = (Button) findViewById(R.id.button2);
        newActivityButton.setOnClickListener(this);
        Button closeAppButton = (Button) findViewById(R.id.button3);
        closeAppButton.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {

        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {

        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {

        Log.d(TAG, "onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {

        Log.d(TAG, "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("COUNT");
        Log.d(TAG, "onRestoreInstanceState()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt("COUNT", count);
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState()");
    }

    @Override
    public void onClick(View v) {

        switch ( v.getId()) {
            case R.id.button:
                count++;

                int operand, resultOfDivision;
                try {
                    operand = 0;
                    resultOfDivision = count / operand;
                    Log.d(TAG, "Result is " + resultOfDivision);
                } catch (ArithmeticException e) { // catch divide-by-zero error
                    Log.d(TAG, "Division by zero. " + e);
                }
                Log.d(TAG, "After catch statement.");

                Log.wtf(TAG, "COUNT = " + count);
                break;
            case R.id.button2:
                Intent i = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(i);
                break;
            case R.id.button3:
                finish();
                break;
        }
    }
}
