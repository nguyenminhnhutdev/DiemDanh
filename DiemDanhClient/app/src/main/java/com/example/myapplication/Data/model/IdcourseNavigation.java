package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IdcourseNavigation {
    public IdcourseNavigation(String idcourse, String coursetName, Integer noc, Integer peroid) {
        this.idcourse = idcourse;
        this.coursetName = coursetName;
        this.noc = noc;
        this.peroid = peroid;
    }

    /*public IdcourseNavigation(String idcourse, String coursetName, Integer noc, Integer peroid, List<Object> groupSubjects) {
        this.idcourse = idcourse;
        this.coursetName = coursetName;
        this.noc = noc;
        this.peroid = peroid;
        this.groupSubjects = groupSubjects;
    }*/

    @SerializedName("idcourse")
    @Expose
    private String idcourse;
    @SerializedName("coursetName")
    @Expose
    private String coursetName;
    @SerializedName("noc")
    @Expose
    private Integer noc;
    @SerializedName("peroid")
    @Expose
    private Integer peroid;
    @SerializedName("groupSubjects")
    @Expose
    private List<Object> groupSubjects = null;

    public String getIdcourse() {
        return idcourse;
    }

    public void setIdcourse(String idcourse) {
        this.idcourse = idcourse;
    }

    public String getCoursetName() {
        return coursetName;
    }

    public void setCoursetName(String coursetName) {
        this.coursetName = coursetName;
    }

    public Integer getNoc() {
        return noc;
    }

    public void setNoc(Integer noc) {
        this.noc = noc;
    }

    public Integer getPeroid() {
        return peroid;
    }

    public void setPeroid(Integer peroid) {
        this.peroid = peroid;
    }

    public List<Object> getGroupSubjects() {
        return groupSubjects;
    }

    public void setGroupSubjects(List<Object> groupSubjects) {
        this.groupSubjects = groupSubjects;
    }


}
