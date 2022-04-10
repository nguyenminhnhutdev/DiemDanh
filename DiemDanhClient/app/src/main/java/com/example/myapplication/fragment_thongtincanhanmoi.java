package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class fragment_thongtincanhanmoi extends Fragment {

    TextView btn_doimk, btn_logout, btn_ttcn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_thongtincanhanmoi, container, false);

        btn_doimk = (TextView) view.findViewById(R.id.btn_doimk_ttcn);
        btn_logout = (TextView) view.findViewById(R.id.btn_logout_ttcn);
        btn_ttcn = (TextView) view.findViewById(R.id.btn_ttcn);

        btn_ttcn.setText("Tài khoản " + "(" + MainActivity.usernametk + ")");

        btn_ttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), test_thongtinchitiet.class);
                startActivity(intent);
            }
        });



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        btn_doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogUpProfile(Gravity.CENTER);
            }
        });
        return view;
    }

    public void openDialogUpProfile(int gravity){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_doimk);

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
        if (Gravity.CENTER != gravity){
            dialog.setCancelable(false);
        }

        TextInputLayout username = dialog.findViewById(R.id.edit_dialog_tk);
        TextInputLayout password = dialog.findViewById(R.id.edit_dialog_mkc);
        TextInputLayout newpassword = dialog.findViewById(R.id.edit_dialog_mkm);


        TextView btn_save = dialog.findViewById(R.id.btn_dialog_doimk);
        ImageView btn_close = dialog.findViewById(R.id.btn_dialog_close);

        //lấy dữ liệu nhập
        String username1 = username.getEditText().getText().toString().trim();
        String password1 = password.getEditText().getText().toString().trim();
        String newpassword1 = newpassword.getEditText().getText().toString().trim();


        //
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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