package com.example.mentormate.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity1 extends Activity implements View.OnClickListener{

    Button btnActivity1;
    Button btnActivity2;
    Button btnActivity3;
    Button btnChooseColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity1);

        btnActivity1 = (Button) this.findViewById(R.id.btnMainActivity1);
        btnActivity1.setOnClickListener(MainActivity1.this);
        btnActivity2 = (Button) this.findViewById(R.id.btnMainActivity2);
        btnActivity2.setOnClickListener(MainActivity1.this);
        btnActivity3 = (Button) this.findViewById(R.id.btnMainActivity3);
        btnActivity3.setOnClickListener(MainActivity1.this);
        btnChooseColor = (Button) this.findViewById(R.id.btnColorPicker);
        btnChooseColor.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity1, menu);
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
        Intent intent = new Intent(MainActivity1.this, MainActivity.class);

        if (view == R.id.btnMainActivity2){
            intent = new Intent(MainActivity1.this, MainActivity2.class);
        } else if (view == R.id.btnMainActivity3){
            intent = new Intent(MainActivity1.this, Main3Activity.class);
        } else if (view == R.id.btnColorPicker){
            intent = new Intent(this, ColorPickerActivity.class);
        }

        this.startActivity(intent);
    }
}
