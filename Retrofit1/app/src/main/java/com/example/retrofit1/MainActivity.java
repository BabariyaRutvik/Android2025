package com.example.retrofit1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.retrofit1.Retrofit.LaptopAdapter;
import com.example.retrofit1.Retrofit.LaptopClient;
import com.example.retrofit1.Retrofit.LaptopResponse;
import com.example.retrofit1.Retrofit.Laptop_interface;
import com.example.retrofit1.Retrofit.ProductModel;
import com.example.retrofit1.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private Laptop_interface laptopInterface;
    private List<ProductModel> leptop_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.leptopRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.leptopRecyclerview.setHasFixedSize(true);

        fetchLaptops();
    }

    private void fetchLaptops() {

        laptopInterface = LaptopClient.getClient().create(Laptop_interface.class);

        Call<LaptopResponse> call = laptopInterface.getLaptop();

        call.enqueue(new Callback<LaptopResponse>() {
            @Override
            public void onResponse(Call<LaptopResponse> call, Response<LaptopResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    leptop_list = response.body().getProducts();

                    LaptopAdapter adapter = new LaptopAdapter(MainActivity.this, leptop_list);
                    binding.leptopRecyclerview.setAdapter(adapter);

                } else {
                    Toast.makeText(MainActivity.this, "Response Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LaptopResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Please Check Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
