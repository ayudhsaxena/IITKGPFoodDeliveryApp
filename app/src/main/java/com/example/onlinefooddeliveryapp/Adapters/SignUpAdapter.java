package com.example.onlinefooddeliveryapp.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.onlinefooddeliveryapp.Activities.MainDetailsFragment;
import com.example.onlinefooddeliveryapp.Activities.signUpDetails;

public class SignUpAdapter extends FragmentPagerAdapter {
    private static final int NUM_ITEMS = 2;


    public SignUpAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 : return signUpDetails.newInstance();
            case 1 : return MainDetailsFragment.newInstance();
            default : return null;
        }
    }
}
