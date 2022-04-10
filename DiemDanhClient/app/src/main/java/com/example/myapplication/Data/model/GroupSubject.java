package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class GroupSubject implements Serializable {
    public GroupSubject(Integer idgroup, String idcourse, Integer idteacher, String _class, String dateStart, String dateEnd, Integer semester, Integer year, IdcourseNavigation idcourseNavigation, IdteacherNavigation idteacherNavigation) {
        this.idgroup = idgroup;
        this.idcourse = idcourse;
        this.idteacher = idteacher;
        this._class = _class;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.semester = semester;
        this.year = year;
        this.idcourseNavigation = idcourseNavigation;
        this.idteacherNavigation = idteacherNavigation;
    }

  /*  public GroupSubject(Integer idgroup, String idcourse, Integer idteacher, String _class, String dateStart, String dateEnd, Integer semester, Integer year) {
        this.idgroup = idgroup;
        this.idcourse = idcourse;
        this.idteacher = idteacher;
        this._class = _class;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.semester = semester;
        this.year = year;
    }*/

    public String getLinkds() {
        return linkds;
    }

    public void setLinkds(String linkds) {
        this.linkds = linkds;
    }

    public String getLinkaddsr() {
        return linkaddsr;
    }

    public void setLinkaddsr(String linkaddsr) {
        this.linkaddsr = linkaddsr;
    }

    @SerializedName("linkds")
    @Expose
    private String linkds;
    @SerializedName("linkaddsr")
    @Expose
    private String linkaddsr;
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
    private IdcourseNavigation idcourseNavigation;
    @SerializedName("idteacherNavigation")
    @Expose
    private IdteacherNavigation idteacherNavigation;

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

    public IdcourseNavigation getIdcourseNavigation() {
        return idcourseNavigation;
    }

    public void setIdcourseNavigation(IdcourseNavigation idcourseNavigation) {
        this.idcourseNavigation = idcourseNavigation;
    }

    public IdteacherNavigation getIdteacherNavigation() {
        return idteacherNavigation;
    }

    public void setIdteacherNavigation(IdteacherNavigation idteacherNavigation) {
        this.idteacherNavigation = idteacherNavigation;
    }

}


