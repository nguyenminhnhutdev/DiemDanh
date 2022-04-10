package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Data.model.IdgroupNavigation;
import com.example.myapplication.Data.model.Session;
import com.example.myapplication.Data.model.Studies;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerView.RecyclerView_StudentDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Student extends Fragment {


    private RecyclerView rcv_sessiongv;
    private SOService mService;
    ArrayList<Integer> lsGroup;//list nhom mon hoc
    ArrayList<Studies> listStudy;
    ArrayList<Session> lsSessionSV;

    RecyclerView_StudentDetail adapterStudentDetail;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_session, container, false);
            mService = ApiUtils.getSOService();//Tao doi tuong interface
        rcv_sessiongv = (RecyclerView) view.findViewById(R.id.rcv_sessiongv);
        CallGroup();
       // CallSession();
        return view;
    }


    protected  void CallGroup (){   //Lay list mon hoc cua sinh vien theo id sinh vien
        lsGroup = new ArrayList<>();
        mService= ApiUtils.getSOService();//Tao doi tuong interface
        mService.getStudyById(MainActivity.idStudent).enqueue(new Callback<List<Studies>>() {
            @Override
            public void onResponse(Call<List<Studies>> call, Response<List<Studies>> response) {
                if(response.isSuccessful()){
                    List<Studies> lsStudy= response.body();


                  /*  rcv_sessiongv.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    adapterStudentDetail = new RecyclerView_StudentDetail(getContext(), listStudy);
                    rcv_sessiongv.setLayoutManager(layoutManager);
                    rcv_sessiongv.setAdapter(adapterStudentDetail);
                    adapterStudentDetail.notifyDataSetChanged();*/
                }
            }

            @Override
            public void onFailure(Call<List<Studies>> call, Throwable t) {
                return;
            }
        });

    }



   /* *//*protected  void CallSession ()*//*{
        //lsidSession = new ArrayList<>();
        mService=ApiUtils.getSOService();
        for (Integer i: lsGroup){
            mService.getSessionById(i).enqueue(new Callback<List<Session>>() {
                @Override
                public void onResponse(Call<List<Session>> call, Response<List<Session>> response) {
                    if(response.isSuccessful()) {
                        List<Session> sessionList = response.body();
                        for (Session sess : sessionList) {
                            int idSession = sess.getIdsession();
                            String classroom = sess.getClassroom();
                            int session1 = sess.getSession1();
                            int periodStart = sess.getPeriodStart();
                            int periodEnd = sess.getPeriodEnd();
                            int idgroup = sess.getIdgroup();
                            String day = sess.getDay();
                            String date = sess.getDate();
                            IdgroupNavigation idg = sess.getIdgroupNavigation();
                            Session session = new Session(idSession, classroom, session1, periodStart, periodEnd, idgroup, day, date, idg);
                           // lsidSession.add(session);
                        }

                        rcv_sessiongv.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        adapterStudentDetail = new RecyclerView_StudentDetail(getContext(), lsSessionSV);
                        rcv_sessiongv.setLayoutManager(layoutManager);
                        rcv_sessiongv.setAdapter(adapterStudentDetail);
                        adapterStudentDetail.notifyDataSetChanged();

                    }
                }

                @Override
                public void onFailure(Call<List<Session>> call, Throwable t) {

                }
            });
        }


    }*/


}
