package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ContactModel> contactList;

    private int lastposition = -1;
    public RecyclerviewAdapter(Context context, ArrayList<ContactModel> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ContactModel model = contactList.get(position);

        holder.imagecontact.setImageResource(model.getImage());
        holder.text_name.setText(model.getName());
        holder.text_number.setText(model.getNumber());

        // setting up to the Animation
        SetAnimation(holder.itemView,position);

        holder.itemView.setOnClickListener(v -> {
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.update_contacts);

            EditText edt_update_name = dialog.findViewById(R.id.edt_name_update);
            EditText edt_update_number = dialog.findViewById(R.id.edt_number_update);
            Button update_btn = dialog.findViewById(R.id.btn_update_user);

            edt_update_name.setText(model.getName());
            edt_update_number.setText(model.getNumber());

            update_btn.setOnClickListener(v1 -> {
                String name = edt_update_name.getText().toString();
                String number = edt_update_number.getText().toString();

                contactList.set(position, new ContactModel(contactList.get(position).getImage(),name, number));
                notifyItemChanged(position);
                dialog.dismiss();
            });

            dialog.show();
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder  builder = new AlertDialog.Builder(context)
                        .setTitle("Delete !")
                        .setMessage("Are you sure you want to delete the Data ?")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                contactList.remove(position);
                                notifyItemRemoved(position);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                 builder.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    // ✅ Inner ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagecontact;
        TextView text_name, text_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagecontact = itemView.findViewById(R.id.image_contact);
            text_name = itemView.findViewById(R.id.contact_name);
            text_number = itemView.findViewById(R.id.contact_number);
        }
    }
    private void SetAnimation(View viewToAnimate, int position){
        if (position > lastposition) {


            Animation slin_in = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(slin_in);

            lastposition = position;
        }
        }
}
