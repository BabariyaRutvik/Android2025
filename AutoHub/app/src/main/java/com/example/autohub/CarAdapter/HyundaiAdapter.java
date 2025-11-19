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

import com.example.autohub.CarModel.HyundaiModel;
import com.example.autohub.R;

import java.util.ArrayList;

public class HyundaiAdapter extends RecyclerView.Adapter<MyHyundai> {
    private Context context;
    private ArrayList<HyundaiModel> hyundai_list;
    private ArrayList<HyundaiModel> selectedHyundai = new ArrayList<>();
    private boolean selectionMode = false;

    // creating HyundaiAdapter constructor
    public HyundaiAdapter(Context context,ArrayList<HyundaiModel> hyundai_list){
        this.context = context;
        this.hyundai_list = hyundai_list;
    }


    @NonNull
    @Override
    public MyHyundai onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hyundai_design,parent,false);
        MyHyundai hyundai = new MyHyundai(view);
        return hyundai;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHyundai holder, int position) {
        HyundaiModel hyundaiModel = hyundai_list.get(position);

        holder.imageHyundai.setImageResource(hyundaiModel.getImage());
        holder.text_hyundai_name.setText(hyundaiModel.getName());
        holder.text_hyundai_price.setText(hyundaiModel.getPrice());
        holder.text_hyundai_sort_desc.setText(hyundaiModel.getShortDescription());
        holder.text_launch_year.setText(hyundaiModel.getLaunchYear());


        if (selectedHyundai.contains(hyundaiModel)){
            holder.itemView.setBackgroundColor(Color.GRAY);
        }
        else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnLongClickListener(v -> {
            if (!selectionMode) {
                setSelectionMode(true);
                toggleSelection(hyundaiModel);
                notifyItemChanged(position);
                return true;
            }
            return false;
        });

        holder.itemView.setOnClickListener(v -> {
            if (selectionMode){
                toggleSelection(hyundaiModel);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hyundai_list.size();
    }
    public void toggleSelection(HyundaiModel hyundaiModel){
        if (selectedHyundai.contains(hyundaiModel)){
            selectedHyundai.remove(hyundaiModel);
        }
        else {
            selectedHyundai.add(hyundaiModel);
        }
    }
    // enabling Selection Mode
    public void setSelectionMode(boolean enable){
        selectionMode = enable;
        if (!enable) selectedHyundai.clear();
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).supportInvalidateOptionsMenu();
        }
        notifyDataSetChanged();
    }
    public ArrayList<HyundaiModel> getSelectedHyundai(){
        return  selectedHyundai;
    }
    public boolean isSelectionMode(){
        return selectionMode;
    }
}
class MyHyundai extends RecyclerView.ViewHolder{
    ImageView imageHyundai;
    TextView text_hyundai_name,text_hyundai_price,text_hyundai_sort_desc,text_launch_year;

    public MyHyundai(@NonNull View itemView) {
        super(itemView);


        imageHyundai = itemView.findViewById(R.id.hyundai_image);
        text_hyundai_name = itemView.findViewById(R.id.text_hyundai_name);
        text_hyundai_price = itemView.findViewById(R.id.text_hyundai_price);
        text_hyundai_sort_desc = itemView.findViewById(R.id.text_short_hyundai_description);
        text_launch_year = itemView.findViewById(R.id.text_hyundai_launch_year);
    }
}
