package com.example.contactapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<MyContactView> {

    private Context context;
    private ArrayList<ContactModel> contactList;
    private ArrayList<ContactModel> selected_list;

    public ContactAdapter(Context context, ArrayList<ContactModel> contactList){
        this.contactList = contactList;
        this. context =context;
        this.selected_list = new ArrayList<>();


    }
    // returning the selected contacts
    public ArrayList<ContactModel>getSelectedContacts(){
            return selected_list;
    }
    // method to filtering out contact
    public void filterContact(ArrayList<ContactModel>contactList1){

        contactList = contactList1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyContactView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.contact_design,parent,false);
        MyContactView myContactView = new MyContactView(view);
        return myContactView;
    }

    @Override
    public void onBindViewHolder(@NonNull MyContactView holder, int position) {
        ContactModel model = contactList.get(position);

        holder.imageContact.setImageResource(model.getImage());
        holder.text_contact_name.setText(model.getContactName());
        holder.text_contact_number.setText(model.getContactNumber());

        // highlight if selected
        if (selected_list.contains(model)){
            holder.itemView.setBackgroundColor(Color.parseColor("#E0E0E0"));

        }
        else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
        // setonLongClickListener for itemview
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (selected_list.contains(model)){
                    // deselecting the item
                    selected_list.remove(model);
                    holder.itemView.setBackgroundColor(Color.TRANSPARENT);

                }
                else {
                    // selecting the item
                    selected_list.add(model);
                    holder.itemView.setBackgroundColor(Color.parseColor("#E0E0E0"));

                }
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialogPlus = DialogPlus.newDialog(holder.itemView.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_contacts))
                        .setExpanded(true,ViewGroup.LayoutParams.WRAP_CONTENT)
                        .create();

                View view = dialogPlus.getHolderView();


                EditText edt_update_name = view.findViewById(R.id.edt_update_name);
                EditText edt_update_number = view.findViewById(R.id.edt_update_number);
                Button btn_update_data = view.findViewById(R.id.update_btn);

                edt_update_name.setText(model.getContactName());
                edt_update_number.setText(model.getContactNumber());

                btn_update_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = edt_update_name.getText().toString();
                        String number = edt_update_number.getText().toString();

                        contactList.set(position, new ContactModel(contactList.get(position).getImage(),name,number));
                        notifyItemChanged(position);
                        dialogPlus.dismiss();

                    }
                });
                dialogPlus.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
class MyContactView extends RecyclerView.ViewHolder{

    CircleImageView imageContact;
    TextView text_contact_name,text_contact_number;


    public MyContactView(@NonNull View itemView) {
        super(itemView);

        imageContact = itemView.findViewById(R.id.image_contact);
        text_contact_name = itemView.findViewById(R.id.text__contact_name);
        text_contact_number = itemView.findViewById(R.id.text__contact_number);
    }
}
