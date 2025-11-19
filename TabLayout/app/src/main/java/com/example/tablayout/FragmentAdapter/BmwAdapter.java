package com.example.tablayout.FragmentAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayout.Model.BmwModel;
import com.example.tablayout.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BmwAdapter extends RecyclerView.Adapter<MyBmw>
{
    private Context context;
    private ArrayList<BmwModel> bmwList;

    // creating Adapter constructor to access the variables

    public BmwAdapter(ArrayList<BmwModel> bmwList, Context context) {
        this.bmwList = bmwList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyBmw onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bmw_design,parent,false);
        MyBmw bmw = new MyBmw(view);
        return bmw;
    }

    @Override
    public void onBindViewHolder(@NonNull MyBmw holder, int position) {
        BmwModel bmwModel = bmwList.get(position);

        holder.imagebmw.setImageResource(bmwModel.getImage());
        holder.text_bmw_name.setText(bmwModel.getName());
        holder.text_bmw_price.setText(bmwModel.getPrice());


    }

    @Override
    public int getItemCount() {
        return bmwList.size();
    }
}
class MyBmw extends RecyclerView.ViewHolder{

    CircleImageView imagebmw;
    TextView text_bmw_name,text_bmw_price;


    public MyBmw(@NonNull View itemView) {
        super(itemView);


        imagebmw = itemView.findViewById(R.id.image_bmw);
        text_bmw_name = itemView.findViewById(R.id.text_bmw_name);
        text_bmw_price = itemView.findViewById(R.id.text_bmwprice);

    }
}
