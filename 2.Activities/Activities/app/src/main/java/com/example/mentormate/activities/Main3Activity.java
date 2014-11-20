package com.example.mentormate.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Main3Activity extends Activity implements View.OnClickListener {

    Button btnActivity1;
    Button btnActivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnActivity2 = (Button) this.findViewById(R.id.btn3Activity2);
        btnActivity2.setOnClickListener(Main3Activity.this);
        btnActivity1 = (Button) this.findViewById(R.id.btn3Activity1);
        btnActivity1.setOnClickListener(Main3Activity.this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main3, menu);
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

        if(view == R.id.btn3Activity1){
            intent = new Intent(Main3Activity.this, MainActivity.class);
            this.startActivity(intent);
        } else if (view == R.id.btn3Activity2){
            intent = new Intent(Main3Activity.this, MainActivity2.class);
            this.startActivity(intent);
        }
    }
}
