package com.example.andrey.gamebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.http.protocol.HTTP;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button answer1;
    private Button answer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answer1 = (Button) findViewById(R.id.btnChapter1Answer1);
        answer1.setOnClickListener(this);
        answer2 = (Button) findViewById(R.id.btnChapter1Answer2);
        answer2.setOnClickListener(this);
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
        Intent intent = new Intent();

        if(view == R.id.btnChapter1Answer1){
            intent = new Intent(MainActivity.this, Chapter3.class);
        } else if (view == R.id.btnChapter1Answer2){
            intent = new Intent(MainActivity.this, Chapter2.class);
        }

        startActivity(intent);
    }
}
