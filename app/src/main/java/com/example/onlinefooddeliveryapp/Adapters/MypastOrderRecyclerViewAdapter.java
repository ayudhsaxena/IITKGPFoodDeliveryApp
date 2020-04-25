package com.example.onlinefooddeliveryapp.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinefooddeliveryapp.Activities.MyAccountFragment;
import com.example.onlinefooddeliveryapp.R;
import com.example.onlinefooddeliveryapp.orderrelated.Order;

import java.text.SimpleDateFormat;


public class MypastOrderRecyclerViewAdapter extends RecyclerView.Adapter<MypastOrderRecyclerViewAdapter.ViewHolder> {


    public MypastOrderRecyclerViewAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pastorder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int n = MyAccountFragment.mConsumerUser.getPlacedOrders().size();
        holder.mOrder = MyAccountFragment.mConsumerUser.getPlacedOrders().get(n-1-position);
        Log.d(getClass().toString(),""+holder.mOrder.getItems().size());
        String cost = "₹ " + (holder.mOrder.getTotalCost());
        holder.totalCost.setText(cost);
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDate.format(holder.mOrder.getDate());
        holder.dateTextView.setText(date);
        holder.hallName.setText(holder.mOrder.getSource());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
                v.startAnimation(buttonClick);
                TextView textView = v.getRootView().findViewById(R.id.order_date);
                textView.setText(holder.dateTextView.getText());
                RecyclerView recyclerViewNew = v.getRootView().findViewById(R.id.orderDetailsRecyclerView);
                RecyclerView recyclerViewOld = v.getRootView().findViewById(R.id.pastOrderRecyclerView);
                CardView cardView = v.getRootView().findViewById(R.id.orderDetailsCardView);
                OrderDetailsAdapter mAdapter = new OrderDetailsAdapter(holder.mOrder);
                recyclerViewNew.setLayoutManager(new LinearLayoutManager(v.getContext()));
                recyclerViewNew.setAdapter(mAdapter);
                recyclerViewOld.setVisibility(View.GONE);
                cardView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MyAccountFragment.mConsumerUser.getPlacedOrders().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView dateTextView;
        public final TextView totalCost;
        public final TextView hallName;
        public  Order mOrder;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            dateTextView = view.findViewById(R.id.date_text_view);
            totalCost = view.findViewById(R.id.past_order_cost);
            hallName = view.findViewById(R.id.past_order_hallname);
        }


    }
}
