package com.example.onlinefooddeliveryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.onlinefooddeliveryapp.Adapters.SignUpAdapter;
import com.example.onlinefooddeliveryapp.R;

public class SignUp extends AppCompatActivity {
    private static final int NUM = 2;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Intent intent = getIntent();
        mPager = findViewById(R.id.viewPagerSignUp);
        pagerAdapter = new SignUpAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
    }

    public void nextPage(int a){
        mPager.setCurrentItem(a);
    }
}
