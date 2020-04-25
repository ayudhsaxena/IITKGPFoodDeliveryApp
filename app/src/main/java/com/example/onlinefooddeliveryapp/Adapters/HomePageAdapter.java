package com.example.onlinefooddeliveryapp.Adapters;

import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.onlinefooddeliveryapp.Activities.CartFragment;
import com.example.onlinefooddeliveryapp.Activities.MyAccountFragment;
import com.example.onlinefooddeliveryapp.Activities.hallCanteensFragment;


public class HomePageAdapter extends FragmentStatePagerAdapter {
    SparseArray<Fragment> registeredFragments = new SparseArray<>();
    private static final int NUM_ITEMS = 3;

    public HomePageAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 : Log.d("HomePageAdapeter","First tab instance created!");
                return hallCanteensFragment.newInstance();

            case 1 : Log.d("HomePageAdapeter","Second tab instance created!");
                Log.d("HomePageAdapter",CartFragment.hallName);
                return CartFragment.newInstance();

            case 2 : Log.d("HomePageAdapeter","Third tab instance created!");
                return MyAccountFragment.newInstance();

            default : return null;
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;

    }

    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0 : return "Order";
            case 1 : return "Cart";
            case 2 : return  "Account";
            default : return null;
        }
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

}
