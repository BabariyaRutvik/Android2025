package com.example.weather.WeatherResponse;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient
{
    private static final String BASE_URL = "https://weatherapi-com.p.rapidapi.com/";
    private static Retrofit retrofit;

    private  static  final String API_KEY = "1caea1f23fmsh7b6dcc08a73f460p1be99bjsnbe0dc6d81ffa";
    private  static  final String API_HOST = "weatherapi-com.p.rapidapi.com";



    public static Retrofit getClient(){
         if ( retrofit == null){
             OkHttpClient client =  new OkHttpClient.Builder()
                     .addInterceptor(new Interceptor() {
                         @NonNull
                         @Override
                         public Response intercept(@NonNull Chain chain) throws IOException {
                             Request  newrequest = chain.request().newBuilder()
                                     .addHeader("X-RapidAPI-Key",API_KEY)
                                     .addHeader("X-RapidAPI-Host",API_HOST)
                                     .build();

                             return chain.proceed(newrequest);
                         }
                     })
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
