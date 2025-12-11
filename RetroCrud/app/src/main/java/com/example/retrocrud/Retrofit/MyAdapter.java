package com.example.retrocrud.Retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrocrud.R;
import com.google.gson.JsonObject;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdapter extends RecyclerView.Adapter<MyView> {

    private Context context;
    private List<Model>list;


    // constructor
    public MyAdapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design,parent,false);
       MyView myview = new MyView(view);
        return myview;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
          Model model = list.get(position);

          holder.name.setText(model.getName());
          holder.address.setText(model.getAddress());
          holder.email.setText(model.getEmail());
          holder.password.setText(model.getPassword());

          // Set avatar text (First letter of name)
          if (model.getName() != null && !model.getName().isEmpty()) {
              holder.avatar.setText(String.valueOf(model.getName().charAt(0)).toUpperCase());
          } else {
              holder.avatar.setText("?");
          }


          // for update the data
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // using DialogPlus
                DialogPlus dialogPlus = DialogPlus.newDialog(holder.itemView.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_data))
                        .setExpanded(true, RecyclerView.LayoutParams.WRAP_CONTENT)
                        .create();

                View view = dialogPlus.getHolderView();


                EditText edtName = view.findViewById(R.id.update_name_edittext);
                EditText edtAddress = view.findViewById(R.id.update_address_edittext);
                EditText edtEmail = view.findViewById(R.id.update_email_edittext);
                EditText edtPassword = view.findViewById(R.id.update_password_edittext);

                Button btnUpdate = view.findViewById(R.id.update_data_btn);



                edtName.setText(model.getName());
                edtAddress.setText(model.getAddress());
                edtEmail.setText(model.getEmail());
                edtPassword.setText(model.getPassword());

                dialogPlus.show();



                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = edtName.getText().toString();
                        String address = edtAddress.getText().toString();
                        String email = edtEmail.getText().toString();
                        String password = edtPassword.getText().toString();

                        if (name.isEmpty() || address.isEmpty() || email.isEmpty() || password.isEmpty()){
                            Toast.makeText(holder.itemView.getContext(), "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // Fix: Don't create a new model here if we want to update the list later easily, 
                            // or verify we update the list item correctly. 
                            // For now, I'll keep the logic but fix the Retrofit types.
                            // Ideally, we should update 'model' (from onBind) or list.get(position) in onResponse.
                            
                            Model updateModel = new Model(list.get(position).getId(),name,address,email,password);

                            Retrofitinterface retrofitinterface = RetrofitClient.getRetrofitInstance().create(Retrofitinterface.class);

                            Call<JsonObject> call = retrofitinterface.updateData(updateModel.getId(), updateModel.getName(), updateModel.getAddress(), updateModel.getEmail(), updateModel.getPassword());



                            call.enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    dialogPlus.dismiss();

                                    if (response.isSuccessful()) {
                                      // Update the actual object in the list
                                      Model originalModel = list.get(position);
                                      originalModel.setName(name);
                                      originalModel.setAddress(address);
                                      originalModel.setEmail(email);
                                      originalModel.setPassword(password);
                                      notifyItemChanged(position);

                                      Toast.makeText(context, "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                                    }
                                    else {
                                        Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {
                                        dialogPlus.dismiss();

                                        Toast.makeText(context, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class MyView extends RecyclerView.ViewHolder{

    TextView name,address,email,password,avatar;

    public MyView(@NonNull View itemView) {
        super(itemView);


        name = itemView.findViewById(R.id.name_text_value);
        address = itemView.findViewById(R.id.address_text_value);
        email = itemView.findViewById(R.id.email_text_value);
        password = itemView.findViewById(R.id.password_text_value);
        avatar = itemView.findViewById(R.id.tv_avatar);


    }
}
