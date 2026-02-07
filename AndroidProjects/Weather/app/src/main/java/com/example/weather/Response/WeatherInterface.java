package com.example.weather.Response;

import com.example.weather.WeatherPOJO.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface WeatherInterface {
    @GET("api/weather/forecast")
    Call<WeatherResponse> getForecast(
            @Header("x-rapidapi-key") String apiKey,
            @Header("x-rapidapi-host") String apiHost,
            @Header("Accept") String accept,
            @Query("q") String city,
            @Query("days") int days
    );
}
