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

public class SevenDaysAdapter extends RecyclerView.Adapter<SevenDaysAdapter.ForecastViewHolder> {

    private Context context;
    private List<ForecastDay> forecastDayList;

    public SevenDaysAdapter(Context context, List<ForecastDay> forecastDayList) {
        this.context = context;
        this.forecastDayList = forecastDayList;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        ForecastDay forecastDay = forecastDayList.get(position);

        // Set Day Name
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("EEE", Locale.getDefault());
            Date date = inputFormat.parse(forecastDay.getDate());
            holder.tvDay.setText(outputFormat.format(date));
        } catch (ParseException e) {
            holder.tvDay.setText(forecastDay.getDate());
        }

        // Set Condition Text
        holder.tvCondition.setText(forecastDay.getDay().getCondition().getText());

        // Set Temperature (Max / Min)
        String tempRange = String.format(Locale.getDefault(), "%.0f / %.0f", 
                forecastDay.getDay().getMaxtemp(), 
                forecastDay.getDay().getMintemp());
        holder.tvTemp.setText(tempRange);

        // Set Icon
        Glide.with(context)
                .load("https:" + forecastDay.getDay().getCondition().getIcon())
                .into(holder.ivIcon);
    }

    @Override
    public int getItemCount() {
        return forecastDayList.size();
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay, tvCondition, tvTemp;
        ImageView ivIcon;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDay = itemView.findViewById(R.id.tv_day);
            tvCondition = itemView.findViewById(R.id.tv_forecast_condition);
            tvTemp = itemView.findViewById(R.id.tv_forecast_temp);
            ivIcon = itemView.findViewById(R.id.iv_forecast_icon);
        }
    }
}
