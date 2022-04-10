package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class IdlessionNavigation {

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

}