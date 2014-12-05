package com.example.andrey.gamebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Chapter3 extends Activity implements View.OnClickListener {

    private final String SecretMessage = "You are not authorized to read this message!";

    private Button answer1;
    private Button answer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter3);

        answer1 = (Button) findViewById(R.id.btnChapter3Answer1);
        answer1.setOnClickListener(this);
        answer2 = (Button) findViewById(R.id.btnChapter3Answer2);
        answer2.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chapter3, menu);
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
        String messageTag = getResources().getString(R.string.secret_message);

        if(view == R.id.btnChapter3Answer1) {
            intent = new Intent(Chapter3.this, Chapter5.class);
            intent.putExtra(messageTag, SecretMessage);
        } else if(view == R.id.btnChapter3Answer2) {
            intent = new Intent(Chapter3.this, Chapter4.class);
        }

        startActivity(intent);
    }
}
