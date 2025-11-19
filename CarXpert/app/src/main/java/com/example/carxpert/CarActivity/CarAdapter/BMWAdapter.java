package com.example.carxpert.CarActivity.CarAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carxpert.CarActivity.CarModel.BMWModel;
import com.example.carxpert.CarActivity.Detailsactivity;
import com.example.carxpert.R;

import java.util.ArrayList;

public class BMWAdapter extends RecyclerView.Adapter<MyBMW> {

    private Context context;
    private ArrayList<BMWModel> bmw_list;       // current filtered list
    private ArrayList<BMWModel> backupBMW;      // original full list

    public BMWAdapter(ArrayList<BMWModel> bmw_list, Context context) {
        this.context = context;
        this.bmw_list = bmw_list;
        this.backupBMW = new ArrayList<>(bmw_list);
    }

    @NonNull
    @Override
    public MyBMW onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bmw_design, parent, false);
        return new MyBMW(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBMW holder, int position) {
        BMWModel bmw = bmw_list.get(position);

        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.car_animation));


        holder.bmwImage.setImageResource(bmw.getImage());
        holder.text_bmw_name.setText(bmw.getName());
        holder.text_bmw_price.setText(bmw.getPrice());
        holder.text_bmw_launch_year.setText(bmw.getLaunch_year());

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, Detailsactivity.class);
            i.putExtra("image", bmw.getImage());
            i.putExtra("name", bmw.getName());
            i.putExtra("price", bmw.getPrice());
            i.putExtra("launch year", bmw.getLaunch_year());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return bmw_list.size();
    }


    public void filterBMW(String text) {
        bmw_list.clear();

        if (text.isEmpty()) {
            bmw_list.addAll(backupBMW); // show all if input empty
        } else {
            for (BMWModel model : backupBMW) {
                if (model.getName().toLowerCase().contains(text.toLowerCase())) {
                    bmw_list.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}

class MyBMW extends RecyclerView.ViewHolder {

    ImageView bmwImage;
    TextView text_bmw_name, text_bmw_price, text_bmw_launch_year;

    public MyBMW(@NonNull View itemView) {
        super(itemView);

        bmwImage = itemView.findViewById(R.id.image_bmw);
        text_bmw_name = itemView.findViewById(R.id.text_dmw_name);
        text_bmw_price = itemView.findViewById(R.id.text_bmw_price);
        text_bmw_launch_year = itemView.findViewById(R.id.text_bmw_launch_year);
    }
}
