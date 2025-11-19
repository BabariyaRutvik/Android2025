package com.example.contactapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Contact_ListActivity extends AppCompatActivity {

    RecyclerView selectedRecyclerView;
    ArrayList<ContactModel> selectedList;
    ContactAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        selectedRecyclerView = findViewById(R.id.contact_recyclerview);
        selectedRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve Parcelable list
        selectedList = getIntent().getParcelableArrayListExtra("select_list");

        if (selectedList != null && !selectedList.isEmpty()) {
            adapter = new ContactAdapter(this, selectedList);
            selectedRecyclerView.setAdapter(adapter);
        }
    }
}
