package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IdgroupNavigation {

    @SerializedName("idgroup")
    @Expose
    private Integer idgroup;
    @SerializedName("idcourse")
    @Expose
    private String idcourse;
    @SerializedName("idteacher")
    @Expose
    private Integer idteacher;
    @SerializedName("classGroup")
    @Expose
    private String _class;
    @SerializedName("dateStart")
    @Expose
    private String dateStart;
    @SerializedName("dateEnd")
    @Expose
    private String dateEnd;
    @SerializedName("semester")
    @Expose
    private Integer semester;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("idcourseNavigation")
    @Expose
    private Object idcourseNavigation;
    @SerializedName("idteacherNavigation")
    @Expose
    private Object idteacherNavigation;
    @SerializedName("sessions")
    @Expose
    private List<Object> sessions = null;

    public Integer getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(Integer idgroup) {
        this.idgroup = idgroup;
    }

    public String getIdcourse() {
        return idcourse;
    }

    public void setIdcourse(String idcourse) {
        this.idcourse = idcourse;
    }

    public Integer getIdteacher() {
        return idteacher;
    }

    public void setIdteacher(Integer idteacher) {
        this.idteacher = idteacher;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Object getIdcourseNavigation() {
        return idcourseNavigation;
    }

    public void setIdcourseNavigation(Object idcourseNavigation) {
        this.idcourseNavigation = idcourseNavigation;
    }

    public Object getIdteacherNavigation() {
        return idteacherNavigation;
    }

    public void setIdteacherNavigation(Object idteacherNavigation) {
        this.idteacherNavigation = idteacherNavigation;
    }



}
