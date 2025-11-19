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

import com.example.carxpert.CarActivity.CarModel.AudiModel;
import com.example.carxpert.CarActivity.Detailsactivity;
import com.example.carxpert.R;

import java.util.ArrayList;

public class AudiAdapter extends RecyclerView.Adapter<MyAudi> {

    private Context context;
    private ArrayList<AudiModel> audi_list;       // current visible list
    private ArrayList<AudiModel> audi_backup;     // original list backup

    // constructor
    public AudiAdapter(ArrayList<AudiModel> audi_list, Context context) {
        this.context = context;
        this.audi_list = audi_list;
        this.audi_backup = new ArrayList<>(audi_list); // ✅ important
    }

    @NonNull
    @Override
    public MyAudi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.audi_design, parent, false);
        return new MyAudi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAudi holder, int position) {
        AudiModel audiModel = audi_list.get(position);

        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.car_animation));

        holder.audi_image.setImageResource(audiModel.getImage());
        holder.text_audi_name.setText(audiModel.getName());
        holder.text_audi_price.setText(audiModel.getPrice());
        holder.text_audi_launchYear.setText(audiModel.getLaunch_Year());

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, Detailsactivity.class);
            i.putExtra("image", audiModel.getImage());
            i.putExtra("name", audiModel.getName());
            i.putExtra("price", audiModel.getPrice());
            i.putExtra("launch year", audiModel.getLaunch_Year());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return audi_list.size();
    }

    // ✅ Search Filter
    public void filterAudi(String text) {
        audi_list.clear();

        if (text.isEmpty()) {
            audi_list.addAll(audi_backup);
        } else {
            for (AudiModel model : audi_backup) {
                if (model.getName().toLowerCase().contains(text.toLowerCase())) {
                    audi_list.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}

class MyAudi extends RecyclerView.ViewHolder {

    ImageView audi_image;
    TextView text_audi_name, text_audi_price, text_audi_launchYear;

    public MyAudi(@NonNull View itemView) {
        super(itemView);

        audi_image = itemView.findViewById(R.id.image_audi);
        text_audi_name = itemView.findViewById(R.id.text_audi_name);
        text_audi_price = itemView.findViewById(R.id.text_audi_price);
        text_audi_launchYear = itemView.findViewById(R.id.text_audi_launch_year);
    }
}
