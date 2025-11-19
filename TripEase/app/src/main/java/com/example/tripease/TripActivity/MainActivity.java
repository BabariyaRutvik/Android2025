package com.example.tripease.TripActivity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tripease.R;
import com.example.tripease.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

   ActivityMainBinding binding;
   AppBarConfiguration appBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // setting up the toolbar from the app bar layout
        setSupportActionBar(binding.appBarTripMain.tripToolbar);


        // setting up the drawerlayout and Navigation View
        DrawerLayout drawerLayout = binding.drawerLayotMain;
        NavigationView navigationView = binding.navViewTrip;


        // setting up th navhost fragment

        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().
                        findFragmentById(R.id.nav_host_fragment_contain_car);
        NavController navController = navHostFragment.getNavController();


        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_profile,
                R.id.nav_settings,
                R.id.nav_bookmark
        ).setOpenableLayout(drawerLayout).build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



    }

    @Override
    public boolean onSupportNavigateUp() {
               NavHostFragment navHostFragment = (NavHostFragment)
                       getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_contain_car);

               NavController navController = navHostFragment.getNavController();
               return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}