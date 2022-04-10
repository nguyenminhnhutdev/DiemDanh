package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.airbnb.lottie.utils.Utils;
import com.example.myapplication.Adapter.StatisticalAdapter;
import com.example.myapplication.Data.model.QuantityStudentByGroup;
import com.example.myapplication.Data.model.Studies;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticalAttended extends AppCompatActivity {
    private QuantityStudentByGroup quantityStudentByGroup;
    private SOService mService;
    TextView txt_sourceCourse, txt_Group_Statiscal,txt_Quantity_Statiscal, tt_txt_ten;
    ListView listView_Statiscal;
    StatisticalAdapter statisticalAdapter;
    ArrayList<Studies> studies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke_diemdanh);
      /*  statisticalAdapter = new StatisticalAdapter(StatisticalAttended.this, R.layout.item_dstkdd);
        mService = ApiUtils.getSOService();*/
       /* txt_Group_Statiscal = findViewById(R.id.tt_nhom);
        txt_sourceCourse = findViewById(R.id.tt_txt_mmh);
        tt_txt_ten= findViewById(R.id.tt_txt_ten);
        txt_Quantity_Statiscal =findViewById(R.id.tt_sl);
        listView_Statiscal = findViewById(R.id.listView_Statiscal);*/

    }

    /*@Override
    protected void onStart() {
        super.onStart();
        //showInformation();
    }*/

    protected void showInformation() {
        studies = new ArrayList<>();
        quantityStudentByGroup = new QuantityStudentByGroup();
        mService.getListStudentByGroup(7).enqueue(new Callback<QuantityStudentByGroup>() {
            @Override
            public void onResponse(Call<QuantityStudentByGroup> call, Response<QuantityStudentByGroup> response) {
                quantityStudentByGroup = response.body();
                studies = (ArrayList<Studies>) quantityStudentByGroup.getList();
                txt_Quantity_Statiscal.setText("SL: "+quantityStudentByGroup.getData());
                /*statisticalAdapter.addAll(studies);
                listView_Statiscal.setAdapter(statisticalAdapter);*/
            }

            @Override
            public void onFailure(Call<QuantityStudentByGroup> call, Throwable t) {

            }
        });
    }
}