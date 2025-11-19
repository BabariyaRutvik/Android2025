package com.example.carxpert.CarActivity.FragmentAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CarPagerAdapter extends FragmentPagerAdapter
{
    private List<String> titleFragName = new ArrayList<>();
    private List<Fragment> fragList = new ArrayList<>();

    public CarPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragName.get(position);
    }

    public void AddFragments(String title, Fragment fragment){
        titleFragName.add(title);
        fragList.add(fragment);


    }
}
