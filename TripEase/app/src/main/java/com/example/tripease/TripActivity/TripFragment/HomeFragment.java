package com.example.tripease.TripActivity.TripFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tripease.R;
import com.example.tripease.TripActivity.TripAdapter.CountryAdapter;
import com.example.tripease.TripActivity.TripAdapter.ImageSliderAdapter;
import com.example.tripease.TripActivity.TripModel.CountryModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    RecyclerView countryRecyclerView;
    ViewPager2 sliderViewPager;
    TabLayout dots_tab;
    Handler handler = new Handler();
    List<Integer> sliderList = new ArrayList<>();
    CountryAdapter countryAdapter;

    ArrayList<CountryModel> countryList;


    public HomeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        countryRecyclerView = view.findViewById(R.id.country_recycleview);
        sliderViewPager = view.findViewById(R.id.swipe_image_viewpager);
        dots_tab = view.findViewById(R.id.dots_tab);

        countryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        countryList = new ArrayList<>();

        // for a Country Name
        SetCountryAdapter();

        // for a slider Image
        SetUpSlider();
        AutoSlideImage();
        SetUpDots();


        return  view;
    }
    public void SetCountryAdapter(){

        countryList.add(new CountryModel(R.drawable.india_, "India"));
        countryList.add(new CountryModel(R.drawable.usa,"USA"));
        countryList.add(new CountryModel(R.drawable.australia,"Australia"));
        countryList.add(new CountryModel(R.drawable.new_zealand,"New Zealand"));
        countryList.add(new CountryModel(R.drawable.switzeland,"Switzerland"));
        countryList.add(new CountryModel(R.drawable.sri_lanka,"Sri Lanka"));
        countryList.add(new CountryModel(R.drawable.japan,"Japan"));
        countryList.add(new CountryModel(R.drawable.indonesia,"Indonesia"));
        countryList.add(new CountryModel(R.drawable.norway,"Norway"));
        countryList.add(new CountryModel(R.drawable.uk,"United Kingdom"));

        countryAdapter = new CountryAdapter(getContext(), countryList);
        countryRecyclerView.setAdapter(countryAdapter);
    }
    public void SetUpSlider(){
        sliderList.add(R.drawable.shree_ram_mandir);
        sliderList.add(R.drawable.eiffel_tower);
        sliderList.add(R.drawable.statue_of_liberty);
        sliderList.add(R.drawable.tokyo_towers);
        sliderList.add(R.drawable.amazon_rainforest);
        sliderList.add(R.drawable.airoplane);

        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(sliderList, getContext());
        sliderViewPager.setAdapter(imageSliderAdapter);

        sliderViewPager.setClipToPadding(false);
        sliderViewPager.setClipChildren(false);
        sliderViewPager.setOffscreenPageLimit(3);

        sliderViewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        sliderViewPager.setPageTransformer((page, position) -> {
            page.setScaleY(1 - Math.abs(position) * 0.20f);
        });



    }
    private void AutoSlideImage(){
       Runnable runnable = new Runnable() {
           @Override
           public void run() {

               int next = sliderViewPager.getCurrentItem() + 1;

               if (next >= sliderList.size()){
                   next = 0;
               }
               sliderViewPager.setCurrentItem(next, true);
               handler.postDelayed(this, 1500);


           }
       };
       handler.postDelayed(runnable,1500);
    }
    private void SetUpDots(){
        new TabLayoutMediator(dots_tab, sliderViewPager, (tab, position) -> {

        }).attach();
    }
}