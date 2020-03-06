package com.example.bookdonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {




    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.register);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // starting background task to update product
                Intent fp=new Intent(MainActivity.this,Register.class);
                startActivity(fp);
            }
        });




    }
}
