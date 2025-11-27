package com.example.accelemetersensor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProximitySensor extends AppCompatActivity implements SensorEventListener {

    TextView textProximitySensor;
    TextView textAmbient;
    SensorManager sensorManager;
    Sensor proximitySensor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);

        textProximitySensor = findViewById(R.id.text_proximity_sensor);
        textAmbient = findViewById(R.id.text_ambient);

        textAmbient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProximitySensor.this, AmbientLight.class);
                startActivity(intent);
                finish();
            }
        });

        // Getting a Sensor Manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager != null) {
            proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if (proximitySensor == null) {
                Toast.makeText(this, "Proximity sensor not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager != null && proximitySensor != null) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            textProximitySensor.setText("Value :" + event.values[0]);

            if (event.values[0] > 0) {
                Toast.makeText(this, "Object is Far", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Object is Near", Toast.LENGTH_SHORT).show();
            }
        }
    }
}