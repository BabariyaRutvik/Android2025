package com.example.statusbarnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "BMW";
    private static final int NOTIFICATION_ID = 100;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.bmw_logo,null);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;


        Bitmap largeIcon = bitmapDrawable.getBitmap();


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification notification;
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(this,REQUEST_CODE, i , PendingIntent.FLAG_UPDATE_CURRENT);

        // Big Picture Style

        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .bigPicture(((BitmapDrawable)
                        (ResourcesCompat.getDrawable
                                (getResources(),
                                        R.drawable.mahindra_be_6,null)))
                        .getBitmap())
                .bigLargeIcon(largeIcon)
                .setBigContentTitle("Image Sent By Mahindra")
                .setSummaryText("Mahindra");

        // Inbox Style

        Notification.InboxStyle inboxStyle= new Notification.InboxStyle()
                .addLine("A")
                .addLine("B")
                .addLine("C")
                .addLine("D")
                .addLine("E")
                .addLine("F")
                .addLine("G")
                .addLine("H")
                .addLine("I")
                .addLine("J")
                .setBigContentTitle("Full Message")
                .setSummaryText("Message Sent by Rutvik");



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.bmw1)
                    .setContentText("BMW X7")
                    .setSubText("I Wll Buy BMW")
                     .setContentIntent(pendingIntent)
                    .setChannelId(CHANNEL_ID)
                     //.setStyle(bigPictureStyle)
                     .setStyle(inboxStyle)
                     .setAutoCancel(false)
                     .setOngoing(true)
                    .build();

             notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"mY Channel",NotificationManager.IMPORTANCE_HIGH));

        }
        else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.bmw1)
                    .setContentText("BMW X7")
                    .setSubText("I Wll Buy BMW")
                    .setContentIntent(pendingIntent)
                    //.setStyle(bigPictureStyle)
                    .setStyle(inboxStyle)
                    .setAutoCancel(false)
                    .setOngoing(true)
                    .build();
        }
        notificationManager.notify(NOTIFICATION_ID,notification);



    }
}