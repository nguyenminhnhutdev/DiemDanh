package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IdteacherNavigation {
    public IdteacherNavigation(Integer idteacher, String name, String phone, String birthday, Integer id, String sourceTeacher, Object idNavigation, List<Object> groupSubjects) {
        this.idteacher = idteacher;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.id = id;
        this.sourceTeacher = sourceTeacher;
        this.idNavigation = idNavigation;
        this.groupSubjects = groupSubjects;
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
