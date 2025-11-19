package com.example.autohub.CarAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.autohub.CarFragment.Audi_Fragment;
import com.example.autohub.CarFragment.BMW_Fragment;
import com.example.autohub.CarFragment.Details_Fragment;
import com.example.autohub.CarFragment.Hyundai_Fragment;

import com.example.autohub.CarFragment.MahindraFragment;
import com.example.autohub.CarFragment.Toyota_Fragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BMW_Fragment();
            case 1:
                return new Audi_Fragment();
            case 2:
                return new MahindraFragment();
            case 3:
                return new Hyundai_Fragment();
            case 4:
                return new Toyota_Fragment();
            case 5:
                return new Details_Fragment();
            default:
                return new BMW_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
