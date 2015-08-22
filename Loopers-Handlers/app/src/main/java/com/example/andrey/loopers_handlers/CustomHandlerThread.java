package com.example.andrey.loopers_handlers;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * Created by Andrey on 5.2.2015 г..
 */
public class CustomHandlerThread extends HandlerThread {

    private Handler handler;

    public CustomHandlerThread(String name) {
        super(name);
    }

    public  void prepareHandler(){
        handler = new Handler(getLooper());
    }

    public void postTask(Runnable runnableObject) {
        runnableObject.run();
    }
}
