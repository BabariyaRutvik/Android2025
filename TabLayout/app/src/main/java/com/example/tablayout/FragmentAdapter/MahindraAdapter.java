package com.example.tablayout.FragmentAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayout.Model.AudiModel;
import com.example.tablayout.Model.MahindraModel;
import com.example.tablayout.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MahindraAdapter extends RecyclerView.Adapter<MyMahindra>
{
    private Context context;
    private ArrayList<MahindraModel> mahindraList;

    public MahindraAdapter(Context context, ArrayList<MahindraModel> mahindraList) {

        this.context = context;
        this.mahindraList = mahindraList;
    }

    @NonNull
    @Override
    public MyMahindra onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mahindra_design,parent,false);
        MyMahindra myMahindra = new MyMahindra(view);
        return myMahindra;
    }

    @Override
    public void onBindViewHolder(@NonNull MyMahindra holder, int position) {
        MahindraModel mahindraModel = mahindraList.get(position);

        holder.imageMahindra.setImageResource(mahindraModel.getImage());
        holder.text_mahindra_name.setText(mahindraModel.getName());
        holder.text_mahindra_price.setText(mahindraModel.getPrice());
    }

    @Override
    public int getItemCount() {
        return mahindraList.size();
    }
}
class MyMahindra extends RecyclerView.ViewHolder{

    CircleImageView imageMahindra;
    TextView text_mahindra_name,text_mahindra_price;

    public MyMahindra(@NonNull View itemView) {
        super(itemView);

        imageMahindra = itemView.findViewById(R.id.image_mahindra);
        text_mahindra_name = itemView.findViewById(R.id.text_mahindra_name);
        text_mahindra_price = itemView.findViewById(R.id.text_mahindra_price);
    }
}
