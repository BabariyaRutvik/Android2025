package com.example.retrofit1.Retrofit;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Laptop_interface {

    @GET("products/category/laptops")
    Call<LaptopResponse> getLaptop();

}
