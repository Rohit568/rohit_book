package com.example.bookdonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
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
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private RecyclerView mRecyclerVeiw;
    private ImageAdapter mAdapter;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataref;
    private List<Upload> mUploads;

    String phoneNo;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mRecyclerVeiw = findViewById(R.id.recycler);
        mRecyclerVeiw.setHasFixedSize(true);
        mRecyclerVeiw.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        mDataref = FirebaseDatabase.getInstance().getReference("images");
        phoneNo = "7276688141";
        message ="Hey! I really need this book, just answer me";
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
                    Log.d("rohi",u.getPhone_No()+"");
                   // phoneNo = u.getPhone_No();
                    message = "I need your book named  "+u.getName()+" !!Please just contact me.. ";
                    Log.d("rohit1", phoneNo+"");
                }

                mAdapter = new ImageAdapter(MainActivity.this, mUploads);

                mRecyclerVeiw.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener(){

                    @Override
                    public void onItemClick(int position) {

                            try{
                                SmsManager smgr = SmsManager.getDefault();
                                smgr.sendTextMessage(phoneNo,null, message ,null,null);
                                Toast.makeText(MainActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                            }
                            catch (Exception e){
                                Toast.makeText(MainActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                            }
                        Log.d("rr","rohit"+position);
                    }
                });


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

