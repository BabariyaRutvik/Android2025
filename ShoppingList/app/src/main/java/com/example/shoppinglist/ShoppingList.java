package com.example.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

public class ShoppingList extends AppCompatActivity {

    MaterialCardView samsung_cardview;
    MaterialCardView apple_cardview;

    MaterialCardView mahindra_cardview;

    MaterialCardView apple_watch_cardview;
    MaterialCardView apple_tab_cardview;
    MaterialCardView macbook;
    MaterialCardView audi_q7_cardview;
    MaterialCardView bmw_x7_cardview;
    MaterialCardView bmw_xm_cardview;
    MaterialCardView bmw_x1_cardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);


        samsung_cardview = findViewById(R.id.samsung_cardview);
        apple_cardview = findViewById(R.id.apple_cardview);
        apple_watch_cardview = findViewById(R.id.apple_watch_cardview);
        apple_tab_cardview = findViewById(R.id.apple_tab_cardview);
        mahindra_cardview = findViewById(R.id.mahindra_cardview);
        macbook = findViewById(R.id.macbook_cardview);
        audi_q7_cardview = findViewById(R.id.audi_q7_cardview);
        bmw_x7_cardview = findViewById(R.id.bmw_x7_cardview);
        bmw_xm_cardview = findViewById(R.id.bmw_xm_cardview);
        bmw_x1_cardview = findViewById(R.id.bmw_x1_cardview);


        samsung_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProductDetails(R.drawable.s26,"Samsung Galaxy S24 Ultra");
            }
        });

        apple_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProductDetails(R.drawable.iphone_17,"Iphone 17 Pro Max");
            }
        });

        apple_watch_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProductDetails(R.drawable.apple_watch,"Apple Watch 10 Series");
            }
        });
        apple_tab_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProductDetails(R.drawable.apple_tab,"Apple tab 11th gen");
            }
        });
        macbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProductDetails(R.drawable.macbook_,"Macbook 4");
            }
        });

        mahindra_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProductDetails(R.drawable.mahindra_be_6,"Mahindra Be 6e");

            }
        });
        audi_q7_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProductDetails(R.drawable.audi_q7,"Audi Q7");
            }
        });
        bmw_x7_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProductDetails(R.drawable.bmw_x7,"BMW X7");
            }
        });
        bmw_xm_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProductDetails(R.drawable.bmw_xm,"BMW XM");
            }
        });
        bmw_x1_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProductDetails(R.drawable.bmwx1,"BMW X1");
            }
        });







        String name = getIntent().getStringExtra("email");

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Welcome: " + name);
        }
    }
    private void OpenProductDetails(int imageId, String name){
        Intent i = new Intent(getApplicationContext(), ProductDetailsActivity.class);
        i.putExtra("product_image",imageId);
        i.putExtra("product_name",name);
        startActivity(i);
    }
}