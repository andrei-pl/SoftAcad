package com.example.teacher.retrofit;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.example.teacher.retrofit.adapters.FragmentsAdapter;

/**
 * Created by Teacher on 1/19/2015.
 */
public class TabsActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));


        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        tabs.setViewPager(pager);


    }
}
