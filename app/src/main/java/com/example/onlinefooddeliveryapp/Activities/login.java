package com.example.onlinefooddeliveryapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.onlinefooddeliveryapp.R;
import com.example.onlinefooddeliveryapp.users.Consumer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class login extends AppCompatActivity {

    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";
    public static final String TAG = "Login";
    public static final String FNAME = "fname";
    public static final String LNAME = "lname";
    public static final String MOBILE = "mobile";
    public static final String HALL = "hall";
    public static final String EMAIL = "email";
    public static CollectionReference collRef = FirebaseFirestore.getInstance().collection("users");
    public static Snackbar mSnackBar,mSnackBar2;

    public void userLogin(final View view){

        EditText unameText =  findViewById(R.id.unameTextId);
        EditText pwdText =  findViewById(R.id.pwdTextId);
        final TextView errorText = findViewById(R.id.errorTextView);
        errorText.setText(null);

        String uname = unameText.getText().toString();
        final String pwd = pwdText.getText().toString();



        Query fetchPass = collRef.whereEqualTo(USERNAME,uname);
        fetchPass.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().isEmpty()){
                        errorText.setText(R.string.missingUsername);
                    }
                    else{
                        DocumentSnapshot doc= task.getResult().getDocuments().get(0);
                        if(doc.get(PASSWORD).equals(pwd)){
                            Log.d(TAG,"Successful Login!");
                            HashMap<String,String> m = new HashMap<String, String>();
                            Log.d(TAG,doc.getString(USERNAME));
                            m.put(USERNAME,doc.getString(USERNAME));
                            m.put(FNAME,doc.getString(FNAME));
                            m.put(LNAME,doc.getString(LNAME));
                            m.put(EMAIL,doc.getString(EMAIL));
                            m.put(MOBILE,doc.getString("contact"));
                            m.put(HALL,doc.getString(HALL));
                            Consumer user = new Consumer(m);
                            MyAccountFragment.mConsumerUser = user;
                            Intent intent = new Intent(view.getContext(),ConsumerHomePage.class);
                            startActivity(intent);
                        }
                        else{
                            errorText.setText(R.string.incorrectPassword);
                        }
                    }
                }
            }
        });


    }

    public void initiateSignUp(View view){
        Log.d("go","MOve!!");
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String s = "Logged out successfully!";
        String s2 = "Account created successfully!";
        setContentView(R.layout.activity_login);
        CoordinatorLayout layout = findViewById(R.id.loginCoordinatorLayout);
        mSnackBar = Snackbar.make(layout,s, BaseTransientBottomBar.LENGTH_LONG);
        mSnackBar2 = Snackbar.make(layout,s2,BaseTransientBottomBar.LENGTH_LONG);
        Intent intent = getIntent();
        if(intent.getStringExtra("snackbar") != null)
            mSnackBar.show();
        if(intent.getStringExtra("from") != null)
            mSnackBar2.show();

    }
}
