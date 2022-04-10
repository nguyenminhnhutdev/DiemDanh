package com.example.myapplication.Data.model;


import com.example.myapplication.Data.model.IdlessionNavigation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SessionDetail {


    public SessionDetail(Integer idlession, String idstuddent, String status, String time, String note, String otp, String viTri) {
        this.idlession = idlession;
        this.idstuddent = idstuddent;
        this.status = status;
        this.time = time;
        this.note = note;
        this.otp = otp;
        this.viTri = viTri;
    }

    public SessionDetail(String status, String time, String note, String otp, String viTri) {
        this.status = status;
        this.time = time;
        this.note = note;
        this.otp = otp;
        this.viTri = viTri;
    }

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
    private String note;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("viTri")
    @Expose
    private String viTri;
    @SerializedName("idlessionNavigation")
    @Expose
    private IdlessionNavigation idlessionNavigation;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public IdlessionNavigation getIdlessionNavigation() {
        return idlessionNavigation;
    }

    public void setIdlessionNavigation(IdlessionNavigation idlessionNavigation) {
        this.idlessionNavigation = idlessionNavigation;
    }

}


