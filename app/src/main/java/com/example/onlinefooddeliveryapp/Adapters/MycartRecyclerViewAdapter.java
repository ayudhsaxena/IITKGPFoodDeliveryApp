package com.example.onlinefooddeliveryapp.Adapters;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinefooddeliveryapp.Activities.CartFragment;
import com.example.onlinefooddeliveryapp.Activities.ConsumerHomePage;
import com.example.onlinefooddeliveryapp.Activities.MenuActivity;
import com.example.onlinefooddeliveryapp.R;
import com.example.onlinefooddeliveryapp.orderrelated.foodItem;


public class MycartRecyclerViewAdapter extends RecyclerView.Adapter<MycartRecyclerViewAdapter.ViewHolder> {


    public MycartRecyclerViewAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = CartFragment.cartList.get(position);
        Log.d("cartAdapter", "" + holder.mItem.getItemName());
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

        String qty = "" + MenuActivity.allHalls.get(CartFragment.hallName).get(holder.mItem.getId());
        holder.mQuantityView.setText(qty);
        Log.d("cartAdapter", holder.mQuantityView.getText().toString());
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
                v.startAnimation(buttonClick);
                TextView mEditText = holder.mQuantityView;
                int qty = Integer.valueOf(mEditText.getText().toString());
                qty++;
                mEditText.setText(String.valueOf(qty));
                if (qty == 1) {
                    holder.mItem.setQuantity(qty);
                    CartFragment.cartList.add(holder.mItem);
                } else {
                    for (int i = 0; i < CartFragment.cartList.size(); i++) {
                        if (CartFragment.cartList.get(i).getId() == holder.mItem.getId()) {
                            CartFragment.cartList.get(i).setQuantity(qty);
                            break;
                        }
                    }
                }

                CartFragment.calculateTotal();
                MenuActivity.allHalls.get(CartFragment.hallName).put(holder.mItem.getId(), qty);
                String cost = "₹ " + CartFragment.totalCost;
                CartFragment.costTextView.setText(cost);

            }
        });

        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
                v.startAnimation(buttonClick);
                TextView mEditText = holder.mQuantityView;
                int qty = Integer.valueOf(mEditText.getText().toString());
                if (qty > 0) {
                    qty--;
                    if (qty == 0) {
                        for (foodItem item : CartFragment.cartList) {
                            if (item.getId() == holder.mItem.getId()) {
                                CartFragment.cartList.remove(item);

                                break;
                            }
                        }

                    } else {
                        for (int i = 0; i < CartFragment.cartList.size(); i++) {
                            if (CartFragment.cartList.get(i).getId() == holder.mItem.getId()) {
                                CartFragment.cartList.get(i).setQuantity(qty);
                                break;
                            }
                        }
                    }
                }
                mEditText.setText(String.valueOf(qty));
                MenuActivity.allHalls.get(CartFragment.hallName).put(holder.mItem.getId(), qty);
                if (CartFragment.cartList.isEmpty()){
                    CartFragment.hallName = "";
                    Intent intent = new Intent(v.getContext(), ConsumerHomePage.class);
                    Activity activity = (Activity)v.getContext();
                    activity.startActivity(intent);

                }

                Log.d("CartRecyclerAdapter","Size of Cart List is " + CartFragment.cartList.size());
                CartFragment.mAdapter.notifyDataSetChanged();
                CartFragment.calculateTotal();
                String cost = "₹ " + CartFragment.totalCost;
                CartFragment.costTextView.setText(cost);

            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return CartFragment.cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final ImageView mImageView;
        public final TextView mCostView;
        public final TextView mQuantityView;
        public final ImageButton increase;
        public final ImageButton decrease;
        public foodItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.cart_food_name);
            mImageView = view.findViewById(R.id.cart_food_label);
            mCostView = view.findViewById(R.id.cart_cost);
            mQuantityView = view.findViewById(R.id.cart_quantity);
            increase = view.findViewById(R.id.cart_increase_button);
            decrease = view.findViewById((R.id.cart_decrease_button));

        }
    }
}
