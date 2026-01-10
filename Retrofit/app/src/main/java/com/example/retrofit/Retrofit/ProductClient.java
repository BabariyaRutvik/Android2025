package com.example.retrofit.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class ProductClient {

    public static final String BASE_URL = "https://dummyjson.com/";
    private static  Retrofit retrofit;

    public static Retrofit getProductClient(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
