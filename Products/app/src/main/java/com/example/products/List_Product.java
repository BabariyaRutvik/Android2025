package com.example.products;

import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class List_Product extends AppCompatActivity {

    GridView gridView;
    ArrayList<Model>selectedList;

    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        gridView = findViewById(R.id.grid_view);

        selectedList = new ArrayList<>();

        selectedList = (ArrayList<Model>) getIntent().getSerializableExtra("selected_list");

        adapter = new MyAdapter(this,selectedList,item ->{});
        gridView.setAdapter(adapter);

    }
}