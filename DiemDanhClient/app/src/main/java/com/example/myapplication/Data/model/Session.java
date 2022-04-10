
package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Session implements Serializable {
    public Session(String couserName, Integer idsession, String classroom, Integer session1, Integer periodStart, Integer periodEnd, Integer idgroup, String day, String date, IdgroupNavigation idgroupNavigation) {
       this.courseName = couserName;
        this.idsession = idsession;
        this.classroom = classroom;
        this.session1 = session1;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.idgroup = idgroup;
        this.day = day;
        this.date = date;
        this.idgroupNavigation = idgroupNavigation;
    }

    public Session(Integer idsession, String classroom, Integer session1, Integer periodStart, Integer periodEnd, Integer idgroup, String day, String date, IdgroupNavigation idgroupNavigation) {
        this.idsession = idsession;
        this.classroom = classroom;
        this.session1 = session1;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.idgroup = idgroup;
        this.day = day;
        this.date = date;
        this.idgroupNavigation = idgroupNavigation;
    }

    @SerializedName("idsession")
    @Expose
    private Integer idsession;
    @SerializedName("classroom")
    @Expose
    private String classroom;
    @SerializedName("session1")
    @Expose
    private Integer session1;
    @SerializedName("periodStart")
    @Expose
    private Integer periodStart;
    @SerializedName("periodEnd")
    @Expose
    private Integer periodEnd;
    @SerializedName("idgroup")
    @Expose
    private Integer idgroup;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("date")
    @Expose
    private String date;



    @SerializedName("courseName")
    @Expose
    private String courseName;
    @SerializedName("idgroupNavigation")
    @Expose


    private IdgroupNavigation idgroupNavigation;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getIdsession() {
        return idsession;
    }

    public void setIdsession(Integer idsession) {
        this.idsession = idsession;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Integer getSession1() {
        return session1;
    }

    public void setSession1(Integer session1) {
        this.session1 = session1;
    }

    public Integer getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Integer periodStart) {
        this.periodStart = periodStart;
    }

    public Integer getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Integer periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Integer getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(Integer idgroup) {
        this.idgroup = idgroup;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public IdgroupNavigation getIdgroupNavigation() {
        return idgroupNavigation;
    }

    public void setIdgroupNavigation(IdgroupNavigation idgroupNavigation) {
        this.idgroupNavigation = idgroupNavigation;
    }


}