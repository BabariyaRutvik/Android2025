package com.example.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.retrofit.Retrofit.Product;
import com.example.retrofit.Retrofit.ProductAdapter;
import com.example.retrofit.Retrofit.ProductClient;
import com.example.retrofit.Retrofit.ProductResponce;
import com.example.retrofit.Retrofit.Productinterface;
import com.example.retrofit.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Productinterface productinterface;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.productRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.productRecyclerview.setHasFixedSize(true);

        FetchProducts();
    }

    private void FetchProducts() {
        binding.progressBar.setVisibility(View.VISIBLE);
        productinterface = ProductClient.getProductClient().create(Productinterface.class);

        Call<ProductResponce> call = productinterface.getProducts();

        call.enqueue(new Callback<ProductResponce>() {
            @Override
            public void onResponse(Call<ProductResponce> call, Response<ProductResponce> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> productsList = response.body().getProducts();

                    // Corrected: Swapped arguments to match ProductAdapter(List<Product>, Context)
                    productAdapter = new ProductAdapter(productsList, MainActivity.this);
                    binding.productRecyclerview.setAdapter(productAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Response Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResponce> call, Throwable throwable) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}