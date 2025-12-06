package com.example.retrofircrud.ProductActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.retrofircrud.Adapter.ProductAdapter;
import com.example.retrofircrud.Model.CartItemModel;
import com.example.retrofircrud.Model.ProductModel;
import com.example.retrofircrud.ProductClient.ProductClient;
import com.example.retrofircrud.Productinterface.Productinterface;
import com.example.retrofircrud.R;
import com.example.retrofircrud.Responce.ProductResponce;
import com.example.retrofircrud.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private List<CartItemModel> cartList;
    private ProductAdapter productAdapter;
    private Productinterface productinterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


         binding.productRecyclerview.setHasFixedSize(true);
         binding.productRecyclerview.setLayoutManager(new LinearLayoutManager(this));

         // now creating a method to fetch the data


        FetchData();


    }
    private void FetchData(){
        // now initializing interface class
        productinterface = ProductClient.getClient().create(Productinterface.class);

        // now fetching the data from the api

        Call<ProductResponce> call = productinterface.getProducts();

        // now making an Async Task
        call.enqueue(new Callback<ProductResponce>() {
            @Override
            public void onResponse(Call<ProductResponce> call, Response<ProductResponce> response) {
              // this method are fetching the data
                if (response.isSuccessful() && response.body() != null){
                    // now making the progressbar functionality
                    binding.progressBar.setVisibility(View.GONE);
                    binding.productRecyclerview.setVisibility(View.VISIBLE);

                    // The API returns a list of 'carts', but we need the 'products' inside them.
                    // We extract all items from all carts into one list.
                    List<ProductModel> carts = response.body().getProducts();
                    cartList = new ArrayList<>();

                    if (carts != null) {
                        for (ProductModel cart : carts) {
                            if (cart.getCartitem() != null) {
                                cartList.addAll(cart.getCartitem());
                            }
                        }
                    }
                    
                    productAdapter = new ProductAdapter(MainActivity.this, cartList);
                    binding.productRecyclerview.setAdapter(productAdapter);

                }
                else {
                    Toast.makeText(MainActivity.this, "Error : No Data Found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResponce> call, Throwable throwable) {

                // if internet not available this method will call
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error : "+throwable.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
