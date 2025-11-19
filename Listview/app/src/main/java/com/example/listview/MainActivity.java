package com.example.listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    int [] arrayNo = new int[]{1,2,3,4,5,6};

    ArrayList<String> arrayNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);




        arrayNames.add("Rutvik");
        arrayNames.add("Dhaval");
        arrayNames.add("Jayraj");
        arrayNames.add("Bhavin");
        arrayNames.add("Ruturaj");
        arrayNames.add("Rutvik");
        arrayNames.add("Dhaval");
        arrayNames.add("Jayraj");
        arrayNames.add("Bhavin");
        arrayNames.add("Ruturaj");
        arrayNames.add("Rutvik");
        arrayNames.add("Dhaval");
        arrayNames.add("Jayraj");
        arrayNames.add("Bhavin");
        arrayNames.add("Ruturaj");
        arrayNames.add("Rutvik");
        arrayNames.add("Dhaval");
        arrayNames.add("Jayraj");
        arrayNames.add("Bhavin");
        arrayNames.add("Ruturaj");




        // creating adapter for Listview

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1,arrayNames);
        // setting up a adapter to set listview
        listView.setAdapter(adapter);


        // setting up click event on Listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = arrayNames.get(position);
                Toast.makeText(MainActivity.this, "Welcome"+name, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), AutoComplete_spinner.class);
               i.putExtra("name",name);
                startActivity(i);
            }
        });
    }
}