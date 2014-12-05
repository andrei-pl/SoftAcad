package com.example.andrey.gamebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Chapter4 extends Activity implements View.OnClickListener {
    private final String SecretMessage = "You are the writer of your life.";

    private Button answer1;
    private Button answer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter4);

        answer1 = (Button) findViewById(R.id.btnChapter4Answer1);
        answer1.setOnClickListener(this);
        answer2 = (Button) findViewById(R.id.btnChapter4Answer2);
        answer2.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chapter4, menu);
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
        String messageTag = getResources().getString(R.string.secret_message);
        int view = v.getId();
        Intent intent = new Intent();

        if(view == R.id.btnChapter4Answer1){
            intent = new Intent(Chapter4.this, Chapter5.class);
            intent.putExtra(messageTag, SecretMessage);
        } else if (view == R.id.btnChapter4Answer2){
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        }

        startActivity(intent);
    }
}
