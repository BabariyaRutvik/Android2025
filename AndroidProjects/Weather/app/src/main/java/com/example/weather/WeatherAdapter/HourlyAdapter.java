package com.example.weather.WeatherAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.Locale;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder> {

    private List<Hour> hourlyData;
    private Context context;
    private int selectedPosition = 0; // Default to first item

    public HourlyAdapter(Context context, List<Hour> hourlyData) {
        this.context = context;
        this.hourlyData = hourlyData;
    }

    @NonNull
    @Override
    public HourlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hourly, parent, false);
        return new HourlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyViewHolder holder, int position) {
        Hour hour = hourlyData.get(position);

        // Set Time
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            Date date = inputFormat.parse(hour.getTime());
            holder.tvHour.setText(outputFormat.format(date));
        } catch (ParseException e) {
            holder.tvHour.setText(hour.getTime()); // Fallback
        }

        // Set Temperature
        holder.tvTemp.setText(String.format(Locale.getDefault(), "%.0f°", hour.getTemp()));

        // Set Icon
        Glide.with(context)
                .load("https:" + hour.getCondition().getIcon())
                .into(holder.ivIcon);

        // Change background based on selection
        if (position == selectedPosition) {
            holder.container.setBackgroundResource(R.drawable.main_card_bg);
            holder.tvHour.setTextColor(context.getResources().getColor(R.color.white));
            holder.tvTemp.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.container.setBackgroundResource(R.drawable.light_blue_card_bg);
            holder.tvHour.setTextColor(context.getResources().getColor(R.color.primary_blue));
            holder.tvTemp.setTextColor(context.getResources().getColor(R.color.primary_blue));
        }
    }

    @Override
    public int getItemCount() {
        return hourlyData.size();
    }

    public static class HourlyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        TextView tvHour, tvTemp;
        ImageView ivIcon;

        public HourlyViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            tvHour = itemView.findViewById(R.id.tv_hour);
            tvTemp = itemView.findViewById(R.id.tv_temp);
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
