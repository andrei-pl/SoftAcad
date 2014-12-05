package com.example.andrey.gamebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.http.protocol.HTTP;


public class Chapter5 extends Activity implements View.OnClickListener {

    private Button answer1;
    private Button answer2;
    private String secretMessage;
    private String messageTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter5);

        Intent receivedIntent = getIntent();
        messageTag = getResources().getString(R.string.secret_message);

        secretMessage = receivedIntent.getStringExtra(messageTag);

        answer1 = (Button) findViewById(R.id.btnChapter5Answer1);
        answer1.setOnClickListener(this);
        answer2 = (Button) findViewById(R.id.btnChapter5Answer2);
        answer2.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chapter5, menu);
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
        String title = getResources().getString(R.string.chooser_title);

        Intent intent = new Intent();

        if (view == R.id.btnChapter5Answer1){

            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, secretMessage);
            intent.setType(HTTP.PLAIN_TEXT_TYPE);
            Intent chooser = Intent.createChooser(intent, title);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }
        } else if (view == R.id.btnChapter5Answer2){

            intent = new Intent(Chapter5.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
