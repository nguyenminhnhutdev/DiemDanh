package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.Adapter.LichSuSV;
import com.example.myapplication.Adapter.StatisticalAdapter;
import com.example.myapplication.Data.model.DataSessionDetailStudent;
import com.example.myapplication.Data.model.LichSuDDSV;
import com.example.myapplication.Data.model.SessionDetail;
import com.example.myapplication.Data.model.Studies;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Lichsudiemdanh_SV extends AppCompatActivity {

    TextView btn_date, txt_lsdd_gio, txt_lsdd_ngay, txt_lsdd_trangthai, txt_lsdd_tenmh;
    ListView list_lichsudiemdanh;
    private static String dateClient;
    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    LichSuSV lichSuSV;
    ArrayList<SessionDetail> sessionDetails = new ArrayList<>();
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_lichsudiemdanh_sv);
        getSupportActionBar().hide();
        lichSuSV = new LichSuSV(Lichsudiemdanh_SV.this, R.layout.item_lichsudiemdanh_sv);

       /* txt_lsdd_gio = findViewById(R.id.txt_lsdd_gio);
        txt_lsdd_ngay = findViewById(R.id.txt_lsdd_ngay);
        txt_lsdd_trangthai = findViewById(R.id.txt_lsdd_trangthai);
        txt_lsdd_tenmh = findViewById(R.id.txt_lsdd_tenmh);*/

        btn_date = findViewById(R.id.btn_lichsu_date);
        list_lichsudiemdanh =findViewById(R.id.list_lichsudiemdanh);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Lichsudiemdanh_SV.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String ngay = "";
                        String thang = "";
                        if (day < 10) {
                            ngay = "0" + day;
                        } else {
                            ngay = String.valueOf(day);
                        }
                        if (month < 10) {
                            thang = "0" + (month + 1);
                        } else {
                            thang = String.valueOf(month + 1);
                        }
                        String date = ngay + "/" + thang + "/" + year;
                        dateClient = year + "-" + thang + "-" + ngay;
                        btn_date.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        SOService mService = ApiUtils.getSOService();

        /*mService.get("2021-12-16", MainActivity.idStudent).enqueue(new Callback<LichSuDDSV>() {
            @Override
            public void onResponse(Call<LichSuDDSV> call, Response<LichSuDDSV> response) {
                if(response.body().getData()!=0){
                    sessionDetails =(ArrayList<SessionDetail>)  response.body().getList();
                    *//*lichSuSV.addAll(sessionDetails);
                    list_lichsudiemdanh.setAdapter(lichSuSV)*//*;
                }


            }

            @Override
            public void onFailure(Call<LichSuDDSV> call, Throwable t) {

            }
        });*/


    }
}