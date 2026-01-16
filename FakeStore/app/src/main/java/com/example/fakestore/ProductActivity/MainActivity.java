package com.example.fakestore.ProductActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fakestore.Adapter.ProductAdapter;
import com.example.fakestore.Client.ProductClient;
import com.example.fakestore.Interface.ProductInterface;
import com.example.fakestore.POJO.Product;
import com.example.fakestore.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ProductInterface productInterface;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.productRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.productRecyclerview.setHasFixedSize(true);

        FetchData();
    }

    private void FetchData() {
        binding.progressbar.setVisibility(View.VISIBLE);
        productInterface = ProductClient.getClient().create(ProductInterface.class);

        Call<List<Product>> call = productInterface.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                binding.progressbar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    // Use the list directly from the response body
                    List<Product> products = response.body();
                    adapter = new ProductAdapter(products, MainActivity.this);
                    binding.productRecyclerview.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Response Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable throwable) {
                binding.progressbar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}