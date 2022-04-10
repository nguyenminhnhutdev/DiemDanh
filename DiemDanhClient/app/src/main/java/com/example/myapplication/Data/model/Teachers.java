package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Teachers {


    public Teachers(String phone, String gmail, String adress) {
        this.phone = phone;
        this.gmail = gmail;
        this.adress = adress;
    }

    @SerializedName("idteacher")
    @Expose
    private Integer idteacher;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sourceTeacher")
    @Expose
    private String sourceTeacher;

    @SerializedName("gmail")
    @Expose
    private String gmail;

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @SerializedName("adress")
    @Expose
    private String adress;;

    @SerializedName("idNavigation")
    @Expose
    private Object idNavigation;
    @SerializedName("groupSubjects")
    @Expose
    private List<Object> groupSubjects = null;

    public Integer getIdteacher() {
        return idteacher;
    }

    public void setIdteacher(Integer idteacher) {
        this.idteacher = idteacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceTeacher() {
        return sourceTeacher;
    }

    public void setSourceTeacher(String sourceTeacher) {
        this.sourceTeacher = sourceTeacher;
    }

    public Object getIdNavigation() {
        return idNavigation;
    }

    public void setIdNavigation(Object idNavigation) {
        this.idNavigation = idNavigation;
    }

    public List<Object> getGroupSubjects() {
        return groupSubjects;
    }

    public void setGroupSubjects(List<Object> groupSubjects) {
        this.groupSubjects = groupSubjects;
    }

}