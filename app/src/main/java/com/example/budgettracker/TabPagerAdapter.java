package com.example.budgettracker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class TabPagerAdapter extends FragmentPagerAdapter {

    public static  final  int NUMBER_OF_FRAGMENTS=5;
    public TabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new firstFrgment();
            case 1:
                return new secondFragment();
            case 2:
                return new ThirdFragment();
            case 3:
                return new ForthFragment();
            case 4:
                return  new FifthFragment();
            default:
                return  null;
        }

    }

    @Override
    public int getCount() {
        return NUMBER_OF_FRAGMENTS;
    }

}

