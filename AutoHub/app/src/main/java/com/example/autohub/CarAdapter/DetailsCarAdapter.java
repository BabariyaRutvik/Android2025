package com.example.autohub.CarAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autohub.CarModel.BMWModel;
import com.example.autohub.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailsCarAdapter extends RecyclerView.Adapter<MyCarDetails>
{
 private Context context;
 private ArrayList<BMWModel> carList;

 // creating Details Adapter constructor
    public DetailsCarAdapter(Context context,ArrayList<BMWModel> carList){
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public MyCarDetails onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(context).inflate(R.layout.details_car_design,parent,false);
        MyCarDetails car = new MyCarDetails(view);
        return car;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCarDetails holder, int position) {
        BMWModel model = carList.get(position);

        holder.imagecar_details.setImageResource(model.getImage());
        holder.text_car_name.setText(model.getName());
        holder.text_car_price.setText(model.getPrice());
        holder.text_car_short_des.setText(model.getShortDescription());
        holder.text_car_launch_year.setText(model.getLaunch_year());


    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}
class MyCarDetails extends RecyclerView.ViewHolder {

    ImageView imagecar_details;
    TextView text_car_name,text_car_price,text_car_short_des,text_car_launch_year;
    public MyCarDetails(@NonNull View itemView) {
        super(itemView);

        imagecar_details = itemView.findViewById(R.id.details_car_image);
        text_car_name = itemView.findViewById(R.id.text_details_car_name);
        text_car_price = itemView.findViewById(R.id.text_details_car_price);
        text_car_short_des =itemView.findViewById(R.id.text_short_details_car_description);
        text_car_launch_year = itemView.findViewById(R.id.text_details_car_launch_year);


    }
}
