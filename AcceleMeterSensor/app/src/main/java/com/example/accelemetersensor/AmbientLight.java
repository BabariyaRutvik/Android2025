package com.example.accelemetersensor;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AmbientLight extends AppCompatActivity implements SensorEventListener {

    TextView textAmbient;
    SensorManager sensorManager;
    Sensor lightSensor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambient_light);

        textAmbient = findViewById(R.id.text_ambient);

        // Getting a Sensor Manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager != null) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if (lightSensor == null) {
                textAmbient.setText("Light Sensor not found!");
                Toast.makeText(this, "Light Sensor not found on this device", Toast.LENGTH_SHORT).show();
            } else {
                // Display sensor name to confirm we found it
                Toast.makeText(this, "Found: " + lightSensor.getName(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Sensor service not available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the listener when the activity is resumed
        // Using SENSOR_DELAY_GAME or FASTEST to ensure updates happen quickly
        if (sensorManager != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener when the activity is paused to save battery
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            // Display the value. If it stays 0.0, the sensor physically detects darkness or is stuck.
            float lightValue = event.values[0];
            textAmbient.setText("Values: " + lightValue);
        }
    }
}