package com.example.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

     TextView txtname;
     Button btn_translate;
     Button btn_rotate;
     Button btn_scale;
     Button btn_alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        txtname = findViewById(R.id.txtname);

        btn_translate = findViewById(R.id.btn_transalate);

        btn_rotate = findViewById(R.id.btn_rottate);

        btn_scale = findViewById(R.id.btn_scale);

        btn_alpha = findViewById(R.id.btn_alpha);



        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation translate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
                txtname.startAnimation(translate);
            }
        });

        btn_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation alph = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
                txtname.startAnimation(alph);
            }
        });
        btn_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotation);
                txtname.startAnimation(rotate);
            }
        });
        btn_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation scale = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
                txtname.startAnimation(scale);
            }
        });
    }
}