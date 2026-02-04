package com.example.weather.WeatherResponse;

import com.example.weather.WeatherPOJO.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Weather_interface {

    @GET("forecast.json")
    Call<WeatherResponse> getForecast(
            @Query("q") String city,
            @Query("days") int days
    );
}
