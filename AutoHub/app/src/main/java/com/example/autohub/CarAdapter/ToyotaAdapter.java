package com.example.autohub.CarAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autohub.CarModel.ToyotaModel;
import com.example.autohub.R;

import java.util.ArrayList;

public class ToyotaAdapter extends RecyclerView.Adapter<MyToyota> {

    private Context context;
    private ArrayList<ToyotaModel> toyota_car_list;
    private ArrayList<ToyotaModel> selectedToyota = new ArrayList<>();
    private boolean selectionMode = false;


    // creating a ToyotaAdapter Constructor
    public ToyotaAdapter(Context context,ArrayList<ToyotaModel> toyota_car_list){
        this.context = context;
        this.toyota_car_list = toyota_car_list;
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
        ToyotaModel toyotaModel = toyota_car_list.get(position);

        holder.imageToyota.setImageResource(toyotaModel.getImage());
        holder.text_toyota_name.setText(toyotaModel.getName());
        holder.text_toyota_price.setText(toyotaModel.getPrice());
        holder.text_toyota_sort_des.setText(toyotaModel.getShortDescription());
        holder.text_toyota_launch_year.setText(toyotaModel.getLaunchYear());

        if (selectedToyota.contains(toyotaModel)){
            holder.itemView.setBackgroundColor(Color.GRAY);
        }
        else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnLongClickListener(v -> {
            if (!selectionMode) {
                setSelectionMode(true);
                toggleSelection(toyotaModel);
                notifyItemChanged(position);
                return true;
            }
            return false;
        });

        holder.itemView.setOnClickListener(v -> {
            if (selectionMode){
                toggleSelection(toyotaModel);
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return toyota_car_list.size();
    }
    public void toggleSelection(ToyotaModel toyotaModel){
        if (selectedToyota.contains(toyotaModel)){
            selectedToyota.remove(toyotaModel);

        }
        else {
            selectedToyota.add(toyotaModel);
        }

    }
    // enabling selection Mode
    public void setSelectionMode(boolean enable){
        selectionMode = enable;
        if (!enable) selectedToyota.clear();
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).supportInvalidateOptionsMenu();
        }
        notifyDataSetChanged();
    }
    public ArrayList<ToyotaModel> getSelectedToyota(){
        return selectedToyota;
    }
    public boolean isSelectionMode(){
        return selectionMode;
    }
}
class MyToyota extends RecyclerView.ViewHolder{

    ImageView imageToyota;
    TextView text_toyota_name,text_toyota_price,text_toyota_sort_des,text_toyota_launch_year;
    public MyToyota(@NonNull View itemView) {
        super(itemView);


        imageToyota = itemView.findViewById(R.id.toyota_image);
        text_toyota_name = itemView.findViewById(R.id.text_toyota_name);
        text_toyota_price = itemView.findViewById(R.id.text_toyota_price);
        text_toyota_sort_des = itemView.findViewById(R.id.text_short_toyota_description);
        text_toyota_launch_year = itemView.findViewById(R.id.text_toyota_launch_year);

    }
}
