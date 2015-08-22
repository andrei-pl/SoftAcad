package com.example.andrey.loopers_handlers;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CustomAsyncTask customAsyncTask = new CustomAsyncTask(this);
//        customAsyncTask.execute("http://google.com");

        CustomHandlerThread thread = new CustomHandlerThread("CustomHandlerThread");

        thread.start();
        thread.prepareHandler();

        Runnable runnableObject = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getBaseContext(), "text", Toast.LENGTH_SHORT).show();
            }
        };
        thread.postTask(runnableObject);
    }
}
