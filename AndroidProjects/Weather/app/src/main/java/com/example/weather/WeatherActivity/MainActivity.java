package com.example.weather.WeatherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weather.R;
import com.example.weather.Response.WeatherClient;
import com.example.weather.WeatherAdapter.AddCityAdapter;
import com.example.weather.WeatherAdapter.HourlyAdapter;
import com.example.weather.WeatherPOJO.Hour;
import com.example.weather.WeatherPOJO.WeatherResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textLocation, textDate, textTemp, textCondition, tv7Days;
    private ImageView imageSun;
    private RecyclerView rvHourly, rvOtherCities;
    private ImageView ivAddCity;

    private static final String API_KEY = "e915f3b179mshdd2e78bc693d86dp15b3bfjsn7af9062f9355";
    private static final String API_HOST = "weather-api167.p.rapidapi.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupRecyclerViews();

        // Fetch weather for a default city
        fetchWeatherData("London");

        tv7Days.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SevenDaysActivity.class);
            startActivity(intent);
        });

        ivAddCity.setOnClickListener(v -> {
            Toast.makeText(this, "Add City Clicked", Toast.LENGTH_SHORT).show();
        });
    }

    private void initViews() {
        textLocation = findViewById(R.id.text_location);
        textDate = findViewById(R.id.text_date);
        textTemp = findViewById(R.id.text_temp);
        textCondition = findViewById(R.id.text_condition);
        imageSun = findViewById(R.id.image_sun);
        rvHourly = findViewById(R.id.rv_hourly);
        rvOtherCities = findViewById(R.id.rv_other_cities);
        tv7Days = findViewById(R.id.tv_7days);
        ivAddCity = findViewById(R.id.iv_add_city);
    }

    private void setupRecyclerViews() {
        rvHourly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvOtherCities.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void fetchWeatherData(String city) {
        WeatherClient.getRetrofit().getForecast(API_KEY, API_HOST, "application/json", city, 7).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateUI(response.body());
                } else {
                    Log.e("MainActivity", "Error Code: " + response.code());
                    Toast.makeText(MainActivity.this, "API Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Log.e("MainActivity", "Failure: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(WeatherResponse weather) {
        if (weather == null || weather.getLocation() == null || weather.getCurrent() == null) return;

        textLocation.setText(weather.getLocation().getName());

        String currentDate = new SimpleDateFormat("EEEE d, MMMM HH:mm", Locale.getDefault()).format(new Date());
        textDate.setText(currentDate);

        textTemp.setText(String.format(Locale.getDefault(), "%.0f°", weather.getCurrent().getTemperature()));
        if (weather.getCurrent().getCondition() != null) {
            textCondition.setText(weather.getCurrent().getCondition().getText());

            Glide.with(this)
                    .load("https:" + weather.getCurrent().getCondition().getIcon())
                    .into(imageSun);
        }

        if (weather.getForecast() != null && weather.getForecast().getForecastday() != null && !weather.getForecast().getForecastday().isEmpty()) {
            List<Hour> hourList = weather.getForecast().getForecastday().get(0).getHour();
            if (hourList != null) {
                HourlyAdapter hourlyAdapter = new HourlyAdapter(this, hourList);
                rvHourly.setAdapter(hourlyAdapter);
            }
        }

        List<WeatherResponse> otherCities = new ArrayList<>();
        otherCities.add(weather);
        AddCityAdapter addCityAdapter = new AddCityAdapter(this, otherCities);
        rvOtherCities.setAdapter(addCityAdapter);
    }
}
