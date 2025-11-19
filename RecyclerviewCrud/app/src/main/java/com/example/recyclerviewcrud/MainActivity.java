package com.example.recyclerviewcrud;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addbtn;
    ArrayList<Model> list;

    MyAdapter adapter;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView= findViewById(R.id.recyclerview_crud);

        addbtn = findViewById(R.id.add_crud_btn);


        list = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list.add(new Model(R.drawable.img_1,"Rutvik Babariya","Age:25","6351202084"));
        list.add(new Model(R.drawable.img_2,"Dhaval Babariya","Age:26","6654123765"));
        list.add(new Model(R.drawable.img_3,"Jayraj Babariya","Age:30","9987612345"));
        list.add(new Model(R.drawable.img_4,"Bhavin Babariya","Age:28","9876781234"));
        list.add(new Model(R.drawable.img_5,"Narendra Rathod","Age:31","6355556123"));
        list.add(new Model(R.drawable.img_6,"Ruturaj Jadeja","Age:24","8965897865"));
        list.add(new Model(R.drawable.img_7,"Jaydip Babariya","Age:32","9089786542"));
        list.add(new Model(R.drawable.img_8,"Ronak Dholakiya","Age:23","9876543210"));
        list.add(new Model(R.drawable.img_9,"Raju","Age:25","8765456789"));
        list.add(new Model(R.drawable.img_10,"Ravi","Age:32","8976561234"));
        list.add(new Model(R.drawable.img_11,"Rajvir","Age:20","7865431267"));
        list.add(new Model(R.drawable.img_12,"Virat Kohli","Age: 39","9876543210"));
        list.add(new Model(R.drawable.img_13,"M.S. Dhoni","Age:44","8987654518"));
        list.add(new Model(R.drawable.img_14,"Yuvaraj","Age:30","7865409081"));
        list.add(new Model(R.drawable.img_15,"Bhavesh","Age:31","1234567890"));
        list.add(new Model(R.drawable.img_16,"Nayan Babariya","Age:24","2345678908"));
        list.add(new Model(R.drawable.img_17,"Pratik","Age:25","9876510967"));
        list.add(new Model(R.drawable.img_18,"Bhavin Dangar","Age:25","6743120986"));
        list.add(new Model(R.drawable.img_19,"Utsav","Age:25","5632124567"));
        list.add(new Model(R.drawable.img_20,"Bharat Odedara","Age:27","6123789065"));



        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Add_List_Activity.class);
                startActivityForResult(intent,Add_List_Activity.ADD_REQUEST_CODE);
            }
        });

        adapter = new MyAdapter(this,this,list);
        recyclerView.setAdapter(adapter);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Add_List_Activity.ADD_REQUEST_CODE && resultCode == RESULT_OK){

            if (data != null){
                String name = data.getStringExtra("name");
                String age = data.getStringExtra("age");
                String phone_number = data.getStringExtra("phoneNumber");

                // Now adding a default image to the data when user will inserted the data
                list.add(new Model(R.drawable.img_1,name,age,phone_number));
                adapter.notifyDataSetChanged();
            }

        }

        else {
            Toast.makeText(this, "Data Has been Not Added", Toast.LENGTH_SHORT).show();
        }
    }
}