package com.example.weather.WeatherActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.weather.WeatherAdapter.DailyWeatherAdapter;
import com.example.weather.WeatherPOJO.ForecastDay;
import com.example.weather.WeatherPOJO.WeatherResponse;
import com.example.weather.WeatherResponse.WeatherClient;
import com.example.weather.WeatherResponse.Weather_interface;
import com.example.weather.databinding.ActivitySevenDaysBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SevenDaysActivity extends AppCompatActivity {

    private ActivitySevenDaysBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySevenDaysBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backImage.setOnClickListener(v -> finish());

        fetchForecastData("London");
    }

    private void fetchForecastData(String city) {
        Weather_interface weatherInterface = WeatherClient.getClient().create(Weather_interface.class);
        // Fetching 7 days of forecast
        Call<WeatherResponse> call = weatherInterface.getForecast(city, 7);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateUI(response.body());
                } else {
                    Toast.makeText(SevenDaysActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("SevenDaysActivity", "onFailure: " + t.getMessage());
                Toast.makeText(SevenDaysActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(WeatherResponse weather) {
        List<ForecastDay> forecastDays = weather.getForecast().getForecastday();

        if (forecastDays.size() > 1) {
            // Tomorrow's data (Index 1)
            ForecastDay tomorrow = forecastDays.get(1);
            
            binding.textTomorrowTemp.setText((int) tomorrow.getDay().getMaxtemp_c() + "°");
            binding.textTomorrowTempMaxMin.setText("/" + (int) tomorrow.getDay().getMintemp_c() + "°");
            binding.tvTomorrowCondition.setText(tomorrow.getDay().getCondition().getText());
            
            // Stats for tomorrow
            binding.textPrecipitation.setText(tomorrow.getDay().getDaily_chance_of_rain() + "%");
            binding.textHumidity.setText(tomorrow.getDay().getAvghumidity() + "%");
            binding.textWindSpeed.setText(tomorrow.getDay().getMaxwind_kph() + " km/h");

            Glide.with(this)
                    .load("https:" + tomorrow.getDay().getCondition().getIcon())
                    .into(binding.tomorrowWeatherImage);

            // Rest of the days for RecyclerView (starting from Index 2)
            List<ForecastDay> nextDays = new ArrayList<>();
            for (int i = 2; i < forecastDays.size(); i++) {
                nextDays.add(forecastDays.get(i));
            }

            DailyWeatherAdapter adapter = new DailyWeatherAdapter(this, nextDays);
            binding.rvDaily.setLayoutManager(new LinearLayoutManager(this));
            binding.rvDaily.setAdapter(adapter);
        }
    }
}
