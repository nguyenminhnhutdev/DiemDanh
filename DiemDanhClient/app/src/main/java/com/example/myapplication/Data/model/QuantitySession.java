package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuantitySession {
    @SerializedName("quantitySession")
    @Expose
    private Integer quantitySession;



    @SerializedName("yes")
    @Expose
    private Integer yes;
    @SerializedName("listSessionDetail")
    @Expose
    private List<SessionDetail> list = null;

    public Integer getYes() {
        return yes;
    }

    public void setYes(Integer yes) {
        this.yes = yes;
    }

    public Integer getQuantitySession() {
        return quantitySession;
    }

    public void setQuantitySession(Integer quantitySession) {
        this.quantitySession =quantitySession;
    }

    public List<SessionDetail> getList() {
        return list;
    }

    public void setList(List<SessionDetail> list) {
        this.list = list;
    }
}
