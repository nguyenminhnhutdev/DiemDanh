package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.utils.Utils;
import com.example.myapplication.Data.model.GroupSubject;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class show_ds extends AppCompatActivity {

    private SOService mService;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ds);
        getSupportActionBar().hide();
        mService = ApiUtils.getSOService();//Tao doi tuong interface
        TextView txtNhom = findViewById(R.id.txt_groupsuject);
        TextView btnLink = findViewById(R.id.btn_link_sheet);
        Intent intent = getIntent();
        int idGroup = intent.getIntExtra("idGroup", 0);
        mService.getGroupById(idGroup).enqueue(new Callback<GroupSubject>() {
            @Override
            public void onResponse(Call<GroupSubject> call, Response<GroupSubject> response) {
                GroupSubject groupSubject = response.body();
                url = groupSubject.getLinkds();
                txtNhom.setText("Danh sách sinh viên thuộc nhóm: " + intent.getIntExtra("idGroup", 0));
                btnLink.setText("Link gooogle sheet: "+ url);
            }
            @Override
            public void onFailure(Call<GroupSubject> call, Throwable t) {
            }
        });


        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL(url);
            }
        });

    }

    private void gotoURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}