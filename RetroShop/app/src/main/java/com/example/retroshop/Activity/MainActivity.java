package com.example.retroshop.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retroshop.Adapter.ProductAdapter;
import com.example.retroshop.Model.ProductModel;
import com.example.retroshop.ProductClient.ProductClient;
import com.example.retroshop.R;
import com.example.retroshop.databinding.ActivityMainBinding;
import com.example.retroshop.productinterface.Productinterface;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
     ProductAdapter productAdapter;
    List<ProductModel> productList;

    Productinterface productinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Initialize ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.productRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.productRecyclerview.setHasFixedSize(true);


        FetchProduct();
        
        // Attach ItemTouchHelper to RecyclerView
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.productRecyclerview);
    }
    private void FetchProduct(){
        // Show progress bar
        binding.progressBar.setVisibility(View.VISIBLE);
        
        // initializing productinterface class
        productinterface = ProductClient.getClient().create(Productinterface.class);

        Call<List<ProductModel>> call = productinterface.getProducts();


        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                // Hide progress bar
                binding.progressBar.setVisibility(View.GONE);
                
                if (response.isSuccessful() && response.body() != null) {

                    productList = response.body();
                    productAdapter = new ProductAdapter(MainActivity.this, productList);
                    binding.productRecyclerview.setAdapter(productAdapter);
                }
                else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                // Hide progress bar
                binding.progressBar.setVisibility(View.GONE);
                
                Toast.makeText(MainActivity.this, "Something went wrong Check you internet connection", Toast.LENGTH_SHORT).show();
            }
        });
        // now adding a new product

       binding.addProductBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Dialog dialog = new Dialog(MainActivity.this);
               dialog.setContentView(R.layout.add_product);
               dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

               EditText edtTitle = dialog.findViewById(R.id.edtTitle);
               EditText edtPrice = dialog.findViewById(R.id.edtPrice);
               EditText edtDescription = dialog.findViewById(R.id.edtDescription);
               EditText edtCategory = dialog.findViewById(R.id.edtCategory);
               EditText edtImage = dialog.findViewById(R.id.edtImage);
               Button btnSubmit = dialog.findViewById(R.id.btnSubmit);



               btnSubmit.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String title = edtTitle.getText().toString();
                       String priceStr = edtPrice.getText().toString();
                       String description = edtDescription.getText().toString();
                       String category = edtCategory.getText().toString();
                       String image = edtImage.getText().toString();

                       if (title.isEmpty() || priceStr.isEmpty() || description.isEmpty() || category.isEmpty() || image.isEmpty()) {
                           Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                           return;
                       }
                       
                       double price = 0;
                       try {
                            price = Double.parseDouble(priceStr);
                       } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this, "Invalid price", Toast.LENGTH_SHORT).show();
                            return;
                       }

                       ProductModel newProduct = new ProductModel(title, description, price, category, image);
                       // newProduct.setTitle(title); // Already set in constructor


                       Call<ProductModel> call = productinterface.createProduct(newProduct);

                       // for adding the product
                       call.enqueue(new Callback<ProductModel>() {
                           @Override
                           public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                               if (response.isSuccessful()){
                                   productList.add(0,response.body());
                                   productAdapter.notifyItemInserted(0);
                                   Toast.makeText(MainActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                                   dialog.dismiss();

                               } else {
                                   Toast.makeText(MainActivity.this, "Failed to add product: " + response.code(), Toast.LENGTH_SHORT).show();
                               }
                           }

                           @Override
                           public void onFailure(Call<ProductModel> call, Throwable t) {
                               Toast.makeText(MainActivity.this, "Something went wrong: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                           }
                       });

                   }
               });



               dialog.show();
           }
       });

    }
    
    // Define the ItemTouchHelper Callback
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        ProductModel deleteItem = null;
        int deletePosition = -1;
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
           deletePosition = viewHolder.getAdapterPosition();
           deleteItem = productList.get(deletePosition);

           // removing item fropm recyclerview
           productList.remove(viewHolder.getAdapterPosition());
           productAdapter.notifyItemRemoved(deletePosition);

           // now making a undo Functionality
            Snackbar snackbar = Snackbar.make(binding.productRecyclerview, "Deleted", Snackbar.LENGTH_LONG);
            snackbar.setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productList.add(deletePosition, deleteItem);
                    productAdapter.notifyItemInserted(deletePosition);
                }
            });
            snackbar.addCallback(new Snackbar.Callback(){
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                      DeleteProduct(deleteItem.getId());
                    }
                }

            });
            snackbar.show();

        }
    };

    // Method to call API to delete product
    private void DeleteProduct(int id) {
        Call<Void> call = productinterface.deleteProduct(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Failed to delete product from server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error deleting product", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
