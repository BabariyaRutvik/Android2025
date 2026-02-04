package com.example.producthub.Client;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class ProductClient
{
     private static final String BASE_URL = "http://192.168.43.178:8080/";
     private static Retrofit retrofit;


     public static Retrofit getClient() {

         if (retrofit == null){
             // adding  logs to logcat to see json data

             HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
             interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


             OkHttpClient client = new OkHttpClient.Builder().
                     addInterceptor(interceptor)
                     .build();



             retrofit = new Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .client(client)
                     .build();
         }
         return retrofit;

         }
     }


