package com.example.onlinefooddeliveryapp.Adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.example.onlinefooddeliveryapp.Activities.MenuActivity;
import com.example.onlinefooddeliveryapp.R;
import com.example.onlinefooddeliveryapp.Activities.hallCanteensFragment.OnListFragmentInteractionListener;


import java.util.ArrayList;


public class MyhallCanteenItemRecyclerViewAdapter extends RecyclerView.Adapter<MyhallCanteenItemRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<String> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyhallCanteenItemRecyclerViewAdapter(ArrayList<String> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_hallcanteenitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.name.setText(holder.mItem);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
                v.startAnimation(buttonClick);
                Log.d("touch",holder.mItem + " is clicked!");
                Intent intent = new Intent(v.getContext(), MenuActivity.class);
                intent.putExtra("hall_name",holder.mItem);
                v.getContext().startActivity(intent);



                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.

                    mListener.onListFragmentInteraction(holder.mItem);

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name =  view.findViewById(R.id.hall_name);

        }



    }
}
