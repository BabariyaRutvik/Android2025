package com.example.cardekhi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import de.hdodenhof.circleimageview.CircleImageView;

public class CarActivity extends AppCompatActivity {

    CircleImageView imageBMW;
    TextView textBMW;
    CircleImageView imageAUDI;
    TextView textAUDI;
    CircleImageView imageTOYOTA;
    TextView textTOYOTA;
    CircleImageView imageSKODA;
    TextView textSKODA;
    CircleImageView imageMAHINDRA;
    TextView textMAHINDRA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        /* intialization with ids */
        imageBMW = findViewById(R.id.bmw_image);
        textBMW = findViewById(R.id.bmw_text);

        imageAUDI = findViewById(R.id.audi_image);
        textAUDI = findViewById(R.id.audi_text);

        imageTOYOTA = findViewById(R.id.toyota_image);
        textTOYOTA = findViewById(R.id.toyota_text);

        imageSKODA = findViewById(R.id.skoda_image);
        textSKODA = findViewById(R.id.skoda_text);

        imageMAHINDRA = findViewById(R.id.mahindra_image);
        textMAHINDRA = findViewById(R.id.mahindra_text);


        /*Explicit intent*/
        imageBMW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CarActivity.this, BMWActivity.class);
                Toast.makeText(CarActivity.this, "BMW", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        /*
         implicit intent
         */
        textBMW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // BMW India website
                String url = "https://www.bmw.in/en/index.html";

                // Create an intent to open browser
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));

                // Starting the browser
                startActivity(i);
            }
        });


        // Audi Activity
        imageAUDI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarActivity.this, AudiActivity.class);
                Toast.makeText(CarActivity.this, "Audi", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        // Url
        textAUDI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Audi India Website
                String url = "https://www.audi.in/en/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));

                startActivity(i);
            }
        });

        // Toyota

        imageTOYOTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ToyotaActivity.class);
                Toast.makeText(CarActivity.this, "Toyota", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        // url
        textTOYOTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toyota India
                String url ="https://www.toyotabharat.com/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));

                startActivity(i);
            }
        });

        // Mahindra
        imageMAHINDRA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MahindraActivity.class);
                Toast.makeText(CarActivity.this, "Mahindra", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        // url
        textMAHINDRA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="https://www.mahindra.com/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        // Skoda
        imageSKODA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SkodaActivity.class);
                Toast.makeText(CarActivity.this, "Skoda", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }

        });
        // url
        textSKODA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="https://www.skoda-auto.co.in/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}