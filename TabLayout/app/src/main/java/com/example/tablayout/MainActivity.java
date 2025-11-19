package com.example.tablayout;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tablayout.Fragments.PagerAdapter.AudiFragment;
import com.example.tablayout.Fragments.PagerAdapter.BMWFragment;
import com.example.tablayout.Fragments.PagerAdapter.MahindraFragment;
import com.example.tablayout.Fragments.PagerAdapter.TabAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    Toolbar toolbar;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        setUpViewPager();

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setUpViewPager(){
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());

        // getting the tab title for the 3 fragments
        adapter.AddTitle("BMW", new BMWFragment());
        adapter.AddTitle("Audi",new AudiFragment());
        adapter.AddTitle("Mahindra",new MahindraFragment());
        viewPager.setAdapter(adapter);
    }
}