package com.example.onlinefooddeliveryapp.Activities;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.onlinefooddeliveryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */

public class editAccountDialogFragment extends DialogFragment {
    public TextView content;
    public static final String FNAME_FIELD = "fname";
    public static final String LNAME_FIELD = "lname";
    public static final String EMAIL_FIELD = "email";
    public static final String CONTACT_FIELD = "contact";
    public static final String HALL_FIELD = "hall";
    public static CollectionReference collRef = FirebaseFirestore.getInstance().collection("users");
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.editacc_dialog_layout,null);

        builder.setView(v)
                .setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditAccount edit = (EditAccount) getContext();
                        String f = edit.firstname.getText().toString();
                        String s = edit.secondname.getText().toString();
                        String c = edit.contact.getText().toString();
                        String e = edit.email.getText().toString();
                        String h = edit.spinner.getSelectedItem().toString();
                        MyAccountFragment.mConsumerUser.setEmail(e);
                        MyAccountFragment.mConsumerUser.setFirstName(f);
                        MyAccountFragment.mConsumerUser.setSecondName(s);
                        MyAccountFragment.mConsumerUser.setMobileNo(c);
                        MyAccountFragment.mConsumerUser.setHall(h);
                        final Map<String,String> data = new HashMap<>();
                        data.put(FNAME_FIELD,f);
                        data.put(LNAME_FIELD,s);
                        data.put(EMAIL_FIELD,e);
                        data.put(CONTACT_FIELD,c);
                        data.put(HALL_FIELD,h);



                        Query fetchPass = collRef.whereEqualTo("username",MyAccountFragment.mConsumerUser.getUsername());
                        fetchPass.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    DocumentSnapshot doc= task.getResult().getDocuments().get(0);
                                    collRef.document(doc.getId()).set(data, SetOptions.merge());
                                }
                                else{
                                    Log.d("dialog","Error updating the database");
                                }
                            }
                        });
                        Intent intent = new Intent(getContext(),ConsumerHomePage.class);
                        intent.putExtra("edit",2);
                        startActivity(intent);
                    }
                }).setNegativeButton(R.string.dialog_negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        content = v.findViewById(R.id.textView4);


        return builder.create();
    }
}
