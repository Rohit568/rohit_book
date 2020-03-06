package com.example.bookdonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {



    private Button rbutton, lbutton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        rbutton = (Button)findViewById(R.id.register);
//        lbutton = (Button)findViewById(R.id.login);
//
//        rbutton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v)
//            {
//                // starting background task to update product
//                Intent fp=new Intent(Login.this,Register.class);
//                startActivity(fp);
//            }
//        });
//
//        lbutton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v)
//            {
//                // starting background task to update product
//                Intent fp=new Intent(Login.this,MainActivity.class);
//                startActivity(fp);
//            }
//        });
    }
}
