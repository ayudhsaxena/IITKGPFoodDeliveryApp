package com.example.onlinefooddeliveryapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinefooddeliveryapp.Adapters.MyfoodRecyclerViewAdapter;
import com.example.onlinefooddeliveryapp.R;
import com.example.onlinefooddeliveryapp.orderrelated.Menu;
import com.example.onlinefooddeliveryapp.orderrelated.foodItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class menuFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column_count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private static Menu mMenu;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public menuFragment() {
    }


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static menuFragment newInstance(Menu m) {
        mMenu = m;
        menuFragment fragment = new menuFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);
        Log.d("menuFragment","Tried to inflate menu list view");
        RecyclerView recyclerView = view.findViewById(R.id.food_list);
        recyclerView.setAdapter(new MyfoodRecyclerViewAdapter(mMenu));
        // Set the adapter
        /*if (view instanceof RecyclerView) {
            Context context = view.getContext();
            Log.d("menuFragment","Inside if");
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                Log.d("menuFragment","set?");
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

        }*/
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(foodItem item);
    }
}
