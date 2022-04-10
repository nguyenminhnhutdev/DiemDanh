package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSessionDetailStudent {
    @SerializedName("idlession")
    @Expose
    private Integer idlession;
    @SerializedName("idstuddent")
    @Expose
    private String idstuddent;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("note")
    @Expose
    private Object note;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("viTri")
    @Expose
    private Object viTri;
    @SerializedName("idlessionNavigation")
    @Expose
    private Object idlessionNavigation;
    @SerializedName("idstuddentNavigation")
    @Expose
    private IdstuddentNavigation idstuddentNavigation;

    public Integer getIdlession() {
        return idlession;
    }

    public void setIdlession(Integer idlession) {
        this.idlession = idlession;
    }

    public String getIdstuddent() {
        return idstuddent;
    }

    public void setIdstuddent(String idstuddent) {
        this.idstuddent = idstuddent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Object getViTri() {
        return viTri;
    }

    public void setViTri(Object viTri) {
        this.viTri = viTri;
    }

    public Object getIdlessionNavigation() {
        return idlessionNavigation;
    }

    public void setIdlessionNavigation(Object idlessionNavigation) {
        this.idlessionNavigation = idlessionNavigation;
    }

    public IdstuddentNavigation getIdstuddentNavigation() {
        return idstuddentNavigation;
    }

    public void setIdstuddentNavigation(IdstuddentNavigation idstuddentNavigation) {
        this.idstuddentNavigation = idstuddentNavigation;
    }
}
