package com.example.bookdonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {




    private Button rbutton, lbutton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rbutton = (Button) findViewById(R.id.register);
        lbutton = (Button) findViewById(R.id.login);

        lbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // starting background task to update product
                Intent ln=new Intent(MainActivity.this,Login.class);
                startActivity(ln);
            }
        });


        rbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // starting background task to update product
                Intent fp=new Intent(MainActivity.this,Register.class);
                startActivity(fp);
            }
        });




    }
}
