package com.example.carxpert.CarActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.carxpert.CarActivity.CarFragments.Audi_Fragment;
import com.example.carxpert.CarActivity.CarFragments.BMW_Fragment;
import com.example.carxpert.CarActivity.CarFragments.Mahindra_Fragment;
import com.example.carxpert.CarActivity.CarFragments.Skoda_Fragment;
import com.example.carxpert.CarActivity.CarFragments.Toyota_Fragment;
import com.example.carxpert.CarActivity.FragmentAdapter.CarPagerAdapter;
import com.example.carxpert.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout car_Tab;
    Toolbar carTool;

    ViewPager carPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        carTool = findViewById(R.id.toolbar);
        setSupportActionBar(carTool);


        carPager = findViewById(R.id.car_viewpager);
        setUpViewPager();

        car_Tab = findViewById(R.id.car_tab);
        car_Tab.setupWithViewPager(carPager);

    }

    private void setUpViewPager() {
        CarPagerAdapter carPagerAdapter = new CarPagerAdapter(getSupportFragmentManager());
        carPagerAdapter.AddFragments("BMW", new BMW_Fragment());
        carPagerAdapter.AddFragments("Audi",new Audi_Fragment());
        carPagerAdapter.AddFragments("Mahindra",new Mahindra_Fragment());
        carPagerAdapter.AddFragments("Skoda", new Skoda_Fragment());
        carPagerAdapter.AddFragments("Toyota",new Toyota_Fragment());


        carPager.setAdapter(carPagerAdapter);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_car,menu);

        MenuItem item = menu.findItem(R.id.car_search);
        SearchView searchView = (SearchView) item.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                int tab  = carPager.getCurrentItem();

                String tag = "android:switcher:" + R.id.car_viewpager + ":" + tab;

                switch (tab){
                    case 0:
                        BMW_Fragment bmw = (BMW_Fragment) getSupportFragmentManager().findFragmentByTag(tag);
                        if (bmw != null){
                            bmw.FilterBMWData(newText);
                        }
                        break;


                    case 1:
                        Audi_Fragment audi = (Audi_Fragment) getSupportFragmentManager().findFragmentByTag(tag);
                        if (audi != null){
                            audi.FilterAudiData(newText);
                        }
                        break;

                    case 2:
                        Mahindra_Fragment mahindra = (Mahindra_Fragment) getSupportFragmentManager().findFragmentByTag(tag);
                        if (mahindra != null){
                            mahindra.FilterMahindraData(newText);
                        }
                        break;
                    case 3:
                        Skoda_Fragment skoda = (Skoda_Fragment) getSupportFragmentManager().findFragmentByTag(tag);

                        if (skoda != null){
                            skoda.FilterSkodaData(newText);
                        }
                        break;

                    case 4:
                        Toyota_Fragment toyota = (Toyota_Fragment) getSupportFragmentManager().findFragmentByTag(tag);

                        if (toyota != null){
                            toyota.FilterToyotaData(newText);
                        }
                        break;
                }
                return true;
            }
        });
        return true;
    }
}