package com.example.retrofit.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.ProductFullScreenActivity;
import com.example.retrofit.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<MyProduct>
{
    private List<Product> products;
    private Context context;

    // now making constructor
    public ProductAdapter(List<Product> products, Context context){
        this.products = products;
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
        Product product = products.get(position);
        holder.text_product_name.setText(product.getTitle());
        holder.text_product_price.setText("Price: ₹ " + product.getPrice());
        Picasso.get().load(product.getThumbnail()).into(holder.product_image);

        holder.btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductFullScreenActivity.class);
                intent.putExtra("product_data", product);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
class MyProduct extends RecyclerView.ViewHolder {

    ImageView product_image;
    TextView text_product_name, text_product_price;
    Button btn_buy;

    public MyProduct(@NonNull View itemView) {
        super(itemView);


        product_image = itemView.findViewById(R.id.image_product);
        text_product_name = itemView.findViewById(R.id.product_name);
        text_product_price = itemView.findViewById(R.id.text_product_price);
        btn_buy = itemView.findViewById(R.id.btn_buy);



    }
}
