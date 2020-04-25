package com.example.onlinefooddeliveryapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.onlinefooddeliveryapp.Adapters.HomePageAdapter;
import com.example.onlinefooddeliveryapp.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class ConsumerHomePage extends AppCompatActivity{
    public static ViewPager mPager;
    public static HomePageAdapter mAdapter;
    public static Snackbar mSnackBar;
    public static FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_home_page);
        mFragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        TabLayout mTabLayout = findViewById(R.id.tab_layout);
        mPager = findViewById(R.id.pager);
        mAdapter = new HomePageAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mPager);
        String s = "The order has been placed successfully!";
        CoordinatorLayout layout = findViewById(R.id.myCoordinatorLayout);
        mSnackBar = Snackbar.make(layout,s, BaseTransientBottomBar.LENGTH_LONG);
        if(intent.getIntExtra("edit",0) == 2){
            Log.d("ConsumerHomePage","I am here!");
            mPager.setCurrentItem(2);
        }


    }




}
