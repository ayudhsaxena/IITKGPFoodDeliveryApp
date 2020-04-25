package com.example.onlinefooddeliveryapp.Adapters;

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
import com.example.onlinefooddeliveryapp.Activities.MenuActivity;
import com.example.onlinefooddeliveryapp.Activities.cartDialogFragment;
import com.example.onlinefooddeliveryapp.Activities.dummy.DummyContent.DummyItem;
import com.example.onlinefooddeliveryapp.Activities.menuFragment.OnListFragmentInteractionListener;
import com.example.onlinefooddeliveryapp.R;
import com.example.onlinefooddeliveryapp.orderrelated.Menu;
import com.example.onlinefooddeliveryapp.orderrelated.foodItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyfoodRecyclerViewAdapter extends RecyclerView.Adapter<MyfoodRecyclerViewAdapter.ViewHolder> {

    private  Menu mMenu;


    public MyfoodRecyclerViewAdapter(Menu menu) {
        mMenu = menu;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mMenu.getItem(position);
        Log.d("adapter",""+holder.mItem.getItemName());
        holder.mNameView.setText(holder.mItem.getItemName());
        String cost = "₹ " + String.valueOf(holder.mItem.getCost());
        holder.mCostView.setText(cost);
        switch (holder.mItem.getLabel()){
            case "N" : holder.mImageView.setImageResource(R.drawable.nonveg);
                       break;
            case "V" : holder.mImageView.setImageResource(R.drawable.veg);
                break;
            case "E" : holder.mImageView.setImageResource(R.drawable.egg);
                break;
        }

        String qty = "" + MenuActivity.allHalls.get(mMenu.getHallName()).get(position);
        holder.mQuantityView.setText(qty);
        Log.d("onBind",holder.mQuantityView.getText().toString());
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
                v.startAnimation(buttonClick);
                TextView mEditText = holder.mQuantityView;
                if(CartFragment.hallName.equals("") || CartFragment.hallName.equals(mMenu.getHallName())) {
                    CartFragment.hallName = mMenu.getHallName();
                    increaseButton(holder.mItem,mEditText);
                }
                else{
                    cartDialogFragment dialog = new cartDialogFragment();
                    dialog.show(MenuActivity.mFragmentManager,"Alert Dialog");
                }


            }
        });

        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
                v.startAnimation(buttonClick);
                TextView mEditText = holder.mQuantityView;
                int qty = Integer.valueOf(mEditText.getText().toString());
                if(qty > 0) {
                    qty--;
                    if(qty == 0){
                        for(foodItem item : CartFragment.cartList){
                            if(item.getId() == holder.mItem.getId()){
                                CartFragment.cartList.remove(item);
                                break;
                            }
                        }

                    }
                    else{
                        for (int i = 0; i < CartFragment.cartList.size(); i++) {
                            if(CartFragment.cartList.get(i).getId() == holder.mItem.getId()){
                                CartFragment.cartList.get(i).setQuantity(qty);
                                break;
                            }
                        }
                    }
                }
                mEditText.setText(String.valueOf(qty));
                if(CartFragment.cartList.isEmpty())
                    CartFragment.hallName = "";
                MenuActivity.allHalls.get(mMenu.getHallName()).put(holder.mItem.getId(),qty);
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
        return mMenu.getSize();
    }

    public void increaseButton(foodItem mItem,TextView mEditText){
        int qty = Integer.valueOf(mEditText.getText().toString());
        qty++;
        mEditText.setText(String.valueOf(qty));
        if(qty == 1){
            mItem.setQuantity(qty);
            CartFragment.cartList.add(mItem);
        }
        else{
            for (int i = 0; i < CartFragment.cartList.size(); i++) {
                if(CartFragment.cartList.get(i).getId() == mItem.getId()){
                    CartFragment.cartList.get(i).setQuantity(qty);
                    break;
                }
            }
        }
        MenuActivity.allHalls.get(mMenu.getHallName()).put(mItem.getId(),qty);
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
            mNameView = (TextView) view.findViewById(R.id.food_name);
            mImageView = view.findViewById(R.id.food_label);
            mCostView = view.findViewById(R.id.cost);
            mQuantityView = view.findViewById(R.id.quantity);
            increase = view.findViewById(R.id.increase_button);
            decrease = view.findViewById((R.id.decrease_button));

        }



    }
}
