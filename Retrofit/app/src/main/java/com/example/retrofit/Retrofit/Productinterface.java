package com.example.retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Productinterface {

    @GET("products")
    Call<ProductResponce> getProducts();
}
