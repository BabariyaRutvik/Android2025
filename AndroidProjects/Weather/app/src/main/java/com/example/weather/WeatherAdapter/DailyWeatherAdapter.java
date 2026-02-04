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
import com.example.weather.WeatherPOJO.ForecastDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherViewHolder> {

    private Context context;
    private List<ForecastDay> forecastDayList;

    public DailyWeatherAdapter(Context context, List<ForecastDay> forecastDayList) {
        this.context = context;
        this.forecastDayList = forecastDayList;
    }

    @NonNull
    @Override
    public DailyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daily_forecast, parent, false);
        return new DailyWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyWeatherViewHolder holder, int position) {
        ForecastDay forecastDay = forecastDayList.get(position);

        // Format date from "2023-01-01" to "Mon"
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE", Locale.ENGLISH);
        try {
            Date date = inputFormat.parse(forecastDay.getDate());
            holder.tvDay.setText(outputFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            holder.tvDay.setText(forecastDay.getDate());
        }

        holder.tvCondition.setText(forecastDay.getDay().getCondition().getText());
        
        String maxTemp = (int) forecastDay.getDay().getMaxtemp_c() + "";
        String minTemp = (int) forecastDay.getDay().getMintemp_c() + "";
        holder.tvTempRange.setText(maxTemp + " / " + minTemp);

        // Load weather icon using Glide
        String iconUrl = "https:" + forecastDay.getDay().getCondition().getIcon();
        Glide.with(context)
                .load(iconUrl)
                .into(holder.ivWeatherIcon);
    }

    @Override
    public int getItemCount() {
        return forecastDayList.size();
    }

    public static class DailyWeatherViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay, tvCondition, tvTempRange;
        ImageView ivWeatherIcon;

        public DailyWeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tv_day);
            tvCondition = itemView.findViewById(R.id.tv_daily_condition);
            tvTempRange = itemView.findViewById(R.id.tv_daily_temp);
            ivWeatherIcon = itemView.findViewById(R.id.iv_daily_weather);
        }
    }
}
