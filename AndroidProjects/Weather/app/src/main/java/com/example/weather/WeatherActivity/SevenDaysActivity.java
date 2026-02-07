package com.example.weather.WeatherActivity;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weather.R;
import com.example.weather.Response.WeatherClient;
import com.example.weather.WeatherAdapter.SevenDaysAdapter;
import com.example.weather.WeatherPOJO.ForecastDay;
import com.example.weather.WeatherPOJO.WeatherResponse;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SevenDaysActivity extends AppCompatActivity {

    private ImageView imageBack, ivTomorrowIcon;
    private TextView tvTomorrowTemp, tvTomorrowCondition, tvTomorrowPrecip, tvTomorrowHumidity, tvTomorrowWind;
    private RecyclerView rvForecast;

    private static final String API_KEY = "e915f3b179mshdd2e78bc693d86dp15b3bfjsn7af9062f9355";
    private static final String API_HOST = "weather-api167.p.rapidapi.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seven_days);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        imageBack.setOnClickListener(v -> finish());

        // Fetch weather for a default city (matching MainActivity)
        fetchSevenDaysForecast("London");
    }

    private void initViews() {
        imageBack = findViewById(R.id.image_back);
        ivTomorrowIcon = findViewById(R.id.iv_tomorrow_icon);
        tvTomorrowTemp = findViewById(R.id.tv_tomorrow_temp);
        tvTomorrowCondition = findViewById(R.id.tv_tomorrow_condition);
        tvTomorrowPrecip = findViewById(R.id.tv_tomorrow_precip);
        tvTomorrowHumidity = findViewById(R.id.tv_tomorrow_humidity);
        tvTomorrowWind = findViewById(R.id.tv_tomorrow_wind);
        rvForecast = findViewById(R.id.rv_forecast);
    }

    private void fetchSevenDaysForecast(String city) {
        WeatherClient.getRetrofit().getForecast(API_KEY, API_HOST, "application/json", city, 7).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateUI(response.body());
                } else {
                    Log.e("SevenDaysActivity", "Error Code: " + response.code());
                    Toast.makeText(SevenDaysActivity.this, "Error fetching forecast: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Log.e("SevenDaysActivity", "Failure: " + t.getMessage());
                Toast.makeText(SevenDaysActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(WeatherResponse weather) {
        if (weather.getForecast() == null || weather.getForecast().getForecastday() == null || weather.getForecast().getForecastday().isEmpty()) return;

        List<ForecastDay> forecastDays = weather.getForecast().getForecastday();

        // Update "Tomorrow" Card (index 1 in the forecast list if available)
        ForecastDay tomorrow;
        if (forecastDays.size() > 1) {
            tomorrow = forecastDays.get(1);
        } else {
            tomorrow = forecastDays.get(0);
        }

        tvTomorrowTemp.setText(String.format(Locale.getDefault(), "%.0f° / %.0f°", 
                tomorrow.getDay().getMaxtemp(), 
                tomorrow.getDay().getMintemp()));
        
        tvTomorrowCondition.setText(tomorrow.getDay().getCondition().getText());
        tvTomorrowPrecip.setText(String.format(Locale.getDefault(), "%.0f%%", tomorrow.getDay().getTotalprecip()));
        tvTomorrowHumidity.setText(String.format(Locale.getDefault(), "%d%%", tomorrow.getDay().getAvghumidity()));
        tvTomorrowWind.setText(String.format(Locale.getDefault(), "%.0f km/h", tomorrow.getDay().getMaxwind()));

        Glide.with(this)
                .load("https:" + tomorrow.getDay().getCondition().getIcon())
                .into(ivTomorrowIcon);

        // Update 7-Day List
        SevenDaysAdapter adapter = new SevenDaysAdapter(this, forecastDays);
        rvForecast.setAdapter(adapter);
    }
}
