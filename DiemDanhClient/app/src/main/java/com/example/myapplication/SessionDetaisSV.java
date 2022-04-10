package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Data.model.GroupSubject;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.checkinternet.NetworkChangeListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionDetaisSV extends AppCompatActivity {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    public static int idSession, session, idGroup;
    private String idStudent;
    private SOService mService;
    ImageView btn_close;
    TextView btn_diemdanh, txt_tenmh, txt_tietbd, txt_tiet, txt_mamh,
            txt_thu, txt_maphong, txt_ngayhoc, txt_lop, txt_hocky, txt_tengv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_session_details_sv);
        mService = ApiUtils.getSOService();//Tao doi tuong interface
        txt_tenmh = findViewById(R.id.txt_tenmonhoc);
        txt_tietbd = findViewById(R.id.txt_tietbatdau);
        txt_tiet = findViewById(R.id.txt_tiet);
        txt_thu = findViewById(R.id.txt_thu);
        txt_maphong = findViewById(R.id.txt_maphong);
        txt_ngayhoc = findViewById(R.id.txt_ngayhoc);
        txt_lop = findViewById(R.id.txt_lop);
        txt_hocky = findViewById(R.id.txt_hocky);
       // txt_mamh = findViewById(R.id.txt_mamh);
        txt_tengv = findViewById(R.id.txt_tengv);
        btn_diemdanh = findViewById(R.id.btn_diemdanhSV);
        btn_close = findViewById(R.id.btn_close1);
        upDuLieuCT();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_diemdanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SessionDetaisSV.this, attendance_sv.class);
                startActivity(intent);
            }
        });


    }
    public void upDuLieuCT() {
        Intent intent = getIntent();
        idGroup = intent.getIntExtra("idGroup", 0);
        mService.getGroupById(idGroup).enqueue(new Callback<GroupSubject>() {
            @Override
            public void onResponse(Call<GroupSubject> call, Response<GroupSubject> response) {
                GroupSubject gr= response.body();
                idSession= intent.getIntExtra("idSession",0);
                session = intent.getIntExtra("session1", 0);
                idStudent = MainActivity.idStudent;
                txt_tenmh.setText("Tên môn: " + gr.getIdcourseNavigation().getCoursetName());
                //txt_mamh.setText("Mã môn học: " + gr.getIdcourseNavigation().getIdcourse());
                txt_tietbd.setText("Tiết bắt đầu: " + String.valueOf(intent.getIntExtra("periodStart", 0)));
                txt_tiet.setText("Tiết: " + String.valueOf(intent.getIntExtra("periodStart", 0) + " - " +
                        String.valueOf(intent.getIntExtra("periodEnd", 0))));
                txt_thu.setText("Thứ: "+ intent.getStringExtra("day").replace("Thứ ",""));
                txt_maphong.setText("Mã phòng học: " + intent.getStringExtra("classRoom"));


                String date = intent.getStringExtra("date").replace("T00:00:00", "");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date dates = (Date)formatter.parse(date);
                    SimpleDateFormat newFormat = new SimpleDateFormat("MM-dd-yyyy");
                    String finalString = newFormat.format(dates);
                    txt_ngayhoc.setText("Ngày: " + finalString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                txt_lop.setText("Lớp : " + intent.getStringExtra("class"));
                txt_hocky.setText("Học kỳ: " + String.valueOf(intent.getIntExtra("semester", 0)));
            }

            @Override
            public void onFailure(Call<GroupSubject> call, Throwable t) {

            }
        });



    }


    @Override
    protected void onStart(){
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected  void onStop(){
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}