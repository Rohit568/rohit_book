package com.example.bookdonation;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.HashMap;
import java.util.Map;

public class MyProfile extends AppCompatActivity {

    EditText t1, t2, t3, t4, t5;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        getSupportActionBar().setTitle("Profile");

        t1 = findViewById(R.id.ename);
        t2 = findViewById(R.id.eemail);
        t3 = findViewById(R.id.emob);
        t4 = findViewById(R.id.eaddress);
        t5 = findViewById(R.id.credit);


        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        dbref.addValueEventListener(new ValueEventListener() {
            UserDetails pd = null;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pd = dataSnapshot.getValue(UserDetails.class);
                updateUi(pd);
                disableAll();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void disableAll(){
        t1.setEnabled(false);
        t2.setEnabled(false);
        t3.setEnabled(false);
        t4.setEnabled(false);
        t5.setEnabled(false);
    }


    private void updateUi(UserDetails pd) {

        t1.setText(pd.getName());
        t2.setText(pd.getEmail_id());
        t3.setText(pd.getPhone_number());
        t4.setText(pd.getAddress());
        t5.setText(pd.getCredit());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logoutmenu:
                mAuth.signOut();
                Toast.makeText(MyProfile.this, "Successfully Logged out", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MyProfile.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
            case R.id.uploadmenu:
                Intent it = new Intent(MyProfile.this, UploadBook.class);
                startActivity(it);
                return true;


            default:
                return super.onOptionsItemSelected(item);


        }

    }

}
