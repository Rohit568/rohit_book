package com.example.bookdonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerVeiw;
    private ImageAdapter mAdapter;
    private FirebaseAuth mAuth;

    private DatabaseReference mDataref;
    private List<Upload> mUploads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Log.d("rohitsachin","check");
        mAuth = FirebaseAuth.getInstance();
        mRecyclerVeiw = findViewById(R.id.recycler);
        mRecyclerVeiw.setHasFixedSize(true);
        mRecyclerVeiw.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        mDataref = FirebaseDatabase.getInstance().getReference("images");

        mDataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Upload upload = postSnapshot.getValue(Upload.class);
                    Log.d("rohitsachin", postSnapshot.getKey() + "rr2");
                    mUploads.add(upload);
                }
                Log.d("rohitsachin","check2");
                for(Upload u:mUploads){
                    Log.d("rohitsachin",u.getImageUrl()+" "+u.getImageUrl());
                    System.out.println(u.getImageUrl()+" "+u.getImageUrl());
                }

                mAdapter = new ImageAdapter(MainActivity.this, mUploads);

                mRecyclerVeiw.setAdapter(mAdapter);

                mRecyclerVeiw.setAdapter(mAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), DatabaseError.UNKNOWN_ERROR, Toast.LENGTH_SHORT).show();
            }
        });


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
                Toast.makeText(MainActivity.this, "Successfully Logged out", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
            case R.id.uploadmenu:
                Intent it = new Intent(MainActivity.this, UploadBook.class);
                startActivity(it);
                return true;
            case R.id.profilemenu:
                Intent t = new Intent(MainActivity.this, MyProfile.class);
                startActivity(t);


            default:
                return super.onOptionsItemSelected(item);


        }

    }
}

