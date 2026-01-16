package com.example.fakestore.Interface;

import com.example.fakestore.POJO.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductInterface
{
    @GET("products")
    Call<List<Product>> getProducts();

}
