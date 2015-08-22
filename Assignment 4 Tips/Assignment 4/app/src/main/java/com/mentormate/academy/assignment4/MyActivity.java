package com.mentormate.academy.assignment4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MyActivity extends Activity implements View.OnClickListener {

    ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
    public void onClick(View v) {

        if (v.getId() == R.id.button) {

            User newUser = new User();
            SingletonClass singleton = SingletonClass.getInstance();
            int uniqueId = singleton.getUserId();
            newUser.uniqueId = uniqueId;
            newUser.id = "user" +  uniqueId;
            users.add(newUser);

            Log.d("COUNT ", ""+users.size());
            for (User user:users) {

                Log.d("USER ID", user.id);
                Log.d("USER UNIQUE ID", ""+user.uniqueId);

            }

        }
    }
}
