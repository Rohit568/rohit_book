package com.example.bookdonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    // Write a message to the database

    EditText name, email, phone_number, address, password;
    String TAG="REgister123";
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        phone_number = (EditText) findViewById(R.id.phone_number);
        address = (EditText) findViewById(R.id.address);
        password = (EditText)findViewById(R.id.pwd2);


        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.register2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String Fullname=name.getText().toString().trim();
               final  String Email=email.getText().toString().trim();
               final String Phone=phone_number.getText().toString().trim();
               final String Address=address.getText().toString().trim();
                String Password=password.getText().toString().trim();
                Toast.makeText(Register.this, "onclick", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "3createUserWithEmail:success");
                UserDetails user_data=new UserDetails(Fullname, Address, Phone, Email);
                createAccount(Email, Password,user_data);
            }
        });



}
    private void add_To_Profile(UserDetails user_data){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference myRef = database.getReference("Users").child(currentuser);
        myRef.setValue(user_data);
    }

    private void createAccount(String email, String password, final UserDetails user_data) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:success2");
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            add_To_Profile(user_data);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed."+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // [START_EXCLUDE]
                        // hideProgressBar();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }
    }





