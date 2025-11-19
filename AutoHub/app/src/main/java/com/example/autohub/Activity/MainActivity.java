package com.example.autohub.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.autohub.CarAdapter.ViewPagerAdapter;
import com.example.autohub.R;
import com.example.autohub.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up adapter for ViewPager2
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        binding.viewpagerswipe.setAdapter(viewPagerAdapter);


        binding.viewpagerswipe.setOffscreenPageLimit(6);


        binding.bottomNaviMenu.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.nav_bmw) {
                    binding.viewpagerswipe.setCurrentItem(0, true);
                }
                else if (itemId == R.id.nav_audi) {
                    binding.viewpagerswipe.setCurrentItem(1, true);
                }
                else if (itemId == R.id.nav_mahindra) {
                    binding.viewpagerswipe.setCurrentItem(2, true);
                }
                else if (itemId == R.id.nav_hyundai) {
                    binding.viewpagerswipe.setCurrentItem(3, true);
                }
                else if (itemId == R.id.nav_toyota) {
                    binding.viewpagerswipe.setCurrentItem(4, true);
                }
                else if (itemId == R.id.nav_details){
                    binding.viewpagerswipe.setCurrentItem(5,true);
                }

                return true;
            }
        });

        // Update BottomNavigation when user swipes ViewPager
        binding.viewpagerswipe.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.bottomNaviMenu.getMenu().getItem(position).setChecked(true);
            }
        });
    }
}
