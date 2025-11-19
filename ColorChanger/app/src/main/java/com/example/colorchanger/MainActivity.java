package com.example.colorchanger;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    RelativeLayout main;
    Button redbutton;
    Button yellowbtn;
    Button Bluebtn;
    Button greenButton;
    Button orangebtn;
    RelativeLayout relativeLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.main);
        redbutton = findViewById(R.id.btnred);
        yellowbtn = findViewById(R.id.btnyellow);
        Bluebtn = findViewById(R.id.btnblue);
        greenButton = findViewById(R.id.btngreen);
        orangebtn = findViewById(R.id.btnorange);
        relativeLayout = findViewById(R.id.relative);


        // Red Button
        redbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //main.setBackgroundResource(R.color.red);
                relativeLayout.setBackgroundResource(R.color.red);
            }
        });

        // Yellow Button
        yellowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //main.setBackgroundResource(R.color.yellow);
                relativeLayout.setBackgroundResource(R.color.yellow);
            }
        });
        // Blue Button
        Bluebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //main.setBackgroundResource(R.color.blue);
                relativeLayout.setBackgroundResource(R.color.blue);
            }
        });
        // Green Button
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //main.setBackgroundResource(R.color.green);
                relativeLayout.setBackgroundResource(R.color.green);
            }
        });
        // Orange Button
        orangebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //main.setBackgroundResource(R.color.Orange);
                relativeLayout.setBackgroundResource(R.color.red);
            }
        });

    }
}