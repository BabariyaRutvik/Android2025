package com.example.products;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<Model> products_list;

     MyAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        products_list = new ArrayList<>();
        listView = findViewById(R.id.product_listview);


        products_list.add(new Model(R.drawable.apple_watch_10,"Apple Watch Series 10","Price : 46,999"," Bigger display with up to 30% more screen area. A thinner, lighter and more comfortable design. Advanced health and fitness features provide invaluable insights. Safety features connect you to help when you need it. " +
                "Faster charging gives you 80% battery in about 30 " +
                "minutes."));
        products_list.add(new Model(R.drawable.s24_ultra,"Samsung Galaxy S24 Ultra","Price: 1,29,999","" +
                "Welcome to the era of mobile AI. With Galaxy S24 Ultra and One UI in your hands, you can unleash completely " +
                "new levels of creativity, productivity and possibility."));
        products_list.add(new Model(R.drawable.samsung_watch_ultra,"Samsung Watch Ultra","Price : 34,770",
                "Cushion Design Dial with Aero-grade Titanium & Sapphire" +
                        " glass is built to survive in the most extreme conditions. " +
                        "With 10ATM & IP68 Rating, " +
                        "Watch ULTRA is ready even for the " +
                        "Oceans. MIL-STD-810H makes it even more " +
                        "reliable for tough outdoor environment. " +
                        "It has secured ability for operation at a " +
                        "wider range of environment like temperature or altitude"));
        products_list.add(new Model(R.drawable.s25_ultra,"Samsung Galaxy s25 Ultra","Price: 1,35,999",
                "Enter the new era of mobile AI with a companion that " +
                        "stays one-step ahead of your needs. Let natural " +
                        "conversation guide you through everyday tasks " +
                        "without switching between multiple apps, while " +
                        "Now Brief keeps you updated with your schedule, " +
                        "reminders, battery status, and Energy Score, " +
                        "all within One UI."));
        products_list.add(new Model(R.drawable.s9_fe_tab,"Samsung S9 fe tab","Price : 24,999","Outstanding vividness with 27.69 cm " +
                "(10.9”) display, 90 Hz Refresh Rate, " +
                "2304 x 1440 (WQXGA, 249 PPI)"));

        products_list.add(new Model(R.drawable.iphone_17pro,"Iphone 17 Pro","Price : 1,11,999",
                "UNIBODY DESIGN. FOR EXCEPTIONAL POWER — Heat-forged aluminium unibody enclosure for " +
                        "the most powerful iPhone ever made."));

        products_list.add(new Model(R.drawable.macbook_m4,"Apple Macbook 4","Price: 94,990",
                "SPEED OF LIGHTNESS — MacBook Air with the M4 chip lets " +
                        "you blaze through work and play. With Apple " +
                        "Intelligence,* up to 18 hours of battery life,* " +
                        "and an incredibly portable design, " +
                        "you can take on anything, anywhere."));

        products_list.add(new Model(R.drawable.bmw_x7,"BMW X7","Price : 1.25 Cr",
                "The BMW X7 makes for a well-rounded luxury 6/7-seater SUV packaging a spacious, " +
                        "rich and tech-loaded interior with a driving experience " +
                        "that’s sure to leave you smiling!"));

        products_list.add(new Model(R.drawable.dell_inspi,"Dell inpiro 3530","Price: 65,999","" +
                "Processor: Intel Core i7-1355U (up to 5.00 GHz, 10 Cores, " +
                "12MB Cache) // RAM: 16 GB, " +
                "2 x 8 GB, DDR4, 2666 MHz // Storage: 512GB SSD"));

        products_list.add(new Model(R.drawable.lenovo_idea,"Lenovo Ideapad Slim 3","Price 45,999",
                "Processor: Intel Core i5-12450H " +
                        "| Speed: 2.0 GHz (Base) - 4.4 GHz (Max) | 8 Cores " +
                        "| 12 Threads | 12MB Cache"));





        // creating adapter with callback
        adapter  = new MyAdapter(this, products_list, selectedItems -> {
           if (selectedItems.size() == 5){
               Toast.makeText(this, "Products Selected!", Toast.LENGTH_SHORT).show();

             Intent i = new Intent(getApplicationContext(),List_Product.class);
             i.putExtra("selected_list",selectedItems);
             startActivity(i);
           }
        });
        listView.setAdapter(adapter);







    }
}