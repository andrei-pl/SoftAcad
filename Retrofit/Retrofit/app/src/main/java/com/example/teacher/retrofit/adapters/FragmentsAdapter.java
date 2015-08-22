package com.example.teacher.retrofit.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.teacher.retrofit.fragments.ScreenSlidePageFragment;

/**
 * Created by Teacher on 1/19/2015.
 */
public class FragmentsAdapter extends FragmentStatePagerAdapter {
    public FragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new ScreenSlidePageFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    private final String[] TITLES = { "Categories", "Home", "Top Paid", "Top Free", "Top Grossing", "Top New Paid",
            "Top New Free", "Trending" };

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }


}
