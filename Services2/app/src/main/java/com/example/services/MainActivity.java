package com.example.services;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView imagePlayService, imagePauseService;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imagePlayService = findViewById(R.id.imagePlayService);
        imagePauseService = findViewById(R.id.image_pause_service);



        imagePlayService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // starting the service
                startService(new Intent(MainActivity.this, MusicService.class));

            }
        });
        imagePauseService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pause the Service
                stopService(new Intent(MainActivity.this, MusicService.class));

            }
        });
    }
}