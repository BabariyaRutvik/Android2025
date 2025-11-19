package com.example.animation;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class Lottie_Activity extends AppCompatActivity {

    LottieAnimationView lview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);


        lview = findViewById(R.id.lottie);

        lview.setAnimation(R.raw.car);
        lview.playAnimation();
        lview.loop(true);
    }
}