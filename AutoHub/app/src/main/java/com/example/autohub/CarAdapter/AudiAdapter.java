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

import com.example.autohub.CarModel.AudiModel;
import com.example.autohub.R;

import java.util.ArrayList;

public class AudiAdapter extends RecyclerView.Adapter<MyAudi> {
  private Context context;
  private ArrayList<AudiModel> audi_car_list;
  private ArrayList<AudiModel> selectedAudi = new ArrayList<>();
  private boolean selectionMode = false;

  // creating AudiAdapter Constructor
    public AudiAdapter(Context context,ArrayList<AudiModel>audi_car_list){
        this.audi_car_list = audi_car_list;
        this.context = context;

    }

    @NonNull
    @Override
    public MyAudi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.audi_design,parent,false);
        MyAudi audi = new MyAudi(view);
        return audi;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAudi holder, int position) {
        AudiModel audiModel = audi_car_list.get(position);

        holder.imageAudi.setImageResource(audiModel.getImage());
        holder.text_audi_name.setText(audiModel.getName());
        holder.text_audi_price.setText(" Price : "+audiModel.getPrice());
        holder.text_audi_launch_year.setText(audiModel.getLaunchYear());
        holder.text_audi_short_desc.setText(audiModel.getShortDescription());

        // changing the background color to GRAY  when user select the audi item

        if (selectedAudi .contains(audiModel)){
            holder.itemView.setBackgroundColor(Color.GRAY);
        }
        else {
            // deselecting item to show white color
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnLongClickListener(v -> {
            if (!selectionMode) {
                setSelectionMode(true);
                toggleSelection(audiModel);
                notifyItemChanged(position);
                return true;
            }
            return false;
        });


        // item click listener
        holder.itemView.setOnClickListener(v -> {
            if (selectionMode){
                toggleSelection(audiModel);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return audi_car_list.size();
    }
    public void toggleSelection(AudiModel audiModel){
        if (selectedAudi.contains(audiModel)){
            selectedAudi.remove(audiModel);

            }
        else {
            selectedAudi.add(audiModel);

        }


    }
    // enabling the selection mode
    public void setSelectionMode(boolean enable){
        selectionMode = enable;
        if (!enable)selectedAudi.clear();
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).supportInvalidateOptionsMenu();
        }
        notifyDataSetChanged();
    }
    public ArrayList<AudiModel>getSelectedAudi(){
        return selectedAudi;
    }
    public boolean isSelectionMode(){
        return selectionMode;
    }

}
class MyAudi extends RecyclerView.ViewHolder{

    ImageView imageAudi;
    TextView text_audi_name,text_audi_price,text_audi_short_desc,text_audi_launch_year;
    public MyAudi(@NonNull View itemView) {
        super(itemView);

        imageAudi = itemView.findViewById(R.id.audi_image);
        text_audi_name = itemView.findViewById(R.id.text_audi_name);
        text_audi_price = itemView.findViewById(R.id.text_audi_price);
        text_audi_short_desc = itemView.findViewById(R.id.text_short_audi_description);
        text_audi_launch_year = itemView.findViewById(R.id.text_audi_launch_year);

    }
}
