package com.example.weather.WeatherAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weather.R;
import com.example.weather.WeatherPOJO.WeatherResponse;

import java.util.List;
import java.util.Locale;

public class AddCityAdapter extends RecyclerView.Adapter<AddCityAdapter.CityViewHolder> {

    private List<WeatherResponse> cityList;
    private Context context;

    public AddCityAdapter(Context context, List<WeatherResponse> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        WeatherResponse weather = cityList.get(position);

        // City Name
        holder.tvCityName.setText(weather.getLocation().getName());

        // Condition
        holder.tvWeatherDesc.setText(weather.getCurrent().getCondition().getText());

        // Temperature
        holder.tvCityTemp.setText(String.format(Locale.getDefault(), "%.0f°", weather.getCurrent().getTemperature()));

        // Weather Icon
        Glide.with(context)
                .load("https:" + weather.getCurrent().getCondition().getIcon())
                .into(holder.ivWeatherIcon);
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {
        ImageView ivWeatherIcon;
        TextView tvCityName, tvWeatherDesc, tvCityTemp;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            ivWeatherIcon = itemView.findViewById(R.id.iv_weather_icon);
            tvCityName = itemView.findViewById(R.id.tv_city_name);
            tvWeatherDesc = itemView.findViewById(R.id.tv_weather_desc);
            tvCityTemp = itemView.findViewById(R.id.tv_city_temp);
        }
    }
}
