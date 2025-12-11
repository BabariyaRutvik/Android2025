package com.example.retrocrud.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
{
    // http://localhost/Android/
    private static final String BASE_URL = "http://192.168.43.178/Android/";

    private static Retrofit retrofit;


    // now making a method to get retrofit instance
    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;
    }
}
