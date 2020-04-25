package com.example.onlinefooddeliveryapp.Adapters;

import android.view.View;
import android.view.ViewGroup;

import com.example.onlinefooddeliveryapp.R;
import com.example.onlinefooddeliveryapp.orderrelated.Order;

public class OrderDetailsAdapter extends MycartRecyclerViewAdapter {
    public Order mOrder;

    public OrderDetailsAdapter(Order mOrder){
        super();
        this.mOrder = mOrder;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItem = mOrder.getItems().get(position);
        holder.mNameView.setText(holder.mItem.getItemName());
        String cost = "₹ " + String.valueOf(holder.mItem.getCost());
        holder.mCostView.setText(cost);
        switch (holder.mItem.getLabel()) {
            case "N":
                holder.mImageView.setImageResource(R.drawable.nonveg);
                break;
            case "V":
                holder.mImageView.setImageResource(R.drawable.veg);
                break;
            case "E":
                holder.mImageView.setImageResource(R.drawable.egg);
                break;
        }
        String qty = "" + holder.mItem.getQuantity();
        holder.mQuantityView.setText(qty);
        holder.increase.setVisibility(View.GONE);
        holder.decrease.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mOrder.getItems().size();
    }


}
