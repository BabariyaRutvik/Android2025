package com.example.bundlepassing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Receiving data from MainActivity
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String studentName = intent.getStringExtra("Student Name");
        int rollNo = intent.getIntExtra("Roll No", 0);

        TextView textView = findViewById(R.id.txtName);

        // Set the title in the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        
        textView.setText("Student Name: " + studentName + "\nRoll No: " + rollNo);
    }
}
