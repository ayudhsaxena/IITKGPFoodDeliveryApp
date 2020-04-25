package com.example.onlinefooddeliveryapp.Activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinefooddeliveryapp.Adapters.MycartRecyclerViewAdapter;
import com.example.onlinefooddeliveryapp.R;
import com.example.onlinefooddeliveryapp.orderrelated.Order;
import com.example.onlinefooddeliveryapp.orderrelated.foodItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CartFragment extends Fragment {
    public static String hallName = "";
    public static ArrayList<foodItem> cartList = new ArrayList<>();
    public static MycartRecyclerViewAdapter mAdapter;
    public static int totalCost;
    public static TextView costTextView;
    public static Button placeOrder;
    private OnFragmentInteractionListener mListener;

    public CartFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_cart, container, false);

        Context context = v.getContext();
        RecyclerView recyclerView =  v.findViewById(R.id.cartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new MycartRecyclerViewAdapter();
        recyclerView.setAdapter(mAdapter);
        refreshView(v);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
                view.startAnimation(buttonClick);
                Date date = Calendar.getInstance().getTime();
                Order o = new Order(CartFragment.hallName,MyAccountFragment.mConsumerUser.getHall(),new ArrayList<>(CartFragment.cartList),CartFragment.totalCost,date);
                Log.d("placeOrderButton","Size of items list in my placed order is "+o.getItems().size());
                MyAccountFragment.mConsumerUser.addPlacedOrder(o);
                Log.d("placeOrderButton","Size of placed orders list is "+MyAccountFragment.mConsumerUser.getPlacedOrders().size());
                MyAccountFragment.mAdapter.notifyDataSetChanged();
                changeHallCanteen();
                ConsumerHomePage.mSnackBar.show();
                mAdapter.notifyDataSetChanged();
                refreshView(v);
            }
        });


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshView(getView());
    }

    public void refreshView(View v){
        calculateTotal();
        CardView cardView = v.findViewById(R.id.cartCardId);
        RecyclerView recyclerView = v.findViewById(R.id.cartRecyclerView);
        TextView emptytextView = v.findViewById(R.id.emptyCartTextView);
        CardView cardView1 = v.findViewById(R.id.totalCostCard);
         costTextView = v.findViewById(R.id.cost_display);
         placeOrder = v.findViewById(R.id.place_order_button);
        Log.d("CartFragment","On Resume : Size of cartList is " + CartFragment.cartList.size()+" Hall name : " + CartFragment.hallName);
        if(CartFragment.cartList.isEmpty()){
            cardView1.setVisibility(View.INVISIBLE);
            costTextView.setVisibility(View.INVISIBLE);
            cardView.setVisibility(View.INVISIBLE);
            placeOrder.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
            emptytextView.setVisibility(View.VISIBLE);

        }
        else{
            placeOrder.setVisibility(View.VISIBLE);
            cardView1.setVisibility(View.VISIBLE);
            costTextView.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            emptytextView.setVisibility(View.INVISIBLE);
            TextView textView = v.findViewById(R.id.cart_hall_name);
            textView.setText(hallName);
            String cost = "₹ " + totalCost;
            costTextView.setText(cost);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static void calculateTotal(){
        int sum =0;
        for(foodItem i : CartFragment.cartList){
            sum += i.getQuantity()*i.getCost();
        }
        totalCost = sum;
    }

    public static void changeHallCanteen(){
        MenuActivity.allHalls.put(CartFragment.hallName,new MenuActivity.MyHashMap<Integer, Integer>(0));
        CartFragment.cartList.clear();
        CartFragment.hallName = "";

    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
