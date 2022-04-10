package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Forgot_Password extends AppCompatActivity {

    ImageView btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(Forgot_Password.this, R.color.Black));
        setContentView(R.layout.activity_forgot_password);

        anhxa();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void anhxa(){
        btn_close = findViewById(R.id.btn_closeqmk);
    }
}