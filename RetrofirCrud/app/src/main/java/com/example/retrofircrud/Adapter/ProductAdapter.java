package com.example.retrofircrud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofircrud.Model.CartItemModel;
import com.example.retrofircrud.Model.ProductModel;
import com.example.retrofircrud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<MyCart> {

    private Context context;
    private List<CartItemModel> cartList;

    public ProductAdapter(Context context, List<CartItemModel> cartList) {
        this.context = context;
        this.cartList = cartList;
    }


    @NonNull
    @Override
    public MyCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_design, parent, false);
        MyCart myCart = new MyCart(view);
        return myCart;
    }



    @Override
    public void onBindViewHolder(@NonNull MyCart holder, int position) {

       CartItemModel cartItemModel =cartList.get(position);

        holder.txtTitle.setText(cartItemModel.getTitle());
        holder.txtPrice.setText("₹ " + cartItemModel.getPrice());
        holder.txtQty.setText("Qty: " + cartItemModel.getQuantity());
        holder.txtDiscount.setText(cartItemModel.getDiscountPercentage() + "% OFF");
        holder.txtTotal.setText("Total: ₹ " + cartItemModel.getDiscountedTotal());

        // getting image from url
        Picasso.get().load(cartItemModel.getThumbnail()).into(holder.imgProduct);



    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
class MyCart extends RecyclerView.ViewHolder {

    ImageView imgProduct;
    TextView txtTitle, txtPrice, txtQty, txtDiscount, txtTotal;


    public MyCart(@NonNull View itemView) {
        super(itemView);

        imgProduct = itemView.findViewById(R.id.imgProduct);
        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtPrice = itemView.findViewById(R.id.txtPrice);
        txtQty = itemView.findViewById(R.id.txtQty);
        txtDiscount = itemView.findViewById(R.id.txtDiscount);
        txtTotal = itemView.findViewById(R.id.txtTotal);

    }
}
