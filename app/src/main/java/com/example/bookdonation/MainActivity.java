package com.example.bookdonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {




    private Button btn_logout;
    private Button GotoExtra;
    private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            btn_logout=findViewById(R.id.logout);
            GotoExtra = findViewById(R.id.extra);
            mAuth=FirebaseAuth.getInstance();
            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signOut();
                    Toast.makeText(MainActivity.this,"Successfully Logged out", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MainActivity.this,Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });

            GotoExtra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, UploadBook.class);
                    startActivity(intent);
                }
            });
        }
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        rbutton = (Button) findViewById(R.id.register);
//       lbutton = (Button) findViewById(R.id.login);
//        sbutton = (Button) findViewById(R.id.extra);
//
//        lbutton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v)
//            {
//                // starting background task to update product
//                Intent ln=new Intent(MainActivity.this,Login.class);
//                startActivity(ln);
//            }
//        });

//
//        rbutton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v)
//            {
//                // starting background task to update product
//                Intent fp=new Intent(MainActivity.this,Register.class);
//                startActivity(fp);
//            }
//        });
//
//        sbutton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v)
//            {
//                // starting background task to update product
//                Intent fp=new Intent(MainActivity.this,Upload_Book.class);
//                startActivity(fp);
//            }
//        });




    }

