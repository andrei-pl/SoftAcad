package com.example.andrey.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.SimpleTimeZone;

public class WorkerService extends Service {

    public final static String ACTION_ASYNC = "com.example.andrey.service.ACTION_ASYNC";
    public final static String BROADCAST_RESULT = "com.example.andrey.service.BROADCAST_RESULT";
    public final static String KEY_MESSAGE = "KEY_MESAGE";
    public WorkerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(ACTION_ASYNC.equals(intent.getAction()))
        {
               new WorkerThread().start();
        } else {
            blockingPrint();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void blockingPrint() {
        int counter = 5;
        while (counter-- > 0){
            String message  = "This is the counter value" + counter;
            Log.d(WorkerService.class.getSimpleName(), message);
        }

        Intent intent = new Intent(BROADCAST_RESULT);
        intent.putExtra(KEY_MESSAGE, "MESSAGE");
        sendBroadcast(intent);

        try {
            Thread.sleep(counter * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class WorkerThread extends Thread{
        @Override
        public void run() {
            super.run();
            blockingPrint();
        }
    }
}
