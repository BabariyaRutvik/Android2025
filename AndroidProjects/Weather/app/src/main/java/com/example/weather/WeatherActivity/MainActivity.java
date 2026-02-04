package com.example.weather.WeatherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.weather.WeatherAdapter.HourlyAdapter;
import com.example.weather.WeatherPOJO.WeatherResponse;
import com.example.weather.WeatherResponse.WeatherClient;
import com.example.weather.WeatherResponse.Weather_interface;
import com.example.weather.databinding.ActivityMainBinding;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initial fetch
        fetchWeatherData("London");

        binding.tv7dayForecast.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SevenDaysActivity.class));
        });
    }

    private void fetchWeatherData(String city) {
        Weather_interface weatherInterface = WeatherClient.getClient().create(Weather_interface.class);
        Call<WeatherResponse> call = weatherInterface.getForecast(city, 3);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateUI(response.body());
                } else {
                    String errorMsg = "Error " + response.code();
                    try {
                        if (response.errorBody() != null) {
                            errorMsg += ": " + response.errorBody().string();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("MainActivity", errorMsg);
                    Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(WeatherResponse weather) {
        binding.textLocation.setText(weather.getLocation().getName());
        binding.textTemp.setText((int) weather.getCurrent().getTemp_c() + "°");
        binding.textCondition.setText(weather.getCurrent().getCondition().getText());
        binding.textDate.setText(weather.getLocation().getLocaltime());
        
        binding.tvPrecipVal.setText(weather.getCurrent().getPrecip_mm() + " mm");
        binding.tvHumidityVal.setText(weather.getCurrent().getHumidity() + "%");
        binding.tvWindVal.setText(weather.getCurrent().getWind_kph() + " km/h");

        Glide.with(this)
                .load("https:" + weather.getCurrent().getCondition().getIcon())
                .into(binding.imageWeather);

        // Setup Hourly Forecast RecyclerView
        if (weather.getForecast() != null && weather.getForecast().getForecastday() != null && !weather.getForecast().getForecastday().isEmpty()) {
            HourlyAdapter hourlyAdapter = new HourlyAdapter(this, weather.getForecast().getForecastday().get(0).getHour());
            binding.rvHourly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            binding.rvHourly.setAdapter(hourlyAdapter);
        }
    }
}
