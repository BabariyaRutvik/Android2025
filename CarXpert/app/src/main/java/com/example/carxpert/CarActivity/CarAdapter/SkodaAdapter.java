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
import com.example.carxpert.CarActivity.CarModel.SkodaModel;
import com.example.carxpert.CarActivity.Detailsactivity;
import com.example.carxpert.R;

import java.util.ArrayList;

public class SkodaAdapter extends RecyclerView.Adapter<MySkoda> {

    Context context;
    ArrayList<SkodaModel> skoda_List;
    private ArrayList<SkodaModel> skoda_backup = new ArrayList<>();

    public SkodaAdapter(Context context, ArrayList<SkodaModel> skoda_List) {
        this.context = context;
        this.skoda_List = skoda_List;
        this.skoda_backup = new ArrayList<>(skoda_List);

    }

    @NonNull
    @Override
    public MySkoda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.skoda_design, parent, false);
        return new MySkoda(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MySkoda holder, int position) {
        SkodaModel model = skoda_List.get(position);

        holder.image_skoda.setImageResource(model.getImage());
        holder.text_skoda_name.setText(model.getName());
        holder.text_skoda_price.setText(model.getPrice());
        holder.text_skoda_LaunchYear.setText(model.getLaunch_date());

        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.car_animation));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context, Detailsactivity.class);
                i.putExtra("image",model.getImage());
                i.putExtra("name",model.getName());
                i.putExtra("price",model.getPrice());
                i.putExtra("launch year",model.getLaunch_date());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return skoda_List.size();
    }

    public void FilterSkoda(String text) {
        skoda_List.clear();

        if (text.isEmpty()) {
            skoda_List.addAll(skoda_backup); // show all if input empty
        } else {
            for (SkodaModel model : skoda_backup) {
                if (model.getName().toLowerCase().contains(text.toLowerCase())) {
                    skoda_List.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}

class MySkoda extends RecyclerView.ViewHolder {

        ImageView image_skoda;
        TextView text_skoda_name, text_skoda_price, text_skoda_LaunchYear;

        public MySkoda(@NonNull View itemView) {
            super(itemView);

            image_skoda = itemView.findViewById(R.id.image_skoda);
            text_skoda_name = itemView.findViewById(R.id.text_skoda_name);
            text_skoda_price = itemView.findViewById(R.id.text_skoda_price);
            text_skoda_LaunchYear = itemView.findViewById(R.id.text_skoda_launch_year);
        }
    }

