package com.example.creditrack.CredActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.creditrack.CredActivity.Database.LoanManager;
import com.example.creditrack.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    LoanManager loanManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // initializing the all views
        drawerLayout = findViewById(R.id.drawer_main);
        navigationView = findViewById(R.id.navigationview);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        loanManager = new LoanManager(this);

        // Setup NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_loan);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        // Setup AppBarConfiguration
        appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.nav_home,
                        R.id.nav_dashboard,
                        R.id.nav_settings,
                        R.id.nav_add_loan,
                        R.id.nav_add_payment,
                        R.id.nav_loan_details,
                        R.id.nav_personal_list,
                        R.id.nav_password_change
                )
                        .setOpenableLayout(drawerLayout)
                        .build();


        // now setting up a toolbar wit navcantroller and drawer
        if (navController != null) {
            NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
            NavigationUI.setupWithNavController(navigationView, navController);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout ?")
                    .setIcon(R.drawable.logout_cred)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            loanManager.Logout();
                            Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, WelcomeScreen.class));
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return (navController != null && NavigationUI.navigateUp(navController, appBarConfiguration))
                || super.onSupportNavigateUp();
    }
}