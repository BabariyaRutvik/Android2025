package com.example.sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MySqlite> {
    private Context context;
    private ArrayList<Model> list;
    DBHelper dbHelper;

    // creating a adapter class constructor.
    public MyAdapter(Context context, ArrayList<Model> list, DBHelper dbHelper) {
        this.context = context;
        this.list = list;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public MySqlite onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.design_file, parent, false);
        MySqlite sqlite = new MySqlite(view);
        return sqlite;
    }

    @Override
    public void onBindViewHolder(@NonNull MySqlite holder, int position) {
        Model model = list.get(position);

        holder.text_name.setText(model.getName());
        holder.text_age.setText(model.getAge());
        holder.text_dob.setText(model.getDate_of_birth());
        holder.textPhone.setText(model.getPhone());
        holder.text_email.setText(model.getEmail());
        holder.text_password.setText(model.getPassword());
        dbHelper = new DBHelper(context);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialogPlus = DialogPlus.newDialog(holder.itemView.getContext())
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.update_data))
                        .setExpanded(true, ViewGroup.LayoutParams.WRAP_CONTENT)
                        .create();


                View view = dialogPlus.getHolderView();

                TextInputEditText name = view.findViewById(R.id.edit_update_name);
                TextInputEditText age = view.findViewById(R.id.edit_update_age);
                TextInputEditText dob = view.findViewById(R.id.edit_update_dob);
                TextInputEditText phone = view.findViewById(R.id.edit_update_phone);
                TextInputEditText email = view.findViewById(R.id.edit_update_email);
                TextInputEditText password = view.findViewById(R.id.edit_update_password);
                Button btnupdate = view.findViewById(R.id.btn_update_data);

                name.setText(model.getName());
                age.setText(model.getAge());
                dob.setText(model.getDate_of_birth());
                phone.setText(model.getPhone());
                email.setText(model.getEmail());
                password.setText(model.getPassword());


                dialogPlus.show();

                btnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        model.setName(name.getText().toString());
                        model.setAge(age.getText().toString());
                        model.setDate_of_birth(dob.getText().toString());
                        model.setPhone(phone.getText().toString());
                        model.setEmail(email.getText().toString());
                        model.setPassword(password.getText().toString());

                        dbHelper.UpdateData(model);
                        Toast.makeText(context, " Data Update Successfully", Toast.LENGTH_SHORT).show();
                        notifyItemChanged(position);
                        dialogPlus.dismiss();

                    }


                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Model getDataAt(int position) {
        return list.get(position);
    }
}
class MySqlite extends RecyclerView.ViewHolder{

    TextView text_name,text_age,text_dob,textPhone,text_email,text_password;
    public MySqlite(@NonNull View itemView) {
        super(itemView);



        text_name = itemView.findViewById(R.id.text_name);
        text_age = itemView.findViewById(R.id.text_age);
        text_dob = itemView.findViewById(R.id.text_dob);
        textPhone = itemView.findViewById(R.id.text_phone_number);
        text_email = itemView.findViewById(R.id.text_email);
        text_password = itemView.findViewById(R.id.text_password);

    }
}
