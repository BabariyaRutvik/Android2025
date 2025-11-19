package com.example.listview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AutoComplete_spinner extends AppCompatActivity {

    Spinner spinner;

    ArrayList<String> arrayIDs = new ArrayList<>();
    ArrayList<String> arrayProg = new ArrayList<>();

    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_auto_complete_spinner);


        spinner = findViewById(R.id.spinner);
        autoCompleteTextView = findViewById(R.id.auto_complete);


        // to set a spinner data
        arrayIDs.add("Adhar Card");
        arrayIDs.add("PAN Card");
        arrayIDs.add("Voter IDs Card");
        arrayIDs.add("Driving Licence Card");
        arrayIDs.add("10th Result");
        arrayIDs.add("12th Result");


        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrayIDs);
        spinner.setAdapter(spinnerAdapter);


        // to setting up a autocomplete textview;

        arrayProg.add("C");
        arrayProg.add("C++");
        arrayProg.add("Java");
        arrayProg.add("Kotlin");
        arrayProg.add("HTML");
        arrayProg.add("CSS");
        arrayProg.add("Bootstrap");
        arrayProg.add("SQL");
        arrayProg.add("PHP");


        ArrayAdapter<String> autoAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayProg);
        autoCompleteTextView.setAdapter(autoAdapter);
        autoCompleteTextView.setThreshold(1);

    }
}