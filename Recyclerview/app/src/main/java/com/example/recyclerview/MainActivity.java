package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView contactrecyclerview;
    FloatingActionButton addContactbtn;
   RecyclerviewAdapter adapter;

    ArrayList<ContactModel> contactList ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contactrecyclerview = findViewById(R.id.recyclerview_contact);
        addContactbtn = findViewById(R.id.add_contacts_f1);

        contactrecyclerview.setLayoutManager(new LinearLayoutManager(this));

        contactList = new ArrayList<>();

        addContactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_contact_design);


                EditText edt_name = dialog.findViewById(R.id.edt_name);
                EditText edt_number = dialog.findViewById(R.id.edt_number);
                Button btn_add_user = dialog.findViewById(R.id.btn_add_user);

                btn_add_user.setOnClickListener(new View.OnClickListener() {
                    String name = "" ;
                    String number = "";
                    @Override
                    public void onClick(View v) {
                        if (!edt_name.getText().toString().equals("")){
                             name = edt_name.getText().toString();

                        }
                        else {
                            Toast.makeText(MainActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                        }
                        if (!edt_number.getText().toString().equals("")){
                             number = edt_number.getText().toString();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Please Enter Contacts Number", Toast.LENGTH_SHORT).show();
                        }

                        contactList.add(new ContactModel(R.drawable.img_1, name,number));

                        adapter.notifyItemInserted(contactList.size() - 1);

                        contactrecyclerview.scrollToPosition(contactList.size() - 1);

                            dialog.dismiss();



                    }
                });
                dialog.show();
            }
        });

        contactList.add(new ContactModel(R.drawable.img_1,"Rutvik Babariya","63512021084"));
        contactList.add(new ContactModel(R.drawable.img_2,"Dhaval Babariya","9878654521"));
        contactList.add(new ContactModel(R.drawable.img_3,"Jayraj Babariya","8967543256"));
        contactList.add(new ContactModel(R.drawable.img_4,"Bhavin Babariya","7656788965"));
        contactList.add(new ContactModel(R.drawable.img_5,"Narendra Rathod","6355053750"));
        contactList.add(new ContactModel(R.drawable.img_6,"Ronak Dholakiya","6355120789"));
        contactList.add(new ContactModel(R.drawable.img_7,"Ruturaj Jadeja","6355431204"));
        contactList.add(new ContactModel(R.drawable.img_8,"Raju Karmata","8767543210"));
        contactList.add(new ContactModel(R.drawable.img_9,"Pratik Rajkotiya","865445678"));
        contactList.add(new ContactModel(R.drawable.img_10,"Parth Soni","1234567890"));
        contactList.add(new ContactModel(R.drawable.img_11,"Hardik Parmar","0987654321"));
        contactList.add(new ContactModel(R.drawable.img_12,"Raj Patel","8767543218"));
        contactList.add(new ContactModel(R.drawable.img_13,"Utsav","6351107884"));
        contactList.add(new ContactModel(R.drawable.img_14,"Bhavesh","8967431278"));
        contactList.add(new ContactModel(R.drawable.img_15,"Krishnakant","7856431289"));


         adapter = new RecyclerviewAdapter(this,contactList);
        contactrecyclerview.setAdapter(adapter);

    }
}