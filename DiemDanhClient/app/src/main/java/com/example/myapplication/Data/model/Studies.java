package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Studies {
    public Studies(Integer idgroup, String idstudent, Integer stt, IdgroupNavigation idgroupNavigation) {
        this.idgroup = idgroup;
        this.idstudent = idstudent;
        this.stt = stt;
        this.idgroupNavigation = idgroupNavigation;

    }

    @SerializedName("idgroup")
    @Expose
    private Integer idgroup;
    @SerializedName("idstudent")
    @Expose
    private String idstudent;
    @SerializedName("stt")
    @Expose
    private Integer stt;
    @SerializedName("idgroupNavigation")
    @Expose
    private IdgroupNavigation idgroupNavigation;
    @SerializedName("idstudentNavigation")
    @Expose
    private IdstuddentNavigation idstudentNavigation;

    public Integer getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(Integer idgroup) {
        this.idgroup = idgroup;
    }

    public String getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(String idstudent) {
        this.idstudent = idstudent;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public IdgroupNavigation getIdgroupNavigation() {
        return idgroupNavigation;
    }

    public void setIdgroupNavigation(IdgroupNavigation idgroupNavigation) {
        this.idgroupNavigation = idgroupNavigation;
    }

    public IdstuddentNavigation getIdstudentNavigation() {
        return idstudentNavigation;
    }

    public void setIdstudentNavigation(IdstuddentNavigation idstudentNavigation) {
        this.idstudentNavigation = idstudentNavigation;
    }

}


