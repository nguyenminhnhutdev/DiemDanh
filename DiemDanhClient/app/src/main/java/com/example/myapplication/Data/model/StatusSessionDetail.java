package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusSessionDetail {
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("data")
    @Expose
    private SessionDetail data = null;

    public Integer getQuantity() {
        return result;
    }

    public void setQuantity(Integer quantity) {
        this.result = quantity;
    }

    public SessionDetail getData() {
        return data;
    }

    public void setData(SessionDetail data) {
        this.data = data;
    }
}
