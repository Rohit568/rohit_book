package com.example.bookdonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.RegionIterator;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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

import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

public class Register extends AppCompatActivity {

    // Write a message to the database

    EditText name, email, phone_number, address, password;
    String TAG="REgister123";
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    boolean isValid;


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
                isValid = checkInputs(Fullname, Email, Password,Address, Phone);

                Log.d(TAG, "3createUserWithEmail:success");

                UserDetails user_data=new UserDetails(Fullname, Address, Phone, Email, 4);
                createAccount(Email, Password,user_data);
            }
        });



}



    private void add_To_Profile(UserDetails user_data){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference myRef = database.getReference().child("Users").child(currentuser);
        myRef.setValue(user_data);
    }

    private boolean checkInputs(String email, String username, String password, String address, String phone){
        String regex2 = "[7-9]([0-9]{9})$";
        Pattern pattern2 = Pattern.compile(regex2);
        if(email.equals("") || username.equals("") || password.equals("") || address.equals("")){
            Toast.makeText(Register.this, "All fields must be filled out.", Toast.LENGTH_SHORT).show();
            return false;
        }else if(name.length()<=10)
        {
            Toast.makeText(Register.this, "Name should be a name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
           Toast.makeText(Register.this, "Enter A valid email Address", Toast.LENGTH_SHORT).show();
           return false;
        }else if(!pattern2.matches(regex2, phone)) {
            Toast.makeText(Register.this, "Enter a valid Phone Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void createAccount(String email, String password, final UserDetails user_data) {
        if (isValid) {
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
                                Toast.makeText(Register.this, "Account is successfully created", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Register.this, MainActivity.class);
                                startActivity(i);
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.d(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Register.this, "Authentication failed." + task.getException(),
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


    }





