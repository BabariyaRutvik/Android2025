package com.example.costumlistview;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Model> car_list;
    ListView carlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        carlist = findViewById(R.id.custom_listview);

        car_list = new ArrayList<>();

        // lising out all car details
        car_list.add(new Model(R.drawable.bmw_x7,"1.12 Cr","BMW X7"));
        car_list.add(new Model(R.drawable.bmw_xm,"2.25 Cr","BMW XM"));
        car_list.add(new Model(R.drawable.bmwx1,"78 Lacs","BMW X1"));
        car_list.add(new Model(R.drawable.bmwx2,"67 Lacs","BMW X2"));
        car_list.add(new Model(R.drawable.bmwx3,"89 Lacs","BMW X3"));
        car_list.add(new Model(R.drawable.bmwx4,"72 Lacs","BMW X4"));
        car_list.add(new Model(R.drawable.bmw_m8,"1.56 Cr","BMW M8"));
        car_list.add(new Model(R.drawable.bmwx6,"1.09 Cr","BMW X6"));
        car_list.add(new Model(R.drawable.audi_a4,"56 Lacs","Audi A4"));
        car_list.add(new Model(R.drawable.audi_q3,"45 Lacs","Audi Q3"));
        car_list.add(new Model(R.drawable.audi_s5_sportback,"67 Lacs","Audi Sports Back"));
        car_list.add(new Model(R.drawable.mahindra_xev_9e,"25.50 Lacs","Mahindra xev 9e"));
        car_list.add(new Model(R.drawable.mahindra_be_6,"21.98 Lacs","Mahindra Be 6e"));
        car_list.add(new Model(R.drawable.skoda_slavia,"13.89 Lacs","Skoda Slavia"));
        car_list.add(new Model(R.drawable.skoda_octavia,"12.67 Lacs","Skoda Octavia"));


        MyAdapter adapter = new MyAdapter(MainActivity.this,car_list);
        carlist.setAdapter(adapter);


    }
}