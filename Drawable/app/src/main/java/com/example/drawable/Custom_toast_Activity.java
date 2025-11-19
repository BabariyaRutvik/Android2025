package com.example.drawable;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Custom_toast_Activity extends AppCompatActivity {

    Button btnclick;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_toast);


        btnclick = findViewById(R.id.btnclick);


//        btnclick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"This is Toast",Toast.LENGTH_LONG).show();
//
//            }
//        });

        /*
         Creating custom toast

         */
        // making toast object
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= new Toast(getApplicationContext());

                View view =getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_layout));

                toast.setView(view);


                TextView textMsg = findViewById(R.id.text_custom_toast);

                textMsg.setText("Message Sent Successfully!");

                toast.setDuration(Toast.LENGTH_LONG);

                toast.setGravity(Gravity.TOP | Gravity.END ,0 ,0);


                toast.show();


            }
        });
    }
}