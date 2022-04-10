package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.Adapter.StatisticalAdapter;
import com.example.myapplication.Data.model.QuantitySession;
import com.example.myapplication.Data.model.QuantityStudentByGroup;
import com.example.myapplication.Data.model.Session;
import com.example.myapplication.Data.model.Students;
import com.example.myapplication.Data.model.Studies;
import com.example.myapplication.Data.model.ThongkeDiemDanh;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Thongke_Diemdanh extends AppCompatActivity {

    public  static  int quantitySession;
    public  static   int  idGroup;
    private QuantityStudentByGroup quantityStudentByGroup;
    private SOService mService;
    TextView txt_sourceCourse, txt_Group_Statiscal,txt_Quantity_Statiscal, tt_txt_ten;
    ListView listView_Statiscal;
    StatisticalAdapter statisticalAdapter;
    ArrayList<Studies> studies;
    private  ArrayList<ThongkeDiemDanh> thongkeDiemDanhs = new ArrayList<>();
    ArrayList<String> lsStudent= new ArrayList<>();
    public static ArrayList<Integer> lsQuantity;
    ArrayList<QuantitySession> quantitySessionArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke_diemdanh);
        getSupportActionBar().hide();
        statisticalAdapter = new StatisticalAdapter(Thongke_Diemdanh.this, R.layout.item_dstkdd);
        mService = ApiUtils.getSOService();
        txt_Group_Statiscal = findViewById(R.id.txt_nhom);
        txt_sourceCourse = findViewById(R.id.tt_txt_mmh);
        tt_txt_ten= findViewById(R.id.tt_txt_ten);
        txt_Quantity_Statiscal =findViewById(R.id.tt_sl);
        listView_Statiscal = findViewById(R.id.list_tkdsdd);
        ImageView btn_backtkdd = findViewById(R.id.btn_backtkdd);
        btn_backtkdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        showInformation();

       // tt_txt_ten.setText(lsQuantity.get(0).toString());


    }

    protected void showInformation() {
        studies = new ArrayList<>();
        Intent intent = getIntent();
        idGroup= intent.getIntExtra("idGroup", 0);
        quantityStudentByGroup = new QuantityStudentByGroup();
        mService.getListStudentByGroup(idGroup).enqueue(new Callback<QuantityStudentByGroup>() {
            @Override
            public void onResponse(Call<QuantityStudentByGroup> call, Response<QuantityStudentByGroup> response) {
                quantityStudentByGroup = response.body();

                studies = (ArrayList<Studies>) quantityStudentByGroup.getList();

                txt_Quantity_Statiscal.setText("SL: "+quantityStudentByGroup.getData());
                txt_sourceCourse.setText(intent.getStringExtra("idCourse"));
                tt_txt_ten.setText(intent.getStringExtra("nameCourse"));
                txt_Group_Statiscal.setText(String.valueOf(idGroup));
                for (Studies studies: studies){
                    mService.getListSessionById(idGroup, studies.getIdstudent()).enqueue(new Callback<QuantitySession>() {
                        @Override
                        public void onResponse(Call<QuantitySession> call, Response<QuantitySession> response) {
                            quantitySession = response.body().getQuantitySession();
                            ThongkeDiemDanh thongke_diemdanh= new ThongkeDiemDanh(studies.getIdstudent(), studies.getIdstudentNavigation().getFullName(),studies.getIdstudentNavigation().getClass_(), response.body().getYes(),quantitySession);
                            thongkeDiemDanhs.add(thongke_diemdanh);
                            statisticalAdapter.add(thongke_diemdanh);
                            statisticalAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onFailure(Call<QuantitySession> call, Throwable t) {

                        }
                    });
                }
                listView_Statiscal.setAdapter(statisticalAdapter);
            }

            @Override
            public void onFailure(Call<QuantityStudentByGroup> call, Throwable t) {

            }
        });



    }

}