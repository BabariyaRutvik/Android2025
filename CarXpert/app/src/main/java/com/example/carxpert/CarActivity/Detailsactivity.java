package com.example.carxpert.CarActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carxpert.R;

public class Detailsactivity extends AppCompatActivity {

    ImageView image_car;
    TextView text_car_name,text_car_price,text_car_launch_year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsactivity);


        image_car = findViewById(R.id.image_car);
        text_car_name = findViewById(R.id.text_car_name);
        text_car_price = findViewById(R.id.text_car_price);
        text_car_launch_year = findViewById(R.id.text_car_launch_year);

        Intent i = getIntent();

        image_car.setImageResource(i.getIntExtra("image",0));
        text_car_name.setText(i.getStringExtra("name"));
        text_car_price.setText(i.getStringExtra("price"));
        text_car_launch_year .setText(i.getStringExtra("launch year"));


    }
}