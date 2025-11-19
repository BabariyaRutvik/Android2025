package com.example.recyclerviewcrud;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import java.util.ArrayList;

public class Add_List_Activity extends AppCompatActivity {

    EditText edt_add_name,edt_add_age,edt_add_phone;
    Button btnAddData;
    ArrayList<Model> list;
    public static final int ADD_REQUEST_CODE = 1;
    MyAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        edt_add_name = findViewById(R.id.edt_add_name);
        edt_add_age = findViewById(R.id.edt_add_age);
        edt_add_phone = findViewById(R.id.edt_add_phone);

        btnAddData = findViewById(R.id.add_btn);

        list = new ArrayList<>();

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_add_name.getText().toString();
                String age = edt_add_age.getText().toString();
                String phone_number = edt_add_phone.getText().toString();

                if (name.isEmpty() || age.isEmpty() || phone_number.isEmpty()){
                    Toast.makeText(Add_List_Activity.this, "You have to fill All Fields", Toast.LENGTH_SHORT).show();
                    return; // exists if fields are empty
                }
                // creating a new Intent to hold the data
                Intent resultIntent = new Intent();


                resultIntent.putExtra("name",name);
                resultIntent.putExtra("age",age);
                resultIntent.putExtra("phoneNumber",phone_number);

                // setting up the result OK if data should be added
                setResult(RESULT_OK,resultIntent);


                Toast.makeText(Add_List_Activity.this, "Data Added Successfully!", Toast.LENGTH_SHORT).show();
                finish();

            }
        });


    }
}