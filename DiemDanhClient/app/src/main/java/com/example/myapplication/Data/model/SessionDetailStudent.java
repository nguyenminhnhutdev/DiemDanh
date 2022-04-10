package com.example.myapplication.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SessionDetailStudent {
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("data")
    @Expose
    private List<DataSessionDetailStudent> data = null;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<DataSessionDetailStudent> getData() {
        return data;
    }

    public void setData(List<DataSessionDetailStudent> data) {
        this.data = data;
    }
}
