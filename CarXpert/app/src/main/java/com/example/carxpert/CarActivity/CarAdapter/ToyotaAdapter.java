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
import com.example.carxpert.CarActivity.CarModel.ToyotaModel;
import com.example.carxpert.CarActivity.Detailsactivity;
import com.example.carxpert.R;

import java.util.ArrayList;

public class ToyotaAdapter extends RecyclerView.Adapter<MyToyota>{
    private Context context;
    private ArrayList<ToyotaModel> toyota_list;

    private ArrayList<ToyotaModel> toyota_back = new ArrayList<>();

    // constructor

    public ToyotaAdapter(Context context, ArrayList<ToyotaModel> toyota_list) {
        this.context = context;
        this.toyota_list = toyota_list;
        toyota_back = new ArrayList<>(toyota_list);
    }

    @NonNull
    @Override
    public MyToyota onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.toyota_design,parent,false);
        MyToyota toyota = new MyToyota(view);
        return toyota;
    }

    @Override
    public void onBindViewHolder(@NonNull MyToyota holder, int position) {
        ToyotaModel model = toyota_list.get(position);

        holder.image_toyota.setImageResource(model.getImage());
        holder.text_toyota_name.setText(model.getName());
        holder.text_toyota_price.setText(model.getPrice());
        holder.text_toyota_LaunchYear.setText(model.getLaunch_Year());

        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.car_animation));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detailsactivity.class);
                intent.putExtra("image",model.getImage());
                intent.putExtra("name",model.getName());
                intent.putExtra("price",model.getPrice());
                intent.putExtra("launch year", model.getLaunch_Year());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return toyota_list.size();
    }
    public void FilterToyota(String text){
        toyota_list.clear();

        if (text.isEmpty()) {
            toyota_list.addAll(toyota_back); // show all if input empty
        } else {
            for (ToyotaModel model : toyota_back) {
                if (model.getName().toLowerCase().contains(text.toLowerCase())) {
                    toyota_list.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
class MyToyota extends RecyclerView.ViewHolder{

    ImageView image_toyota;
    TextView text_toyota_name,text_toyota_price,text_toyota_LaunchYear;
    public MyToyota(@NonNull View itemView) {
        super(itemView);

        image_toyota = itemView.findViewById(R.id.image_toyota);
        text_toyota_name = itemView.findViewById(R.id.text_toyota_name);
        text_toyota_price = itemView.findViewById(R.id.text_toyota_price);
        text_toyota_LaunchYear = itemView.findViewById(R.id.text_toyota_launch_year);
    }
}
