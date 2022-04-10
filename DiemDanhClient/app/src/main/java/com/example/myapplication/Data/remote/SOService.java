package com.example.myapplication.Data.remote;

import com.example.myapplication.Data.model.GroupSubject;
import com.example.myapplication.Data.model.LichSuDDSV;
import com.example.myapplication.Data.model.QuantitySession;
import com.example.myapplication.Data.model.QuantityStudentByGroup;
import com.example.myapplication.Data.model.Session;
import com.example.myapplication.Data.model.SessionDetail;
import com.example.myapplication.Data.model.SessionDetailStudent;
import com.example.myapplication.Data.model.StatusSessionDetail;
import com.example.myapplication.Data.model.Students;
import com.example.myapplication.Data.model.Studies;
import com.example.myapplication.Data.model.Teachers;
import com.example.myapplication.Data.model.Users;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SOService {                    //Chua cac phuong thuc su dung de thuc thi cac yeu cau
    @GET("/api/Users/{username}/{password}")
    Call<Users> getUsers(@Path("username") String u, @Path("password") String p);

    @GET ("/api/Student/{idUser}")
    Call<Students> getStudents(@Path("idUser") int id); //Get thong tin sinh vien

    @GET ("/api/Students/{id}")
    Call<Students> getStudentsByid(@Path("id") String id);

    @GET ("/api/Teacher/{idUser}")
    Call<Teachers> getTeachers(@Path("idUser") int id); //Get thong tin giang vien

    @PUT ("/api/Teachers/{id}")
    Call<Teachers> putTeachers(@Path("id") int id, @Body Teachers teachers);

    @PUT("/api/Students/{id}")
    Call<Students> putStudents (@Path("id") String id, @Body Students  students);

    @GET("/api/Group/{idTeacher}")
    Call <List<GroupSubject>> getGroupByTeacher (@Path("idTeacher") int id);//Get group cua giang vien

    @GET("/api/GroupSubjects/{idGroup}")
    Call <GroupSubject> getGroupById(@Path("idGroup") int idGroup);

    @GET("/api/Studies/Student/{idStudent}")
    Call <List<Studies>> getStudyById(@Path("idStudent") String idStudent);

    @GET("/api/Sessions/Group/{id}/{idStudent}")
    Call<QuantitySession> getListSessionById(@Path("id") int id,@Path("idStudent") String idStudent);//

    @GET("/api/Session/Date/{date}")
    Call<List<Session>> getSessionByDate(@Path("date") String date);

    @GET ("/api/Sessions/{idSession}")
    Call<Session> getSessionByIdSession(@Path("idSession") int idSession);
    @GET("api/Session/{idSession}")
    Call<String > postDD(@Path("idSession") int idSession);

    @PUT("/api/SessionDetail/{idSession}/{idStudent}")
    Call<SessionDetail> putSourceOTP(@Path("idSession") int idSession, @Path("idStudent") String idStudent, @Body SessionDetail sess);

    @GET("/api/SessionDetails/{idStudent}/{idSession}")
    Call<SessionDetail> getSessionDetail(@Path("idStudent") String idStudent, @Path("idSession") int idSession);

    @GET("/api/SessionDetails/{idsession}")
    Call<List<SessionDetail>> getLessionDetail(@Path("idsession") int idsession);

    @GET("api/SessionDetails/api/SessionDetail/{idSession}/{status}")
    Call<SessionDetailStudent> getByStatus(@Path("idSession") int idSession, @Path("status") String status);

    @GET("api/Studies/QuantityStudent/{idGroup}")
    Call<QuantityStudentByGroup> getListStudentByGroup(@Path("idGroup") int idGroup);

    @GET("api/SessionDetails/api/{id}/{idstudent}")
    Call<StatusSessionDetail> getSessionDetails(@Path("id") int id, @Path("idstudent") String idstudent);

    @GET("api/SessionDetail/Date/{date}/{idStudent}")
    Call<LichSuDDSV> get(@Path("date") String date, @Path("idstudent") String idstudent);




}
