package com.example.carepoint.HealthActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carepoint.R;

public class SplashScreenActivityHealth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_health);



        // splash screen for the CarePoint

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivityHealth.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}