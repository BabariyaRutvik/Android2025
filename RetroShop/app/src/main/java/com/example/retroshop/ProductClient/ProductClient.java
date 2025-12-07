package com.example.retroshop.ProductClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductClient
{
    // https://api.escuelajs.co/api/v1/products
    private static final String BASE_URL = "https://api.escuelajs.co/api/v1/";
    private static Retrofit retrofit;

    // now making the getClient() method to fetch the data from the api

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
