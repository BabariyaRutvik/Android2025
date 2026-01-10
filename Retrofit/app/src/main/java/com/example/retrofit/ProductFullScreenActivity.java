package com.example.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.retrofit.Retrofit.Product;
import com.squareup.picasso.Picasso;

public class ProductFullScreenActivity extends AppCompatActivity {

    ImageView imageDetails;
    TextView productTitle, productPrice, productCategory, detailRating, productDetailedDescription;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_full_screen);

        // Initialize views
        imageDetails = findViewById(R.id.image_details);
        productTitle = findViewById(R.id.product_title);
        productPrice = findViewById(R.id.product_price);
        productCategory = findViewById(R.id.product_category);
        detailRating = findViewById(R.id.detail_rating);
        productDetailedDescription = findViewById(R.id.product_detailed_description);
        toolbar = findViewById(R.id.toolbar);

        // Setup Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(""); // Title handled by CollapsingToolbar or manually
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Get Product data
        Product product = (Product) getIntent().getSerializableExtra("product_data");

        if (product != null) {
            Picasso.get().load(product.getThumbnail()).into(imageDetails);
            productTitle.setText(product.getTitle());
            productPrice.setText("₹ " + product.getPrice());
            productCategory.setText(product.getCategory().toUpperCase());
            productDetailedDescription.setText(product.getDescription());
            
            // Static rating as it's often a separate field in API
            detailRating.setText("4.8"); 
        }
    }
}