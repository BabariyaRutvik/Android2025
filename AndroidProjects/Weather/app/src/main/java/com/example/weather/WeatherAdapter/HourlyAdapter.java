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
import com.example.weather.WeatherPOJO.Hour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {

    private Context context;
    private List<Hour> hourList;

    public HourlyAdapter(Context context, List<Hour> hourList) {
        this.context = context;
        this.hourList = hourList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hourly_forecast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hour hour = hourList.get(position);

        // Format time from "2023-01-01 10:00" to "10:00"
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
        try {
            Date date = inputFormat.parse(hour.getTime());
            holder.tvHour.setText(outputFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            holder.tvHour.setText(hour.getTime());
        }

        holder.tvTemp.setText((int) hour.getTemp_c() + "°");

        // Load weather icon using Glide
        String iconUrl = "https:" + hour.getCondition().getIcon();
        Glide.with(context)
                .load(iconUrl)
                .into(holder.ivIcon);
    }

    @Override
    public int getItemCount() {
        return hourList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHour, tvTemp;
        ImageView ivIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHour = itemView.findViewById(R.id.tv_hour);
            tvTemp = itemView.findViewById(R.id.tv_hour_temp);
            ivIcon = itemView.findViewById(R.id.iv_hour_weather);
        }
    }
}
