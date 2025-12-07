package com.example.retroshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retroshop.Activity.ProductDetailsActivity;
import com.example.retroshop.Model.CategoryModel;
import com.example.retroshop.Model.ProductModel;
import com.example.retroshop.ProductClient.ProductClient;
import com.example.retroshop.R;
import com.example.retroshop.productinterface.Productinterface;
import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductView> 
{
    private Context context;
    private List<ProductModel> productList;
    
    // constructor 
    public ProductAdapter(Context context, List<ProductModel> productList) {
        this.context = context;
        this.productList = productList;
    }


    @NonNull
    @Override
    public ProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_design, parent, false);
        ProductView product = new ProductView(view);
        return product;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductView holder, int position) {
        ProductModel product = productList.get(position);
        
        holder.textTitle.setText(product.getTitle());
        holder.textPrice.setText("₹ " + product.getPrice());
        
        // Fixed: Access the name property from the CategoryModel object
            holder.textCategory.setText(product.getCategory().getName());

        
        // Fixed: Access the first image from the list of images
            Picasso.get().load(product.getImages().get(0)).into(holder.imageProduct);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, ProductDetailsActivity.class);

                    intent.putExtra("title", product.getTitle());
                    intent.putExtra("price", product.getPrice());
                    intent.putExtra("category", product.getCategory().getName());
                    intent.putExtra("description", product.getDescription());
                    intent.putExtra("image", product.getImages().get(0));

                    context.startActivity(intent);
                }
            });

            // now updating the api data
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                DialogPlus dialogPlus = DialogPlus.newDialog(holder.itemView.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_product))
                        .setExpanded(true, ViewGroup.LayoutParams.WRAP_CONTENT)
                        .create();

                View view = dialogPlus.getHolderView();

                TextInputEditText edtUpdateTitle = view.findViewById(R.id.edtUpdateTitle);
                TextInputEditText edtUpdatePrice = view.findViewById(R.id.edtUpdatePrice);
                TextInputEditText edtUpdateDescription = view.findViewById(R.id.edtUpdateDescription);
                TextInputEditText edtUpdateCategory = view.findViewById(R.id.edtUpdateCategory);
                TextInputEditText edtUpdateImage = view.findViewById(R.id.edtUpdateImage);
                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                // setting up old product data list
                edtUpdateTitle.setText(product.getTitle());
                edtUpdatePrice.setText(String.valueOf(product.getPrice()));
                edtUpdateDescription.setText(product.getDescription());
                edtUpdateCategory.setText(product.getCategory().getName());
                edtUpdateImage.setText(product.getImages().get(0));

                dialogPlus.show();


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // creating model class object
                        ProductModel productModel = new ProductModel();
                        productModel.setId(product.getId());
                        productModel.setTitle(edtUpdateTitle.getText().toString());
                        productModel.setPrice(Double.parseDouble(edtUpdatePrice.getText().toString()));
                        productModel.setDescription(edtUpdateDescription.getText().toString());
                        productModel.setCategory(new CategoryModel(product.getCategory().getId(), edtUpdateImage.getText().toString(), edtUpdateCategory.getText().toString(), edtUpdateCategory.getText().toString()));
                        productModel.setImages(new ArrayList<>());
                        productModel.getImages().add(edtUpdateImage.getText().toString());

                        Productinterface updateinterface = ProductClient.getClient().create(Productinterface.class);


                        Call<ProductModel> call = updateinterface.updateProduct(product.getId(), productModel);

                        call.enqueue(new Callback<ProductModel>() {
                            @Override
                            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                                dialogPlus.dismiss();
                                Toast.makeText(context, "Product Updated", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ProductModel> call, Throwable t) {
                                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });


                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
class ProductView extends RecyclerView.ViewHolder{

    ImageView imageProduct;
    TextView textTitle, textPrice, textCategory;

    public ProductView(@NonNull View itemView) {
        super(itemView);
        
        
        imageProduct = itemView.findViewById(R.id.image_product);
        textTitle = itemView.findViewById(R.id.text_title);
        textPrice = itemView.findViewById(R.id.text_price);
        textCategory = itemView.findViewById(R.id.text_category);
    }
}
