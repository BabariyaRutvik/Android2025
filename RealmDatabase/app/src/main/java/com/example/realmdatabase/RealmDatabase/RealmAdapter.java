package com.example.realmdatabase.RealmDatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmdatabase.R;
import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmAdapter extends RecyclerView.Adapter<MyRealm> {

    private Context context;
    private RealmResults<RealmModel> list;

    // now making the constructor
    public RealmAdapter(Context context, RealmResults<RealmModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyRealm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.design,parent,false);
        return new MyRealm(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRealm holder, int position) {
        RealmModel model = list.get(position);

        holder.text_p_name.setText(model.getPerson_name());
        holder.text_p_phone_number.setText(model.getPhone_number());
        holder.text_p_age.setText(model.getAge());
        holder.text_p_email.setText(model.getEmail());
        holder.text_p_password.setText(model.getPassword());


        // now updating the data to using dialogplus

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialogPlus = DialogPlus.newDialog(holder.itemView.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_design))
                        .setExpanded(true, ViewGroup.LayoutParams.WRAP_CONTENT)
                        .create();


                View view = dialogPlus.getHolderView();
                // now initialising all fields for update the data
                TextInputEditText edit_text_name = view.findViewById(R.id.edit_text_name_update);
                TextInputEditText edit_phone = view.findViewById(R.id.edit_text_phone_number_update);
                TextInputEditText edit_age = view.findViewById(R.id.edit_text_age_update);
                TextInputEditText edit_email = view.findViewById(R.id.edit_text_email_update);
                TextInputEditText edit_password = view.findViewById(R.id.edit_text_password_update);

                Button updatebtn = view.findViewById(R.id.update_btn);

                // Set existing data to edit text
                if (edit_text_name != null) edit_text_name.setText(model.getPerson_name());
                if (edit_phone != null) edit_phone.setText(model.getPhone_number());
                if (edit_age != null) edit_age.setText(model.getAge());
                if (edit_email != null) edit_email.setText(model.getEmail());
                if (edit_password != null) edit_password.setText(model.getPassword());


                updatebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(@NonNull Realm realm) {
                                // Check for nulls before setting data, although getText() on TextInputEditText usually returns non-null Editable
                                if (edit_text_name != null && edit_text_name.getText() != null) model.setPerson_name(edit_text_name.getText().toString());
                                if (edit_phone != null && edit_phone.getText() != null) model.setPhone_number(edit_phone.getText().toString());
                                if (edit_age != null && edit_age.getText() != null) model.setAge(edit_age.getText().toString());
                                if (edit_email != null && edit_email.getText() != null) model.setEmail(edit_email.getText().toString());
                                if (edit_password != null && edit_password.getText() != null) model.setPassword(edit_password.getText().toString());
                            }
                        });
                        realm.close(); // Close the local instance
                        notifyDataSetChanged();
                        CustomToastUpdate.show(context,"Data Updated Successfully");
                        dialogPlus.dismiss();
                    }
                });
                dialogPlus.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class MyRealm extends RecyclerView.ViewHolder{

    TextView text_p_name,text_p_phone_number,text_p_age,text_p_email,text_p_password;

    public MyRealm(@NonNull View itemView) {
        super(itemView);

        text_p_name = itemView.findViewById(R.id.text_p_name);
        text_p_phone_number = itemView.findViewById(R.id.text_p_phone_number);
        text_p_age = itemView.findViewById(R.id.text_p_age);
        text_p_email = itemView.findViewById(R.id.text_p_email);
        text_p_password = itemView.findViewById(R.id.text_p_password);

    }
}
