package com.example.tablayout.FragmentAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayout.Model.AudiModel;
import com.example.tablayout.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AudiAdapter extends RecyclerView.Adapter<MyAudi>
{
     private Context context;
     private ArrayList<AudiModel> audiList;

    public AudiAdapter(ArrayList<AudiModel> audiList, Context context) {
        this.audiList = audiList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAudi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.audi_design,parent,false);
       MyAudi myAudi = new MyAudi(view);
        return myAudi;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAudi holder, int position) {
        AudiModel audiModel = audiList.get(position);

        holder.imageAudi.setImageResource(audiModel.getImage());
        holder.text_audi_price.setText(audiModel.getName());
        holder.text_audi_price.setText(audiModel.getPrice());


    }

    @Override
    public int getItemCount() {
        return audiList.size();
    }
}
class MyAudi extends RecyclerView.ViewHolder{
    CircleImageView imageAudi;
    TextView text_audi_name,text_audi_price;
    public MyAudi(@NonNull View itemView) {
        super(itemView);

        imageAudi = itemView.findViewById(R.id.image_audi);
        text_audi_name = itemView.findViewById(R.id.text_audi_name);
        text_audi_price = itemView.findViewById(R.id.text_audiprice);


    }
}
