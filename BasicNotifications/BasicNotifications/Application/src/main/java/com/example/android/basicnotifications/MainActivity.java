package com.example.android.basicnotifications;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

/**
 * The entry point to the BasicNotification sample.
 */
public class MainActivity extends Activity {

    // Unique within this app, doesn't need to be unique system-wide.
    public static final int NOTIFICATION_ID = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_layout);
    }

    public void sendNotification(View view) {

        // Create an intent that will be fired when the user clicks the notification.
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://google.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity (this, 0, intent, 0);

        // Use NotificationCompat.Builder to set up our notification.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // Icon in the notification bar. Also appears in the lower right hand corner of the notification itself.
        builder.setSmallIcon(R.drawable.ic_stat_notification);
        // The content title, which appears in large type at the top of the notification
        builder.setContentTitle("CONTENT TITLE");
        // The content text, which appears in smaller text below the title
        builder.setContentText("Sample content text...");
        // Icon which appears on the left of the notification.
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        // The subtext, which appears under the text on newer devices.
        // Devices running versions of Android prior to 4.2 will ignore this field, so don't use it for anything vital!
        builder.setSubText("Sub text goes here. Only secondary info here.");
        // Notification will disappear after the user taps it, rather than remaining until it's explicitly dismissed.
        builder.setAutoCancel(true);
        // Set the intent that will fire when the user taps the notification.
        builder.setContentIntent(pendingIntent);

        // Immediately display the notification icon in the notification bar.
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
