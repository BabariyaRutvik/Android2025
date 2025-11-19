package com.example.foodorder;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodorder.databinding.ActivityBillBinding;

public class BillActivity extends AppCompatActivity {

    ActivityBillBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBillBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        setContentView(view);

        // getting data from the homeactivity

        String name = getIntent().getStringExtra("name");
        int image = getIntent().getIntExtra("image",0);
        int price = getIntent().getIntExtra("price",0);

        // setting up the data from the homeactivity
        binding.imageFood.setImageResource(image);
        binding.textFood.setText(name);
        binding.textPrice.setText("Price: ₹" + price);


    }
}