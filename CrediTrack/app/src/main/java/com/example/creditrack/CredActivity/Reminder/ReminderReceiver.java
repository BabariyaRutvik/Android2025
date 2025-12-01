package com.example.creditrack.CredActivity.Reminder;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.creditrack.R;

public class ReminderReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "payment_reminder_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        String amount = intent.getStringExtra("amount");
        String note = intent.getStringExtra("note");
        String personName = intent.getStringExtra("personName");

        // Show Notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // Create Channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Payment Reminders", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_cred) // Ensure you have this icon
                .setContentTitle("Payment Due Reminder")
                .setContentText("Payment of ₹ " + amount + " for " + personName + " is due.")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Payment of ₹ " + amount + " for " + personName + " is due.\nNote: " + note))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        // Check for permission before notifying
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify((int) System.currentTimeMillis(), builder.build());
        }
    }
}