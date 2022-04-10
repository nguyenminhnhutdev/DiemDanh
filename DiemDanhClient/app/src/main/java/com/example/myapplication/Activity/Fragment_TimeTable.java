package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Data.model.GroupSubject;

import com.example.myapplication.Data.model.IdcourseNavigation;
import com.example.myapplication.Data.model.IdgroupNavigation;
import com.example.myapplication.Data.model.IdteacherNavigation;
import com.example.myapplication.Data.model.Session;
import com.example.myapplication.Data.model.Students;
import com.example.myapplication.Data.model.Studies;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
//import com.example.myapplication.RecyclerView.RecyclerView_StudentDetail;
import com.example.myapplication.RecyclerView.RecyclerView_StudentDetail;
import com.example.myapplication.RecyclerView.RecyclerView_TeacherDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_TimeTable extends Fragment {

    public static String idStudent;
    public static String fullname;
    public static String birthday;
    public static String phone;
    public static String email;
    public static String address;
    public static int idTeacher;

    ArrayList<Students> lsStudent;
    ArrayList<GroupSubject> lsGroupSubjectDetail;
    ArrayList<Studies> lsStudies;
    ArrayList<Session> lsSessionSV;



    RecyclerView_TeacherDetail adapterTeacherDetail;
    RecyclerView_StudentDetail adapterStudentDetail;

    private RecyclerView rcv_sessiongv;
    private SOService mService;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_session, container, false);
        mService = ApiUtils.getSOService();//Tao doi tuong interface
        rcv_sessiongv = (RecyclerView) view.findViewById(R.id.rcv_sessiongv);
        selectDBTeacher();

        return view;
    }


    protected void selectDBTeacher() {

        lsGroupSubjectDetail= new ArrayList<>();
         mService.getGroupByTeacher(MainActivity.idTeacher).enqueue(new Callback<List<GroupSubject>>() {
             @Override
             public void onResponse(Call <List<GroupSubject>> call, Response<List<GroupSubject>> response) {
                 if(response.isSuccessful()) {
                      List<GroupSubject> groupSubject = response.body();
                     if(groupSubject!=null) {

                         for (GroupSubject group: groupSubject) {
                             int idGroup = group.getIdgroup();
                             String idCourse = group.getIdcourse();
                             String classs = group.getClass_();
                             String dateStart = group.getDateStart();
                             String dateEnd = group.getDateEnd();
                             int semeter = group.getSemester();
                             int year = group.getYear();
                             int idteacher=group.getIdteacher();
                             String coursetName = group.getIdcourseNavigation().getCoursetName();
                             int noc = group.getIdcourseNavigation().getNoc();
                             int peroid=group.getIdcourseNavigation().getPeroid();
                             IdcourseNavigation idc= group.getIdcourseNavigation();
                             IdteacherNavigation idt= group.getIdteacherNavigation();

                             GroupSubject gr= new GroupSubject(idGroup, idCourse,idteacher,classs,dateStart,dateEnd,semeter,year, idc, idt );
                             lsGroupSubjectDetail.add(gr);
                         }
                         rcv_sessiongv.setHasFixedSize(true);
                         RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                         adapterTeacherDetail = new RecyclerView_TeacherDetail(getContext(), lsGroupSubjectDetail);
                         rcv_sessiongv.setLayoutManager(layoutManager);
                         rcv_sessiongv.setAdapter(adapterTeacherDetail);
                         adapterTeacherDetail.notifyDataSetChanged();
                     }else {
                         Toast.makeText(getContext(), "Không có dữ liệu! ", Toast.LENGTH_SHORT).show();
                     }
                 }

             }

             @Override
             public void onFailure(Call<List<GroupSubject>> call, Throwable t) {
                 Toast.makeText(getContext(), "Mất kết nối máy chủ! ", Toast.LENGTH_SHORT).show();
             }
         });
}
    /*protected void selectDBStudent(){
        lsStudies= new ArrayList<>();
        lsSessionSV= new ArrayList<>();

        mService.getStudyById(MainActivity.idStudent).enqueue(new Callback<List<Studies>>() {
            @Override
            public void onResponse(Call<List<Studies>> call, Response<List<Studies>> response) {
                if(response.isSuccessful()){
                    List<Studies> lsStudies = response.body();
                    if(lsStudies!=null){
                        for(Studies studies: lsStudies){
                            int idgroup = studies.getIdgroup();
                            String idStudent= studies.getIdstudent();
                            int stt=studies.getStt();
                            lsSessionSV.addAll(lsidSession);

                        }

                        rcv_sessiongv.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        adapterStudentDetail = new RecyclerView_StudentDetail(getContext(), lsSessionSV);
                        rcv_sessiongv.setLayoutManager(layoutManager);
                        rcv_sessiongv.setAdapter(adapterStudentDetail);
                        adapterStudentDetail.notifyDataSetChanged();

                    }
                }
                else Toast.makeText(getContext(), "Không tìm thấy! ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Studies>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Mất kêt nối server! ", Toast.LENGTH_SHORT).show();

            }

        });
    }*/









}

