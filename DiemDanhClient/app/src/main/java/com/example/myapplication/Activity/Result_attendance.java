package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.Adapter.ResultAdapter;
import com.example.myapplication.Adapter.SessionAdapter;
import com.example.myapplication.Data.model.Session;
import com.example.myapplication.Data.model.SessionDetail;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.R;
import com.example.myapplication.SessionDetailsGV;
import com.example.myapplication.weekly_calendar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Result_attendance extends AppCompatActivity {

    ResultAdapter resultAdapter;
    private SOService mService;
    ImageView btn_close;
    ListView lv_Result;
    ArrayList<SessionDetail> lsResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_result_attendance);

        resultAdapter = new ResultAdapter(Result_attendance.this, R.layout.item_attendance_list_students);
        mService = ApiUtils.getSOService();//Tao doi tuong interface

        btn_close = findViewById(R.id.btn_closedsdd);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        lv_Result = findViewById(R.id.lv_result);
        callApi();

    }
    protected  void callApi(){
        lsResult = new ArrayList<>();
        mService.getLessionDetail(SessionDetailsGV.idlession).enqueue(new Callback<List<SessionDetail>>() {
            @Override
            public void onResponse(Call<List<SessionDetail>> call, Response<List<SessionDetail>> response) {
                lsResult= (ArrayList<SessionDetail>) response.body();
                resultAdapter.addAll(lsResult);
                lv_Result.setAdapter(resultAdapter);
            }

            @Override
            public void onFailure(Call<List<SessionDetail>> call, Throwable t) {

            }
        });


    }
}