package com.example.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.contactapp.databinding.ActivityAddContactBinding;

import java.util.ArrayList;

public class Add_Contact_Activity extends AppCompatActivity {
     ActivityAddContactBinding binding;

     ArrayList<ContactModel> contactlist;
     ContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edtAddName.getText().toString();
                String number = binding.edtAddNumber.getText().toString();

                contactlist = new ArrayList<>();

                if (name. isEmpty() || number.isEmpty()){
                    Toast.makeText(Add_Contact_Activity.this, "Please Fill Out Details!", Toast.LENGTH_SHORT).show();
                }




                Intent resultIntent = new Intent();
                resultIntent.putExtra("name",name);
                resultIntent.putExtra("number",number);
                setResult(RESULT_OK,resultIntent);
                finish(); // returning to the main activity


            }
        });

    }
}