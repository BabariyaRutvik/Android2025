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
import com.example.carxpert.CarActivity.CarModel.BMWModel;
import com.example.carxpert.CarActivity.CarModel.MahindraModel;
import com.example.carxpert.CarActivity.Detailsactivity;
import com.example.carxpert.R;

import java.util.ArrayList;

public class MahindraAdapter extends RecyclerView.Adapter<MyMahindra>  {

    private Context context;
    private ArrayList<MahindraModel> mahindra_list;
    private ArrayList<MahindraModel> mahindra_back = new ArrayList<>();

    // constructor
    public MahindraAdapter(Context context, ArrayList<MahindraModel>mahindra_list){
        this.context = context;
        this.mahindra_list = mahindra_list;
        this.mahindra_back = new ArrayList<>(mahindra_list);

    }

    @NonNull
    @Override
    public MyMahindra onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mahindra_design,parent,false);
        MyMahindra mahindra = new MyMahindra(view);
        return mahindra;
    }

    @Override
    public void onBindViewHolder(@NonNull MyMahindra holder, int position) {
        MahindraModel mahindraModel = mahindra_list.get(position);

        holder.image_mahindra.setImageResource(mahindraModel.getImage());
        holder.text_mahindra_name.setText(mahindraModel.getName());
        holder.text_mahibdra_price.setText(mahindraModel.getPrice());
        holder.text_mahindra_LaunchYear.setText(mahindraModel.getLaunch_Year());

         holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.car_animation));



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detailsactivity.class);
                intent.putExtra("image",mahindraModel.getImage());
                intent.putExtra("name",mahindraModel.getName());
                intent.putExtra("price",mahindraModel.getPrice());
                intent.putExtra("launch year",mahindraModel.getLaunch_Year());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mahindra_list.size();
    }
    public void FilterMahindra(String text){
        mahindra_list.clear();

        if (text.isEmpty()) {
            mahindra_list.addAll(mahindra_back); // show all if input empty
        } else {
            for (MahindraModel model : mahindra_back) {
                if (model.getName().toLowerCase().contains(text.toLowerCase())) {
                    mahindra_list.add(model);
                }
            }
        }
        notifyDataSetChanged();

    }
}
class MyMahindra extends RecyclerView.ViewHolder{
   ImageView image_mahindra;
   TextView text_mahindra_name,text_mahibdra_price,text_mahindra_LaunchYear;

    public MyMahindra(@NonNull View itemView) {
        super(itemView);

        image_mahindra = itemView.findViewById(R.id.image_mahindra);
        text_mahindra_name = itemView.findViewById(R.id.text_mahindra_name);
        text_mahibdra_price = itemView.findViewById(R.id.text_mahindra_price);
        text_mahindra_LaunchYear = itemView.findViewById(R.id.text_mahindra_launch_year);


    }
}
