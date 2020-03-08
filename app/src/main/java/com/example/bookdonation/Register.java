package com.example.bookdonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;

public class Register extends AppCompatActivity{

    private Button rbutton;

    private EditText name, address, phonenumber, email, password;

    private ProgressDialog loadingBar;

    private FirebaseAuth firebaseAuth;


    // Write a message to the database

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Write a message to the database


        firebaseAuth = FirebaseAuth.getInstance();
        rbutton = (Button)findViewById(R.id.register);


        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        phonenumber = (EditText) findViewById(R.id.phone_number);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        loadingBar = new ProgressDialog(this);




       rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });



    }

    private void createAccount(){
        String fname = name.getText().toString().trim();
        String faddress = address.getText().toString().trim();
        String fphone  = phonenumber.getText().toString().trim();
        String femail = email.getText().toString().trim();
        String fpassword = password.getText().toString().trim();



        if(TextUtils.isEmpty(fname)){
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT);
            return;
        }

        else if(TextUtils.isEmpty(faddress)){
            Toast.makeText(this, "Please write your address...", Toast.LENGTH_SHORT);
            return;
        }

        else if(TextUtils.isEmpty(fphone)){
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT);
            return;
        }

       else if(TextUtils.isEmpty(femail)){
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT);
            return;
        }

       else if(TextUtils.isEmpty(fpassword)){
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT);
            return;
        }


       else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait while we checking the credential...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validatePhoneNumber(fname, fphone, fpassword, femail, faddress);
        }
    }

    private void validatePhoneNumber(final String aname, final String aphone, final String apassword, final String aemail,final String aaddress) {

          final DatabaseReference rootRef;

          rootRef = FirebaseDatabase.getInstance().getReference();

          rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  if(!dataSnapshot.child("u" +
                          "users").child(aphone).exists())
                  {
                        HashMap<String, Object> userdata = new HashMap<String, Object>();
                        userdata.put("Name", aname);
                        userdata.put("Address", aaddress);
                        userdata.put("Phone_number", aphone);
                        userdata.put("Email", aemail);
                        userdata.put("Password",apassword);
                        userdata.put("Credit", "4");

                        rootRef.child("users").child(aphone).updateChildren(userdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                 if(task.isSuccessful())
                                 {
                                     Toast.makeText(Register.this, "Congratulations, your account has created", Toast.LENGTH_SHORT).show();
                                     loadingBar.dismiss();
                                     Intent s = new Intent(Register.this, MainActivity.class);
                                     startActivity(s);
                                 }
                                 else
                                 {
                                     Toast.makeText(Register.this, "Network error!", Toast.LENGTH_SHORT).show();
                                 }
                            }
                        });
                  }
                  else
                  {
                      Toast.makeText(Register.this, "this "+aphone+" already exists", Toast.LENGTH_SHORT).show();
                      loadingBar.dismiss();
                       Toast.makeText(Register.this, "Please try again using another phone number",Toast.LENGTH_SHORT).show();

                      Intent s=new Intent(Register.this,MainActivity.class);
                      startActivity(s);
                  }
              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {
                  Toast.makeText(Register.this, "Something is wrong",Toast.LENGTH_LONG ).show();
              }
          });
    }
}




