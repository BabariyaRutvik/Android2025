package com.example.tripease.TripActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripease.R;
import com.example.tripease.TripActivity.TripAdapter.Top10Adapter;
import com.example.tripease.TripActivity.TripModel.Top10Model;

import java.util.ArrayList;

public class Top10PlacesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Top10Model> placesList;

    Top10Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10_places);

        recyclerView  = findViewById(R.id.topPlacesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        placesList = new ArrayList<>();

        Intent i = getIntent();
        int position = i.getIntExtra("position", 0);


        if (position == 0){
            placesList.add(new Top10Model(R.drawable.shree_ram_mandir_,"Shree Ram Mandir","Ayodhya","March - April"));
            placesList.add(new Top10Model(R.drawable.dwarka_mandir,"Dwarka Shree Krishna Mandir","Gujarat, Dwarka","August - September"));
            placesList.add(new Top10Model(R.drawable.somnath,"Somnath","Gujarat, Somnath","August - September"));
            placesList.add(new Top10Model(R.drawable.kashi,"Kashi","Uttar Predesh, Kashi","August - September"));
            placesList.add(new Top10Model(R.drawable.badrinath,"Badrinath","Uttarakhand, Chammoli","May - June"));
            placesList.add(new Top10Model(R.drawable.kedarnath,"Kedarnath","Uttarakhand, RudraPrayag","May - June"));
            placesList.add(new Top10Model(R.drawable.ooty,"Ooty","Tamil Nadu , Nilgiri","March - June"));
            placesList.add(new Top10Model(R.drawable.leh,"Leh","Union Territory Of India , Leh","May - September"));
            placesList.add(new Top10Model(R.drawable.manali,"Manali","Himachal Pradesh, Kullu","March - June"));
            placesList.add(new Top10Model(R.drawable.hevelock_island,"Hevelock Island","Andaman & Nicobar Islands (Union Territory of India)","October - May"));


        }
        else if (position == 1) {
            placesList.add(new Top10Model(R.drawable.new_york,"New York","New York,(NYC)","April - June"));
            placesList.add(new Top10Model(R.drawable.grand_canyon_arizona,"Grand Canyon, Arizona","Arizona, South Rim, USA","March - May"));
            placesList.add(new Top10Model(R.drawable.los_angeles,"Los Angeles","California, USA","March - May"));
            placesList.add(new Top10Model(R.drawable.san_francisco,"San Francisco","California, USA","September - November"));
            placesList.add(new Top10Model(R.drawable.time_square_new_york,"Time Square, New York","New York","November - December"));
            placesList.add(new Top10Model(R.drawable.miami_florida,"Miami, Florida","Florida, USA","December - April"));
            placesList.add(new Top10Model(R.drawable.washington_dc,"Washington, DC","Washington, USA","March - June"));
            placesList.add(new Top10Model(R.drawable.yellowstone_national_park,"Yellowstone National Park","Wyoming, USA","April - May"));
            placesList.add(new Top10Model(R.drawable.orlando_florida,"Orlando, Florida","Florida, USA","May - June"));
            placesList.add(new Top10Model(R.drawable.hawaii,"Hawaii","Hawaii, USA","May - June"));


        }
        else if (position == 2) {

            placesList.add(new Top10Model(R.drawable.sydney,"Sydney","New South Wales","April - June"));
            placesList.add(new Top10Model(R.drawable.brisbane_australia,"Brisbane","Queensland","March - May"));
            placesList.add(new Top10Model(R.drawable.melborne,"Melbourne","Victoria","March - May"));
            placesList.add(new Top10Model(R.drawable.perth_australia,"Perth","Western Australia","September - November"));
            placesList.add(new Top10Model(R.drawable.great_barrier_reef,"Great Barrier Reef","Coral Sea","June - October"));
            placesList.add(new Top10Model(R.drawable.ulluru_australia,"Ulluru Australia","South Australia","December - April"));
            placesList.add(new Top10Model(R.drawable.gold_coast_australia,"Gold Coast","Queensland","March - June"));
            placesList.add(new Top10Model(R.drawable.greate_ocean_road_australia,"Great Ocean Road","South Australia","April - May"));
            placesList.add(new Top10Model(R.drawable.tasmania_australia,"Tasmania","Tasmania","May - June"));
            placesList.add(new Top10Model(R.drawable.whitsunday_islands,"Whitsunday Islands","Western Australia","May - June"));




        }
        else if (position == 3) {
            placesList.add(new Top10Model(R.drawable.new_queenstown,"Queenstown","New Zealand","April - June"));
            placesList.add(new Top10Model(R.drawable.new_auckland,"Auckland","New Zealand","March - May"));
            placesList.add(new Top10Model(R.drawable.new_milford_sound,"Milford Sound","New Zealand","March - May"));
            placesList.add(new Top10Model(R.drawable.new_rotorua,"Rotorua","New Zealand","September - November"));
            placesList.add(new Top10Model(R.drawable.new_wellington,"Wellington","New Zealand","November - April"));
            placesList.add(new Top10Model(R.drawable.new_fox_and_franz_glaciers,"Fox and Franz Glacier","New Zealand","December - April"));
            placesList.add(new Top10Model(R.drawable.new_christchurch_island,"Christchurch","New Zealand","March - June"));
            placesList.add(new Top10Model(R.drawable.new_lake_tekapo,"Lake Tekapo","New Zealand","April - May"));
            placesList.add(new Top10Model(R.drawable.new_mount_cook_national_park,"Mount Cook National Park","New Zealand","May - June"));
            placesList.add(new Top10Model(R.drawable.new_hobitton,"Hobitton","New Zealand","May - June"));



        }
        else if (position == 4){
            placesList.add(new Top10Model(R.drawable.switz_zurich,"Zurich","Switzerland","January - Fabruary"));
            placesList.add(new Top10Model(R.drawable.switz_bern,"Bern","Switzerland","March - April"));
            placesList.add(new Top10Model(R.drawable.switz_geneva,"Geneva","Switzerland","March - April"));
            placesList.add(new Top10Model(R.drawable.switz_lucerne,"Lucerne","Switzerland","September - November"));
            placesList.add(new Top10Model(R.drawable.switz_basel,"Basel","Switzerland","November - April"));
            placesList.add(new Top10Model(R.drawable.switz_interlaken,"Interlaken","Switzerland","April - May"));
            placesList.add(new Top10Model(R.drawable.switz_jungfrajoach,"Jungfrajoach","Switzerland","March - June"));
            placesList.add(new Top10Model(R.drawable.switz_zermatt,"Zermatt","Switzerland","April - May"));
            placesList.add(new Top10Model(R.drawable.switz_montreux,"Montreux","Switzerland","May - June"));
            placesList.add(new Top10Model(R.drawable.switz_lauterbrunnen,"Lauterbrunnen Velly","Switzerland","May - June"));


        }
        else if (position == 5){
            placesList.add(new Top10Model(R.drawable.lanka_colombo,"Colombo","Sri Lanka","April - June"));
            placesList.add(new Top10Model(R.drawable.laanka_ashok_vatika,"Ashok Vatika","Sri Lanka","April - May"));
            placesList.add(new Top10Model(R.drawable.lanka_kandy,"Kandy","Sri Lanka","March - May"));
            placesList.add(new Top10Model(R.drawable.lanka_sigiriya,"Sigiriya","Sri Lanka","June - July"));
            placesList.add(new Top10Model(R.drawable.lanka_ella,"Ella","Sri Lanka","September - November"));
            placesList.add(new Top10Model(R.drawable.lanka_ravan_palace,"Ravan Palace","Sri Lanka","April - May"));
            placesList.add(new Top10Model(R.drawable.lanka_mirissa,"Mirissa","Sri Lanka","March - June"));
            placesList.add(new Top10Model(R.drawable.lanka_bentota,"Bentota","Sri Lanka","April - May"));
            placesList.add(new Top10Model(R.drawable.lanka_galle,"Galle","Sri Lanka","May - June"));
            placesList.add(new Top10Model(R.drawable.lanka_yalla_national_park,"Yala National Park","Sri Lanka","May - June"));

        }
        else  if (position == 6){
            placesList.add(new Top10Model(R.drawable.japan_tokyo,"Tokyo","Japan","March - May"));
            placesList.add(new Top10Model(R.drawable.japan_osaka,"Osaka","Japan","March - May"));
            placesList.add(new Top10Model(R.drawable.japan_kyoto,"Kyoto","Japan","March - May"));
            placesList.add(new Top10Model(R.drawable.japan_hakone,"Hakone","Japan","September - November"));
            placesList.add(new Top10Model(R.drawable.japan_nara,"Nara","Japan","May - June"));
            placesList.add(new Top10Model(R.drawable.japan_mount_fuuji,"Mount Fuuji","Japan","May - June"));
            placesList.add(new Top10Model(R.drawable.japan_okinawa,"Okinawa","Japan","June - October"));
            placesList.add(new Top10Model(R.drawable.japan_nikko,"Nikko","Japan","April - May"));
            placesList.add(new Top10Model(R.drawable.japan_hokkaido,"Hokkaido","Japan","May - June"));
            placesList.add(new Top10Model(R.drawable.japan_hiroshima,"Hiroshima","Japan","October - May"));

        }
        else if (position == 7) {
          placesList.add(new Top10Model(R.drawable.indo_bali,"Bali","Indonesia","March - June"));
          placesList.add(new Top10Model(R.drawable.indo_jakarta,"Jakarta","Indonesia","April - May"));
          placesList.add(new Top10Model(R.drawable.indo_surabaya,"Surabaya","Indonesia","March - May"));
          placesList.add(new Top10Model(R.drawable.indo_bandung,"Bandung","Indonesia","September - November"));
          placesList.add(new Top10Model(R.drawable.indo1_malang,"Malang","Indonesia","December - April"));
          placesList.add(new Top10Model(R.drawable.indo_yogyakarta,"Yogyakarta","Indonesia","May - June"));
          placesList.add(new Top10Model(R.drawable.indo1_bintaan,"Bintaan","Indonesia","May - June"));
          placesList.add(new Top10Model(R.drawable.indo_lombok_island,"Lombok Island","Indonesia","June - October"));
          placesList.add(new Top10Model(R.drawable.indo_komodo_island,"Komodo Island","Indonesia","April - May"));
          placesList.add(new Top10Model(R.drawable.indo_raja_ampat,"Raja Ampat","Indonesia","May - June"));
        }
        else if (position == 8) {
          placesList.add(new Top10Model(R.drawable.norway_oslo,"Oslo","Norway","May - September"));
          placesList.add(new Top10Model(R.drawable.norway_bergen,"Bergen","Norway","June - October"));
          placesList.add(new Top10Model(R.drawable.norway_trondheim,"Trondheim","Norway","April - May"));
          placesList.add(new Top10Model(R.drawable.norway_tromso,"Tromso","Norway","September - November"));
          placesList.add(new Top10Model(R.drawable.norway_lofoten,"Lofoten","Norway","May - June"));
          placesList.add(new Top10Model(R.drawable.norway_stavanger,"Stavanger","Norway","December - April"));
          placesList.add(new Top10Model(R.drawable.norway_alesund,"Alesund","Norway","March - June"));
          placesList.add(new Top10Model(R.drawable.norway_preikestolen,"Preikestolen","Norway","April - May"));
          placesList.add(new Top10Model(R.drawable.norway_geirangerfjord,"Geirangerfjord","Norway","May - June"));
          placesList.add(new Top10Model(R.drawable.norway_svalbard,"Svalbard","Norway","May - June"));

        }
        else if (position == 9) {
            placesList.add(new Top10Model(R.drawable.uk_london, "London", "England", "April - June"));
            placesList.add(new Top10Model(R.drawable.uk_oxford, "Oxford", "England", "March - May"));
            placesList.add(new Top10Model(R.drawable.uk_manchester, "Manchester", "England", "March - May"));
            placesList.add(new Top10Model(R.drawable.uk_liverpool, "Liverpool", "England", "September - November"));
            placesList.add(new Top10Model(R.drawable.uk_cardiff, "Cardiff", "England", "August - September"));
            placesList.add(new Top10Model(R.drawable.uk_skotland_edinburgh, "Edinburgh", "England, Scotland", "April - May"));
            placesList.add(new Top10Model(R.drawable.uk_lake_district, "Lake District", "England", "March - June"));
            placesList.add(new Top10Model(R.drawable.uk_bellfast, "Bellfast", "England", "April - May"));
            placesList.add(new Top10Model(R.drawable.uk_stonehenge,"Stonehenge","England","May - June"));
            placesList.add(new Top10Model(R.drawable.uk_york,"York","England","September - November"));





        }

        adapter = new Top10Adapter(this, placesList);
        recyclerView.setAdapter(adapter);

    }
}


