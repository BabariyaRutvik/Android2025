package com.example.producthub.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.producthub.ProductActivity.FullScreenProduct;
import com.example.producthub.ProductModel.Product;
import com.example.producthub.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ProductView> {
    private Context mContext;
    private List<Product> mProductList;
    public List<Product> mProductListFull;

    public MyAdapter(Context context, List<Product> productList) {
        this.mContext = context;
        this.mProductList = productList;
        this.mProductListFull = new ArrayList<>(productList);
    }

    @NonNull
    @Override
    public ProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_design, parent, false);
        return new ProductView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductView holder, int position) {
        Product product = mProductList.get(position);

        holder.productName.setText(product.getName());
        holder.productCategory.setText(product.getCategory());
        holder.productPrice.setText("₹ " + product.getPrice());

        // Decode Base64 image and load using Glide
        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            try {
                byte[] imageBytes = Base64.decode(product.getImageUrl(), Base64.DEFAULT);
                Glide.with(mContext)
                        .asBitmap()
                        .load(imageBytes)
                        .placeholder(R.drawable.ic_person)
                        .error(R.drawable.ic_person)
                        .into(holder.productImage);
            } catch (Exception e) {
                holder.productImage.setImageResource(R.drawable.ic_person);
            }
        } else {
            holder.productImage.setImageResource(R.drawable.ic_person);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, FullScreenProduct.class);
            intent.putExtra("PRODUCT_ID", product.getProductId());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public void filterList(List<Product> filteredList) {
        mProductList = filteredList;
        notifyDataSetChanged();
    }

    public void updateData(List<Product> newList) {
        mProductList = newList;
        mProductListFull = new ArrayList<>(newList);
        notifyDataSetChanged();
    }

    class ProductView extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName, productCategory, productPrice;

        public ProductView(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.iv_product);
            productName = itemView.findViewById(R.id.tv_name);
            productCategory = itemView.findViewById(R.id.tv_category);
            productPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}
