package com.example.retrofit1.Retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LaptopAdapter extends RecyclerView.Adapter<Laptop>  {

    private Context context;
    private List<ProductModel> leptop_list;


    // now creating constructor to fetch the data
    public LaptopAdapter(Context context, List<ProductModel> leptop_list){
        this.context = context;
        this.leptop_list = leptop_list;
    }


    @NonNull
    @Override
    public Laptop onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.leptop_design,parent,false);
        Laptop laptop = new Laptop(view);
        return laptop;
    }

    @Override
    public void onBindViewHolder(@NonNull Laptop holder, int position) {
        ProductModel productModel = leptop_list.get(position);

        // now fetch the Image from the server using Picasso
        Picasso.get().load(productModel.getThumbnail()).into(holder.image_laptop_thumbnail);

        // now fetching the data from the model or  POJO class
        holder.text_title.setText(productModel.getTitle());
        holder.text_description.setText(productModel.getDescription());
        holder.text_price.setText("₹ "+productModel.getPrice());
        holder.text_brand.setText(productModel.getBrand());
        holder.text_category.setText(productModel.getCategory());

    }

    @Override
    public int getItemCount() {
        return leptop_list.size();
    }
}
class Laptop extends RecyclerView.ViewHolder{
    CircleImageView image_laptop_thumbnail;
   TextView text_title,text_description,text_price,text_brand,text_category;

    public Laptop(@NonNull View itemView) {
        super(itemView);


        image_laptop_thumbnail = itemView.findViewById(R.id.image_laptop_thumbnail);
        text_title = itemView.findViewById(R.id.text_title);
        text_description = itemView.findViewById(R.id.text_description);
        text_price = itemView.findViewById(R.id.text_price);
        text_brand = itemView.findViewById(R.id.text_brand);
        text_category = itemView.findViewById(R.id.text_category);
    }
}
