package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Users {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("usename")
    @Expose
    private String usename;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("idadmin")
    @Expose
    private Integer idadmin;
    @SerializedName("idadminNavigation")
    @Expose
    private Object idadminNavigation;
    @SerializedName("students")
    @Expose
    private List<Object> students = null;
    @SerializedName("teachers")
    @Expose
    private List<Object> teachers = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(Integer idadmin) {
        this.idadmin = idadmin;
    }

    public Object getIdadminNavigation() {
        return idadminNavigation;
    }

    public void setIdadminNavigation(Object idadminNavigation) {
        this.idadminNavigation = idadminNavigation;
    }

    public List<Object> getStudents() {
        return students;
    }

    public void setStudents(List<Object> students) {
        this.students = students;
    }

    public List<Object> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Object> teachers) {
        this.teachers = teachers;
    }

}
