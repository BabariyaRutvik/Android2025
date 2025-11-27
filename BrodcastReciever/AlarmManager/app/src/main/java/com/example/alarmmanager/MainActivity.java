package com.example.alarmmanager;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextInputEditText editTextTime;
    Button buttonSetAlarm;
    private static final int ALARTM_CODE = 101;
    TextView textSms;
    
    private Calendar alarmCalendar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTime = findViewById(R.id.edit_text_time);
        buttonSetAlarm = findViewById(R.id.button_set_alarm);
        textSms = findViewById(R.id.text_sms);

        // Initialize calendar to current time
        alarmCalendar = Calendar.getInstance();

        // Prevent manual editing so the user uses the TimePicker
        editTextTime.setFocusable(false);
        editTextTime.setClickable(true);

        AlarmManager alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);

        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setting up a TimePickerDialog

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, (TimePicker view, int hourOfDay, int minuteOfHour) -> {
                    // Update the calendar with selected time
                    alarmCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    alarmCalendar.set(Calendar.MINUTE, minuteOfHour);
                    alarmCalendar.set(Calendar.SECOND, 0);
                    alarmCalendar.set(Calendar.MILLISECOND, 0);
                    
                    // Format time as HH:mm
                    String timeText = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minuteOfHour);
                    editTextTime.setText(timeText);
                }, hour, minute, false);

                timePickerDialog.show();
            }
        });

        buttonSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the selected time is in the past, set it for the next day
                if (alarmCalendar.before(Calendar.getInstance())) {
                    alarmCalendar.add(Calendar.DATE, 1);
                }

                long triggerTime = alarmCalendar.getTimeInMillis();

                // Main intent to send broadcast
                Intent intent = new Intent(MainActivity.this, MyBroadReceiver.class);

                // Pending intent to send broadcast
                // FLAG_IMMUTABLE is required for Android 12+ (API 31+)
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, ALARTM_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

                alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
            }
        });

        textSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SMSActivity.class);
                startActivity(intent);
            }
        });

    }
}