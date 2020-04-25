package com.example.onlinefooddeliveryapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinefooddeliveryapp.R;

public class EditAccount extends AppCompatActivity {
    public EditText firstname;
    public EditText secondname;
    public EditText contact;
    public EditText email;
    public Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        spinner = findViewById(R.id.hall_spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.halls_array,R.layout.spinner_item_custom);
        spinner.setAdapter(adapter);
        Intent intent = getIntent();
        firstname = findViewById(R.id.firstNameEditText3);
        secondname = findViewById(R.id.lastNameEditText4);
        contact = findViewById(R.id.contactEditText3);
        email = findViewById(R.id.emailEditText3);
        Button save = findViewById(R.id.save_button);
        Button cancel = findViewById(R.id.Cancel_button);
        firstname.setText(intent.getStringExtra("fname"));
        secondname.setText(intent.getStringExtra("sname"));
        contact.setText(MyAccountFragment.contact.getText());
        email.setText(MyAccountFragment.mConsumerUser.getEmail());
        Log.d("EditAccount","Name of mConsumer is" + MyAccountFragment.mConsumerUser.getFirstName());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAccountDialogFragment dialog = new editAccountDialogFragment();
                dialog.show(getSupportFragmentManager(),"Alert Dialog");
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ConsumerHomePage.class);
                intent.putExtra("edit",2);
                startActivity(intent);
            }
        });


    }
}
