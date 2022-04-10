package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class test_thongtinchitiet extends AppCompatActivity {

    ImageView btn_close;
    TextView tv_fullname, tv_birthday, tv_address, tv_phone, tv_email,tv_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_test_thongtinchitiet);

        anhxa();
        loadingdata();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void anhxa(){
        btn_close = findViewById(R.id.btn_closettct);
        tv_username = findViewById(R.id.tv_username);
        tv_fullname = findViewById(R.id.tv_name);
        tv_birthday = findViewById(R.id.tv_birthday);
        tv_phone = findViewById(R.id.tv_phone);
        tv_address = findViewById(R.id.tv_address);
        tv_email = findViewById(R.id.tv_email);
    }
    public void loadingdata(){
        tv_username.setText("Tài khoản_"+MainActivity.usernametk);
        tv_fullname.setText(MainActivity.fullname);
        tv_birthday.setText(MainActivity.birthday);
        tv_phone.setText(MainActivity.phone);
        tv_address.setText(MainActivity.address);
        tv_email.setText(MainActivity.email);
    }
}