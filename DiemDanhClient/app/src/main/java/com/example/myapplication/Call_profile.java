package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Data.model.Students;
import com.example.myapplication.Data.model.Teachers;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Call_profile extends AppCompatActivity {

    TextView btn_update, tv_fullname, tv_birthday, tv_phone, tv_address, tv_email;
    ImageView btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_call_profile);

        anhxa();
        loadData();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogUpProfile(Gravity.CENTER);
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }

    public void anhxa(){
        tv_fullname = findViewById(R.id.profilecall_txtname);
        tv_birthday = findViewById(R.id.profilecall_txtbirthday);
        tv_phone = findViewById(R.id.profilecall_txtmunber);
        tv_address = findViewById(R.id.profilecall_txtaddress);
        btn_update = findViewById(R.id.btn_callupprofile);
        tv_email = findViewById(R.id.profilecall_txtemail);
        btn_close = findViewById(R.id.btn_closecp);
    }


    public void loadData(){
        tv_fullname.setText(MainActivity.fullname);
        tv_birthday.setText(MainActivity.birthday);
        tv_address.setText(MainActivity.address);
        tv_email.setText(MainActivity.email);
        tv_phone.setText(MainActivity.phone);
    }
    public void openDialogUpProfile(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_updateprofile);

        Window window = dialog.getWindow();
        if (window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        //người dùng nhấp ra ngoài không bị văng
        /*if (Gravity.CENTER != gravity){
            dialog.setCancelable(false);
        }*/

        TextInputLayout tv_email = findViewById(R.id.txt_email);
        TextInputLayout tv_sdt = findViewById(R.id.txt_sdt);
        TextInputLayout tv_diachi = findViewById(R.id.txt_diachi);

        /*EditText et_fullname = dialog.findViewById(R.id.et_upfullname);
        EditText et_upbirthday = dialog.findViewById(R.id.et_upbirthday);
        EditText et_upphone = dialog.findViewById(R.id.et_upphone);
        EditText et_address = dialog.findViewById(R.id.et_upaddress);
        EditText et_upemail = dialog.findViewById(R.id.et_upemail);*/



        TextView btn_save = dialog.findViewById(R.id.btn_saveprofile);
        TextView btn_close = dialog.findViewById(R.id.btn_closeupprofile);

        //
        tv_email.setHint(MainActivity.email);



       /* et_fullname.setText(MainActivity.fullname);
        et_upbirthday.setText(MainActivity.birthday);
        et_address.setText(MainActivity.address);
        et_upemail.setText(MainActivity.email);
        et_upphone.setText(MainActivity.phone);*/


        //
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Put api update database to user

            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}