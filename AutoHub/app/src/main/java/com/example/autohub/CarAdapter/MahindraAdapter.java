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

import com.example.autohub.CarModel.MahindraModel;
import com.example.autohub.R;

import java.util.ArrayList;

public class MahindraAdapter extends RecyclerView.Adapter<MyMahindra>
{
    private Context context;
    private ArrayList<MahindraModel> mahindra_car_list;
    private ArrayList<MahindraModel> selectedMahindra = new ArrayList<>();
    private boolean selectionMode = false;

    // creating a constructor class
    public MahindraAdapter(Context context, ArrayList<MahindraModel> mahindra_car_list){
        this.context = context;
        this.mahindra_car_list = mahindra_car_list;
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
        MahindraModel mahindraModel = mahindra_car_list.get(position);

        holder.imagemahindra.setImageResource(mahindraModel.getImage());
        holder.text_mahindra_name.setText(mahindraModel.getName());
        holder.text_mahindra_price.setText(mahindraModel.getPrice());
        holder.text_mahindra_short_desc.setText(mahindraModel.getShortDescription());
        holder.text_mahindra_launch_year.setText(mahindraModel.getLaunchYear());

        /*
         Changing the item background color to gray when item will clicked
         */
        if (selectedMahindra.contains(mahindraModel)){
            holder.itemView.setBackgroundColor(Color.GRAY);
        }
        else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnLongClickListener(v -> {
            if (!selectionMode) {
                setSelectionMode(true);
                toggleSelection(mahindraModel);
                notifyItemChanged(position);
                return true;
            }
            return false;
        });

        holder.itemView.setOnClickListener(v -> {
            if (selectionMode){
                toggleSelection(mahindraModel);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mahindra_car_list.size();
    }
    public void toggleSelection(MahindraModel model){
        if (selectedMahindra.contains(model)){
            selectedMahindra.remove(model);
        }
        else {
            selectedMahindra.add(model);
        }
    }
    public void setSelectionMode(boolean enable){
        selectionMode = enable;
        if (!enable) selectedMahindra.clear();
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).supportInvalidateOptionsMenu();
        }
        notifyDataSetChanged();
    }
    public ArrayList<MahindraModel> getSelectedMahindra(){
        return selectedMahindra;
    }
    public boolean isSelectionMode(){
        return selectionMode;
    }
}
class MyMahindra extends RecyclerView.ViewHolder{

    ImageView imagemahindra;
    TextView text_mahindra_name,text_mahindra_price,text_mahindra_short_desc,text_mahindra_launch_year;
    public MyMahindra(@NonNull View itemView) {
        super(itemView);

        imagemahindra = itemView.findViewById(R.id.mahindra_image);
        text_mahindra_name = itemView.findViewById(R.id.text_mahindra_name);
        text_mahindra_price = itemView.findViewById(R.id.text_mahindra_price);
        text_mahindra_short_desc = itemView.findViewById(R.id.text_short_mahindra_description);
        text_mahindra_launch_year = itemView.findViewById(R.id.text_mahindra_launch_year);

    }
}
