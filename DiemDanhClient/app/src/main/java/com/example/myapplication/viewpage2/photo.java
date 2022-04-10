package com.example.myapplication.viewpage2;

import java.io.Serializable;

public class photo implements Serializable {
    private int rsuorceId;

    public photo(int rsuorceId) {
        this.rsuorceId = rsuorceId;
    }

    public int getRsuorceId() {
        return rsuorceId;
    }

    public void setRsuorceId(int rsuorceId) {
        this.rsuorceId = rsuorceId;
    }
}
