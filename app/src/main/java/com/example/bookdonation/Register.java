package com.example.bookdonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {

    private Button rbutton, lbutton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rbutton = (Button)findViewById(R.id.register);
        lbutton = (Button)findViewById(R.id.login);

        rbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // starting background task to update product
                Intent i=new Intent(Register.this,MainActivity.class);
                startActivity(i);
            }
        });

        lbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // starting background task to update product
                Intent j=new Intent(Register.this,Login.class);
                startActivity(j);
            }
        });
    }
    }


