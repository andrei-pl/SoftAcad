package com.example.mentormate.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {

    Button btnActivity2;
    Button btnActivity3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActivity2 = (Button) this.findViewById(R.id.btn1Activity2);
        btnActivity2.setOnClickListener(MainActivity.this);
        btnActivity3 = (Button) this.findViewById(R.id.btn1Activity3);
        btnActivity3.setOnClickListener(MainActivity.this);
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
        int view = v.getId();
        Intent intent;

        if(view == R.id.btn1Activity2){
            intent = new Intent(MainActivity.this, MainActivity2.class);
            this.startActivity(intent);
        } else if (view == R.id.btn1Activity3){
            intent = new Intent(MainActivity.this, Main3Activity.class);
            this.startActivity(intent);
        }
    }
}
