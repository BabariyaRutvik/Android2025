package com.example.fakestore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fakestore.POJO.Product;
import com.example.fakestore.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<MyProduct>
{
    private List<Product> productList;
    private Context context;

    // now making tye constructor
    public ProductAdapter(List<Product> productList,Context context){
        this.productList = productList;
        this.context = context;

    }


    @NonNull
    @Override
    public MyProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_design,parent,false);
        MyProduct product = new MyProduct(view);
        return product;
    }

    @Override
    public void onBindViewHolder(@NonNull MyProduct holder, int position) {
        Product product = productList.get(position);

        // setting up a title , price and description
        holder.titleproduct.setText(product.getTitle());
        holder.descriptionproduct.setText(product.getDescription());
        holder.pricepurchase.setText("₹ "+product.getPrice());


        // glide for product image;
        Glide.with(context)
                .load(product.getImage())
                .into(holder.imageproduct);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
class MyProduct extends RecyclerView.ViewHolder{

    ImageView imageproduct;
    TextView titleproduct;
    TextView descriptionproduct;
    TextView pricepurchase;

    public MyProduct(@NonNull View itemView) {
        super(itemView);



        imageproduct = itemView.findViewById(R.id.product_image);
        titleproduct = itemView.findViewById(R.id.product_title);
        descriptionproduct = itemView.findViewById(R.id.product_description);
        pricepurchase = itemView.findViewById(R.id.product_price);

    }
}
