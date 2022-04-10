package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LichSuDDSV {
    @SerializedName("data")
    @Expose
    private Integer data;
    @SerializedName("list")
    @Expose
    private List<SessionDetail> list = null;

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public List<SessionDetail> getList() {
        return list;
    }

    public void setList(List<SessionDetail> list) {
        this.list = list;
    }

}
