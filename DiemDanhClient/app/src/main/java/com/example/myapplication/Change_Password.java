package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

public class Change_Password extends AppCompatActivity {

    TextInputLayout txt_username, txt_pass, txt_newpass, txt_anewpass;
    ImageView btn_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_password);

        anhxa();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void anhxa(){
        txt_username = findViewById(R.id.til_username);
        txt_pass = findViewById(R.id.til_password);
        txt_newpass = findViewById(R.id.til_mkm);
        txt_anewpass = findViewById(R.id.til_nmkm);
        btn_close = findViewById(R.id.btn_closedmk);
    }
}