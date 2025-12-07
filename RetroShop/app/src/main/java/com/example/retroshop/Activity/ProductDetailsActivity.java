package com.example.retroshop.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.retroshop.R;
import com.example.retroshop.databinding.ActivityProductDetailsBinding;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {

    ActivityProductDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize ViewBinding
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        // now getting data from the adapter class when user will clicxk on recyxlwrview item
        String title = getIntent().getStringExtra("title");
        double price = getIntent().getDoubleExtra("price",0.0);
        String category = getIntent().getStringExtra("category");
        String description = getIntent().getStringExtra("description");
        String image = getIntent().getStringExtra("image");

        binding.titleProduct.setText(title);
        binding.priceProduct.setText("₹ " + price);
        binding.textDescription.setText(description);
        binding.categoryProduct.setText(category);


        // loading image
        Picasso.get().load(image).into(binding.imageProduct);





    }
}