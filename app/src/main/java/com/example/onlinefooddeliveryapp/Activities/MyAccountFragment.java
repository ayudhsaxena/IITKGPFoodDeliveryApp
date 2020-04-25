package com.example.onlinefooddeliveryapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinefooddeliveryapp.Adapters.MypastOrderRecyclerViewAdapter;
import com.example.onlinefooddeliveryapp.R;
import com.example.onlinefooddeliveryapp.users.Consumer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyAccountFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAccountFragment extends Fragment {

    public static Consumer mConsumerUser;
    public static MypastOrderRecyclerViewAdapter mAdapter;
    public static MypastOrderRecyclerViewAdapter orderDetailsAdapter;
    public TextView fullname;
    public static TextView contact;
    public static TextView email;
    public TextView hall;
    public TextView username;
    private OnFragmentInteractionListener mListener;

    public MyAccountFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MyAccountFragment newInstance() {
        MyAccountFragment fragment = new MyAccountFragment();

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
        View v = inflater.inflate(R.layout.fragment_my_account, container, false);
        fullname = v.findViewById(R.id.fullNameTextView);
        contact = v.findViewById(R.id.account_contact);
        email = v.findViewById(R.id.account_email);
        hall = v.findViewById(R.id.account_hall_name);
        username = v.findViewById(R.id.account_var_username);
        fullname.setText(mConsumerUser.getFirstName()+" "+mConsumerUser.getSecondName());
        contact.setText(mConsumerUser.getMobileNo());
        email.setText("|  "+mConsumerUser.getEmail());
        hall.setText(mConsumerUser.getHall());
        username.setText(mConsumerUser.getUsername());
        CardView cv = v.getRootView().findViewById(R.id.orderDetailsCardView);
        cv.setVisibility(View.GONE);
        Context context = v.getContext();
        RecyclerView recyclerView =  v.findViewById(R.id.pastOrderRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new MypastOrderRecyclerViewAdapter();
        recyclerView.setAdapter(mAdapter);
        Button back_button = v.findViewById(R.id.back_order_details_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardView cardView = view.getRootView().findViewById(R.id.orderDetailsCardView);
                cardView.setVisibility(View.GONE);
                RecyclerView recyclerView1 = view.getRootView().findViewById(R.id.pastOrderRecyclerView);
                recyclerView1.setVisibility(View.VISIBLE);
            }
        });

        Button editAccount = v.findViewById(R.id.edit_account_button);
        editAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),EditAccount.class);
                intent.putExtra("fname",mConsumerUser.getFirstName());
                intent.putExtra("sname",mConsumerUser.getSecondName());
                startActivity(intent);
            }
        });

        Button logout = v.findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),login.class);
                intent.putExtra("snackbar","display");
                startActivity(intent);
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
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
