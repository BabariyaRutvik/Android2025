package com.example.retrofircrud.Productinterface;

import com.example.retrofircrud.Responce.ProductResponce;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Productinterface {

    @GET("carts")
    Call<ProductResponce> getProducts();





}
