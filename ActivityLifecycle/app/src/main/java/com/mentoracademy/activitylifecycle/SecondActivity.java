package com.mentoracademy.activitylifecycle;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class SecondActivity extends Activity {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView calculatorOutput = (TextView) findViewById(R.id.calculatorOutput);
        Typeface calculatorFont = Typeface.createFromAsset(getAssets(), "digital-7.ttf");
        calculatorOutput.setTypeface(calculatorFont);

        int operand, resultOfDivision;
        int count = 10;
        try {
            operand = 0;
            resultOfDivision = count / operand;
            Log.d(TAG, "Result is " + resultOfDivision);
        } catch (ArithmeticException e) { // catch divide-by-zero error
            Log.d(TAG, "Division by zero. " + e);
        }
        Log.d(TAG, "After catch statement.");
    }
}
