package com.example.retrocrud;

import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrocrud.Retrofit.Model;
import com.example.retrocrud.Retrofit.MyAdapter;
import com.example.retrocrud.Retrofit.RetrofitClient;
import com.example.retrocrud.Retrofit.Retrofitinterface;
import com.example.retrocrud.databinding.ActivityMainBinding;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private List<Model> list;
    private MyAdapter adapter;

    private Retrofitinterface retrofitinterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // initializing the retrofit interface
        retrofitinterface = RetrofitClient.getRetrofitInstance().create(Retrofitinterface.class);

        binding .crudRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.crudRecyclerview.setHasFixedSize(true);

        list = new ArrayList<>();
        adapter = new MyAdapter(this,list);
        binding.crudRecyclerview.setAdapter(adapter);

        // calling the fetch data method
        fetchData();


        // inserting the data
        binding.addDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertDaa();
            }
        });

        // For Deleting the data
        DeleteData();



    }
    // fetching the data
    private void fetchData(){
        binding.progressBar.setVisibility(View.VISIBLE);
        Call<List<Model>> call = retrofitinterface.fetchdata();


        // Async task to fetch the data
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                  binding.progressBar.setVisibility(View.GONE);
                  if (response.isSuccessful() && response.body() != null){
                      list.clear();
                      list.addAll(response.body());
                      adapter.notifyDataSetChanged();
                  }
                  else {
                      Toast.makeText(MainActivity.this, " No Data Found", Toast.LENGTH_SHORT).show();
                  }

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }
    // for insert the Data
    private void InsertDaa() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_data_retro);
        dialog.setCancelable(true);

        // Fix: Make dialog full width
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);


        EditText name = dialog.findViewById(R.id.name_edittext);
        EditText address = dialog.findViewById(R.id.address_edittext);
        EditText email = dialog.findViewById(R.id.email_edittext);
        EditText password = dialog.findViewById(R.id.password_edittext);


        AppCompatButton btnadd = dialog.findViewById(R.id.add_data_btn);



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_text = name.getText().toString();
                String address_text = address.getText().toString();
                String email_text = email.getText().toString();
                String password_text = password.getText().toString();


                if (name_text.isEmpty() || address_text.isEmpty() || email_text.isEmpty() || password_text.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                } else {
                    Call<JsonObject> call = retrofitinterface.insertData(name_text, address_text, email_text, password_text);

                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                            Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            fetchData();

                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                            Toast.makeText(MainActivity.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
        dialog.show();


    }
    // for delete the data itemTouchHelper
    private void DeleteData(){
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback( 0,ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Model model = list.get(position);

                Deleterecords(model.getId(),position);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;
                ColorDrawable background = new ColorDrawable(Color.RED);
                Drawable deleteIcon = ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_delete);

                if (dX < 0) { // Swiping to the left
                    // Draw red background
                    background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                    background.draw(c);

                    // Draw delete icon
                    if (deleteIcon != null) {
                        int iconMargin = (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
                        int iconTop = itemView.getTop() + (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
                        int iconBottom = iconTop + deleteIcon.getIntrinsicHeight();
                        int iconLeft = itemView.getRight() - iconMargin - deleteIcon.getIntrinsicWidth();
                        int iconRight = itemView.getRight() - iconMargin;

                        deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                        deleteIcon.draw(c);
                    }
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(binding.crudRecyclerview);



    }
    // delete the data from the Database
    private void Deleterecords(int id , int position){
        Call<JsonObject> call = retrofitinterface.deleteData(id);


        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                list.remove(position);
                adapter.notifyItemRemoved(position);
                Toast.makeText(MainActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                // Restore item if delete failed
                adapter.notifyItemChanged(position);
            }
        });
    }
}