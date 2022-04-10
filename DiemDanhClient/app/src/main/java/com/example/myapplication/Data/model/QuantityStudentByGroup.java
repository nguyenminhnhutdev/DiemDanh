package com.example.myapplication.Data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuantityStudentByGroup {

    @SerializedName("data")
    @Expose
    private Integer data;
    @SerializedName("list")
    @Expose
    private List<Studies> list = null;

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public List<Studies> getList() {
        return list;
    }

    public void setList(List<Studies> list) {
        this.list = list;
    }

}

