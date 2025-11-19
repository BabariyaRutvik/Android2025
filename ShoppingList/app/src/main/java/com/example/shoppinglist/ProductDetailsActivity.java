package com.example.shoppinglist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductDetailsActivity extends AppCompatActivity {

    ImageView imageProduct,imageCall,imageShare;
    TextView text_product_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_details);

        imageProduct = findViewById(R.id.image_product);
        imageCall = findViewById(R.id.image_call);
        imageShare = findViewById(R.id.share_image);

        text_product_name = findViewById(R.id.product_name);

        // receiving data from the Shopping list activity
        int imageId = getIntent().getIntExtra("product_image",0);
        String name = getIntent().getStringExtra("product_name");


        // setting up the data
        imageProduct.setImageResource(imageId);
        text_product_name.setText(name);

        // Call button to take a call

        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setting my number to call button
                String phoneNumber = "6351202084";
                Intent callIntent= (new Intent(Intent.ACTION_DIAL));
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(callIntent);
            }
        });
        // Share products
        imageShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareintent = new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                shareintent.putExtra(Intent.EXTRA_SUBJECT,"Check out this product!");
                shareintent.putExtra(Intent. EXTRA_TEXT,name);
                startActivity(Intent.createChooser(shareintent,"Share Via"));
            }
        });



    }
}