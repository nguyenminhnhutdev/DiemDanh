package com.example.myapplication;

import static com.example.myapplication.calender.CalendarUtils.daysInWeekArray;
import static com.example.myapplication.calender.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.SessionAdapter;
import com.example.myapplication.Data.model.GroupSubject;
import com.example.myapplication.Data.model.IdcourseNavigation;
import com.example.myapplication.Data.model.IdgroupNavigation;
import com.example.myapplication.Data.model.IdteacherNavigation;
import com.example.myapplication.Data.model.Session;
import com.example.myapplication.Data.model.Studies;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.calender.CalendarAdapter;
import com.example.myapplication.calender.CalendarUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class weekly_calendar extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private TextView tv_thangnam;
    private RecyclerView rcv_calendar;
    ImageView btn_close;



    ArrayList<GroupSubject> lsGroupSubjectDetail = new ArrayList<>();
    private SOService mService;
    ArrayList<Session> lsSessionGV = new ArrayList<>();
    ArrayList<Session> lsSessiongv;
    ListView lv_SessionGV;
    SessionAdapter sessionAdapter;

    ArrayList<Studies> lsGroupSV;
    ArrayList<Studies> lsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_weekly_calendar2);
        mService = ApiUtils.getSOService();//Tao doi tuong interface
        sessionAdapter = new SessionAdapter(weekly_calendar.this, R.layout.item_list_studentdetail);
        anhxa();
        CalendarUtils.selectedDate = LocalDate.now();
        checkRole();
        setWeekView();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void anhxa() {
        rcv_calendar = findViewById(R.id.rcv_calendar);
        tv_thangnam = findViewById(R.id.tv_thangnam);
        lv_SessionGV = findViewById(R.id.lv_SessionGV);
        btn_close = findViewById(R.id.btn_closetkb);
    }

    private void setWeekView() {
        tv_thangnam.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        rcv_calendar.setLayoutManager(layoutManager);
        rcv_calendar.setAdapter(calendarAdapter);
        checkRoleDisplay();

    }

    public void previousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectedDate = date;
        setWeekView();

    }

    public void CallSessionGV() {
        lsSessionGV = new ArrayList<>();
        lsSessiongv= new ArrayList<>();
        mService.getSessionByDate(String.valueOf(CalendarUtils.selectedDate)).enqueue(new Callback<List<Session>>() {
            @Override
            public void onResponse(Call<List<Session>> call, Response<List<Session>> response) {
                List<Session> lsSessiongv = response.body();
                for (Session ss: lsSessiongv){
                    int id = ss.getIdgroup();
                    for(GroupSubject gr : lsGroupSubjectDetail){
                        if(id== gr.getIdgroup()){
                           // Toast.makeText(weekly_calendar.this, "Group:" + id, Toast.LENGTH_LONG).show();
                            int idgroup = ss.getIdgroup();
                            String classroom = ss.getClassroom();
                            int session1 = ss.getSession1();
                            int periodStart = ss.getPeriodStart();
                            int periodEnd = ss.getPeriodEnd();
                            int idsession = ss.getIdsession();
                            String day = ss.getDay();
                            String date = ss.getDate();
                            IdgroupNavigation idgroupNavigation = ss.getIdgroupNavigation();
                            Session session = new Session( idsession, classroom, session1, periodStart,
                                    periodEnd, idgroup, day, date, idgroupNavigation);

                            lsSessionGV.add(session);
                        }
                    }
                }
                sessionAdapter.clear();
                sessionAdapter.addAll(lsSessionGV);
                lv_SessionGV.setAdapter(sessionAdapter);
                lv_SessionGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(weekly_calendar.this, SessionDetailsGV.class);
                        intent.putExtra("courseName",lsSessionGV.get(i).getCourseName());
                        intent.putExtra("idGroup", lsSessionGV.get(i).getIdgroup());
                        intent.putExtra("idSession",lsSessionGV.get(i).getIdsession());
                        intent.putExtra("periodStart", lsSessionGV.get(i).getPeriodStart());
                        intent.putExtra("periodEnd", lsSessionGV.get(i). getPeriodEnd());
                        intent.putExtra("class", lsSessionGV.get(i).getIdgroupNavigation().getClass_());
                        intent.putExtra("classRoom", lsSessionGV.get(i).getClassroom());
                        intent.putExtra("day", lsSessionGV.get(i).getDay());
                        intent.putExtra("date",lsSessionGV.get(i).getDate());
                        intent.putExtra("semester",lsSessionGV.get(i).getIdgroupNavigation().getSemester());
                        intent.putExtra("year", lsSessionGV.get(i).getIdgroupNavigation().getYear());

                        startActivity(intent);
                    }
                });

            }
            @Override
            public void onFailure(Call<List<Session>> call, Throwable t) {
                Toast.makeText(weekly_calendar.this, "Mất kết nối máy chủ", Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void CallSessionSV() {
        lsSessionGV = new ArrayList<>();
        lsSessiongv= new ArrayList<>();
        mService.getSessionByDate(String.valueOf(CalendarUtils.selectedDate)).enqueue(new Callback<List<Session>>() {
            @Override
            public void onResponse(Call<List<Session>> call, Response<List<Session>> response) {
                List<Session> lsSessiongv = response.body();
                for (Session ss: lsSessiongv){
                    int id = ss.getIdgroup();
                    for(Studies gr : lsGroupSV){
                        if(id== gr.getIdgroup()){
                            //Toast.makeText(weekly_calendar.this, "Group:" + id, Toast.LENGTH_LONG).show();
                            int idgroup = ss.getIdgroup();
                            String classroom = ss.getClassroom();
                            int session1 = ss.getSession1();
                            int periodStart = ss.getPeriodStart();
                            int periodEnd = ss.getPeriodEnd();
                            int idsession = ss.getIdsession();
                            String day = ss.getDay();
                            String date = ss.getDate();
                            IdgroupNavigation idgroupNavigation = ss.getIdgroupNavigation();
                            Session session = new Session( idsession, classroom, session1, periodStart,
                                    periodEnd, idgroup, day, date, idgroupNavigation);

                            lsSessionGV.add(session);
                        }
                    }
                }
                sessionAdapter.clear();
                sessionAdapter.addAll(lsSessionGV);
                lv_SessionGV.setAdapter(sessionAdapter);
                lv_SessionGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(weekly_calendar.this, SessionDetaisSV.class);
                        intent.putExtra("session1",lsSessionGV.get(i).getSession1());
                        intent.putExtra("courseName",lsSessionGV.get(i).getCourseName());
                        intent.putExtra("idGroup", lsSessionGV.get(i).getIdgroup());
                        intent.putExtra("idSession",lsSessionGV.get(i).getIdsession());
                        intent.putExtra("periodStart", lsSessionGV.get(i).getPeriodStart());
                        intent.putExtra("periodEnd", lsSessionGV.get(i). getPeriodEnd());
                        intent.putExtra("class", lsSessionGV.get(i).getIdgroupNavigation().getClass_());
                        intent.putExtra("classRoom", lsSessionGV.get(i).getClassroom());
                        intent.putExtra("day", lsSessionGV.get(i).getDay());
                        intent.putExtra("date",lsSessionGV.get(i).getDate());
                        intent.putExtra("semester",lsSessionGV.get(i).getIdgroupNavigation().getSemester());
                        intent.putExtra("year", lsSessionGV.get(i).getIdgroupNavigation().getYear());
                        startActivity(intent);
                    }
                });

            }
            @Override
            public void onFailure(Call<List<Session>> call, Throwable t) {
                Toast.makeText(weekly_calendar.this, "Mất kết nối máy chủ", Toast.LENGTH_SHORT).show();

            }
        });



    }
    protected void selectDBTeacher() {
        mService.getGroupByTeacher(MainActivity.idTeacher).enqueue(new Callback<List<GroupSubject>>() {
            @Override
            public void onResponse(Call<List<GroupSubject>> call, Response<List<GroupSubject>> response) {
                    List<GroupSubject> groupSubject = response.body();
                    if (groupSubject != null) {
                        for (GroupSubject group : groupSubject) {
                            int idGroup = group.getIdgroup();
                            String idCourse = group.getIdcourse();
                            String classs = group.getClass_();
                            String dateStart = group.getDateStart();
                            String dateEnd = group.getDateEnd();
                            int semeter = group.getSemester();
                            int year = group.getYear();
                            int idteacher = group.getIdteacher();

                            IdcourseNavigation idc = group.getIdcourseNavigation();
                            IdteacherNavigation idt = group.getIdteacherNavigation();

                            GroupSubject gr = new GroupSubject( idGroup, idCourse, idteacher,
                                    classs, dateStart, dateEnd, semeter, year, idc, idt);
                            lsGroupSubjectDetail.add(gr);
                        }
                    } else {
                        Toast.makeText(weekly_calendar.this, "Không có dữ liệu! ", Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onFailure(Call<List<GroupSubject>> call, Throwable t) {
                Toast.makeText(weekly_calendar.this, "Mất kết nối máy chủ! ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected  void selectGroupSSV(){
        lsGroup = new ArrayList<>();
        lsGroupSV= new ArrayList<>();
        mService.getStudyById(MainActivity.idStudent).enqueue(new Callback<List<Studies>>() {
            @Override
            public void onResponse(Call<List<Studies>> call, Response<List<Studies>> response) {
                List<Studies> lsGroup= response.body();
                if(lsGroup!=null)
                {
                    for  (Studies ss: lsGroup){
                        int idgroup = ss.getIdgroup();
                        String idstudent= ss.getIdstudent();
                        int stt= ss.getStt();
                        IdgroupNavigation idg= ss.getIdgroupNavigation();
                        Studies studies = new Studies(idgroup, idstudent, stt, idg);
                        lsGroupSV.add(studies);
                    }

                }
            }
            @Override
            public void onFailure(Call<List<Studies>> call, Throwable t) {
                return;
            }
        });
    }
    protected  void checkRole(){
        if (MainActivity.role.equals("1")){
            selectDBTeacher();
        }else if (MainActivity.role.equals("2")){
            selectGroupSSV();
        }
    }
    protected  void checkRoleDisplay(){
        if (MainActivity.role.equals("1")){
            CallSessionGV();
        }else if (MainActivity.role.equals("2")){
            CallSessionSV();
        }
    }

}
