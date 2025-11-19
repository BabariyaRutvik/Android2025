package com.example.autohub.CarAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autohub.CarModel.BMWModel;
import com.example.autohub.R;

import java.util.ArrayList;

public class BMWAdapter extends RecyclerView.Adapter<BMWAdapter.MyBMW> {

    private Context context;
    private ArrayList<BMWModel> bmw_car_list;
    private ArrayList<BMWModel> selectedBMW = new ArrayList<>();
    private boolean selectionMode = false;

    // Constructor
    public BMWAdapter(Context context, ArrayList<BMWModel> bmw_car_list) {
        this.context = context;
        this.bmw_car_list = bmw_car_list;
    }

    @NonNull
    @Override
    public MyBMW onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bmw_design, parent, false);
        return new MyBMW(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBMW holder, int position) {
        BMWModel bmwModel = bmw_car_list.get(position);

        // set data to views
        holder.imagebmw.setImageResource(bmwModel.getImage());
        holder.text_bmw_name.setText(bmwModel.getName());
        holder.text_bmw_price.setText(bmwModel.getPrice());
        holder.text_bmw_short_desc.setText(bmwModel.getShortDescription());
        holder.text_launch_year.setText(bmwModel.getLaunch_year());

        // update color based on selection state
        if (selectedBMW.contains(bmwModel)) {
            holder.itemView.setBackgroundColor(Color.LTGRAY); // selected color
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE); // default color
        }

        holder.itemView.setOnLongClickListener(v -> {
            if (!selectionMode) {
                setSelectionMode(true);
                toggleSelection(bmwModel);
                notifyItemChanged(position);
                return true;
            }
            return false;
        });


        // single click handling
        holder.itemView.setOnClickListener(v -> {
            if (selectionMode) {
                toggleSelection(bmwModel);
                notifyItemChanged(position);
            } else {
                // normal click behavior when not in selection mode
                Toast.makeText(context, "Clicked: " + bmwModel.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bmw_car_list.size();
    }

    // Toggle item selection
    private void toggleSelection(BMWModel bmwModel) {
        if (selectedBMW.contains(bmwModel)) {
            selectedBMW.remove(bmwModel);
        } else {
            selectedBMW.add(bmwModel);
        }
    }

    // Enable or disable selection mode
    public void setSelectionMode(boolean enable) {
        selectionMode = enable;
        if (!enable) {
            selectedBMW.clear();
        }
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).supportInvalidateOptionsMenu();
        }
        notifyDataSetChanged();
    }

    // Getter for selected items
    public ArrayList<BMWModel> getSelectBMW() {
        return selectedBMW;
    }

    // Check if selection mode is active
    public boolean isSelectionMod() {
        return selectionMode;
    }

    // ViewHolder class
    public static class MyBMW extends RecyclerView.ViewHolder {
        ImageView imagebmw;
        TextView text_bmw_name, text_bmw_price, text_launch_year, text_bmw_short_desc;

        public MyBMW(@NonNull View itemView) {
            super(itemView);
            imagebmw = itemView.findViewById(R.id.bmw_image);
            text_bmw_name = itemView.findViewById(R.id.text_bmw_name);
            text_bmw_price = itemView.findViewById(R.id.text_bmw_price);
            text_launch_year = itemView.findViewById(R.id.text_bmw_launch_year);
            text_bmw_short_desc = itemView.findViewById(R.id.text_short_bmw_description);
        }
    }
}