package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.HistorySVKVangAdapter;
import com.example.myapplication.Adapter.HistorySVVangAdapter;
import com.example.myapplication.Data.model.DataSessionDetailStudent;
import com.example.myapplication.Data.model.GroupSubject;
import com.example.myapplication.Data.model.QuantityStudentByGroup;
import com.example.myapplication.Data.model.Session;
import com.example.myapplication.Data.model.SessionDetailStudent;
import com.example.myapplication.Data.model.Studies;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.R;
import com.example.myapplication.SessionDetailsGV;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceHistoryTeacher extends AppCompatActivity {

    HistorySVKVangAdapter historyAdapter;
    HistorySVVangAdapter historySVVangAdapter;
    private TextView txtGroup, txtCourse, txtNameTeacher, txtQuantityStudent, txt_idCourse, txt_Quantity_Student_Absent, txt_sl_sinhviencomat;
    ListView listViewHistory, list_view_SinhVienKVang;

    SessionDetailStudent sessionDetailStudents;
    ArrayList<DataSessionDetailStudent> dataSessionDetailStudents, dataSessionDetailStudents1;
    private SOService mService;
    private int group, quantityAbsent, quantityAttended;
    private GroupSubject groupSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsudiemdanh_gv);
        getSupportActionBar().hide();
        historyAdapter = new HistorySVKVangAdapter(AttendanceHistoryTeacher.this, R.layout.item_ds_diemdanh_comat);
        historySVVangAdapter = new HistorySVVangAdapter(AttendanceHistoryTeacher.this, R.layout.item_ds_diemdanh_vangmat);
        mService = ApiUtils.getSOService();
        ImageView btnback = findViewById(R.id.btnback);
        txtGroup = findViewById(R.id.lsdd_tv_nhom);
        txtCourse = findViewById(R.id.lsdd_tv_tmh);
        txtQuantityStudent = findViewById(R.id.txt_slsinhviengroup);
        txt_Quantity_Student_Absent = findViewById(R.id.txt_Quantity_Student_Absent);
        txt_sl_sinhviencomat = findViewById(R.id.txt_sl_sinhviencomat);
        txt_idCourse = findViewById(R.id.lsdd_tv_mmh);
        listViewHistory = findViewById(R.id.listview_AbsentAttended);
        list_view_SinhVienKVang = findViewById(R.id.list_view_SinhVienKVang);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mService.getSessionByIdSession(SessionDetailsGV.idlession).enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                Session session = response.body();
                group = session.getIdgroup();
                QuantityStudentByGroup();
                selectDB();
                ShowAbsentStudents();
                ShowAttendedStudents();
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Toast.makeText(AttendanceHistoryTeacher.this, "Mất kết nối máy chủ!Vui lòng thử lại..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void selectDB() {
        mService.getGroupById(group).enqueue(new Callback<GroupSubject>() {
            @Override
            public void onResponse(Call<GroupSubject> call, Response<GroupSubject> response) {
                groupSubject = response.body();
                txtGroup.setText(String.valueOf(group));
                txtCourse.setText(groupSubject.getIdcourseNavigation().getCoursetName());
                txt_idCourse.setText(groupSubject.getIdcourse());
            }

            @Override
            public void onFailure(Call<GroupSubject> call, Throwable t) {

            }
        });
        //txtQuantityStudent.setText(//call id gourp ở ban studi điếm tên server luôn);
    }

    protected void ShowAbsentStudents() {
        sessionDetailStudents = new SessionDetailStudent();
        dataSessionDetailStudents = new ArrayList<>();
        String status = "0";
        mService.getByStatus(group, status).enqueue(new Callback<SessionDetailStudent>() {
            @Override
            public void onResponse(Call<SessionDetailStudent> call, Response<SessionDetailStudent> response) {
                sessionDetailStudents = response.body();
                quantityAbsent = sessionDetailStudents.getQuantity();
                txt_Quantity_Student_Absent.setText("SL: " + quantityAbsent);
                dataSessionDetailStudents = (ArrayList<DataSessionDetailStudent>) sessionDetailStudents.getData();
                historySVVangAdapter.addAll(dataSessionDetailStudents);
                listViewHistory.setAdapter(historySVVangAdapter);
            }

            @Override
            public void onFailure(Call<SessionDetailStudent> call, Throwable t) {

            }
        });

    }

    protected void ShowAttendedStudents() {
        String status = "1";
        sessionDetailStudents = new SessionDetailStudent();
        dataSessionDetailStudents1 = new ArrayList<>();
        mService.getByStatus(group, status).enqueue(new Callback<SessionDetailStudent>() {
            @Override
            public void onResponse(Call<SessionDetailStudent> call, Response<SessionDetailStudent> response) {
                sessionDetailStudents = response.body();
                quantityAbsent = sessionDetailStudents.getQuantity();
                txt_sl_sinhviencomat.setText("SL: " + quantityAbsent);
                dataSessionDetailStudents1 = (ArrayList<DataSessionDetailStudent>) sessionDetailStudents.getData();
                historyAdapter.clear();
                historyAdapter.addAll(dataSessionDetailStudents1);
                list_view_SinhVienKVang.setAdapter(historyAdapter);
            }

            @Override
            public void onFailure(Call<SessionDetailStudent> call, Throwable t) {

            }
        });

    }

    protected void QuantityStudentByGroup() {
        mService.getListStudentByGroup(group).enqueue(new Callback<QuantityStudentByGroup>() {
            @Override
            public void onResponse(Call<QuantityStudentByGroup> call, Response<QuantityStudentByGroup> response) {
                txtQuantityStudent.setText(String.valueOf(response.body().getData()));
            }

            @Override
            public void onFailure(Call<QuantityStudentByGroup> call, Throwable t) {

            }
        });
    }
}