package com.example.onlinefooddeliveryapp.Activities;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.onlinefooddeliveryapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class cartDialogFragment extends DialogFragment {
    public TextView content;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.cart_dialog_layout,null);
        builder.setView(v)
                .setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CartFragment.changeHallCanteen();
                        dismiss();
                    }
                }).setNegativeButton(R.string.dialog_negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getContext(), ConsumerHomePage.class);
                startActivity(intent);
            }
        });

        content = v.findViewById(R.id.textView4);

        return builder.create();
    }
}
