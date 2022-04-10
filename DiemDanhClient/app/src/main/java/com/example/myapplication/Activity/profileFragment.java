package com.example.myapplication.Activity;



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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Change_Password;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.checkinternet.NetworkChangeListener;

public class profileFragment extends Fragment {


    ImageView img;
    TextView txt_fullname, txt_birthday, txt_phone, txt_address, txt_email;
    TextView btn_update, btn_logout,btn_doimk;

    private SOService mService;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txt_fullname = view.findViewById(R.id.profile_txtname);
        txt_birthday = view.findViewById(R.id.profile_txtbirthday);
        txt_phone = view.findViewById(R.id.profile_txtmunber);
        txt_address = view.findViewById(R.id.profile_txtaddress);
        btn_update = view.findViewById(R.id.btn_callupdateprofile);
        txt_email = view.findViewById(R.id.profile_txtemail);
        btn_logout = view.findViewById(R.id.btn_logout);
        btn_doimk = view.findViewById(R.id.btn_doimk);

        loadData();

        btn_doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Change_Password.class);
                startActivity(intent);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUpProfile(Gravity.CENTER);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        return view;
    }

    public void loadData(){
        txt_fullname.setText(MainActivity.fullname);
        txt_birthday.setText(MainActivity.birthday);
        txt_address.setText(MainActivity.address);
        txt_email.setText(MainActivity.email);
        txt_phone.setText(MainActivity.phone);
    }

    public void openDialogUpProfile(int gravity){
        final Dialog dialog = new Dialog(getContext());
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
        if (Gravity.CENTER != gravity){
            dialog.setCancelable(false);
        }

       /* EditText et_fullname = dialog.findViewById(R.id.et_upfullname);
        EditText et_upbirthday = dialog.findViewById(R.id.et_upbirthday);
        EditText et_upphone = dialog.findViewById(R.id.et_upphone);
        EditText et_address = dialog.findViewById(R.id.et_upaddress);
        EditText et_upemail = dialog.findViewById(R.id.et_upemail);*/

        TextView btn_save = dialog.findViewById(R.id.btn_saveprofile);
        TextView btn_close = dialog.findViewById(R.id.btn_closeupprofile);

       /* //
        et_fullname.setText(MainActivity.fullname);
        et_upbirthday.setText(MainActivity.birthday);
        et_address.setText(MainActivity.address);
        et_upemail.setText(MainActivity.email);
        et_upphone.setText(MainActivity.phone);
*/
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