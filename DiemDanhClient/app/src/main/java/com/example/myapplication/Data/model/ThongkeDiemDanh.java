package com.example.myapplication.Data.model;

public class ThongkeDiemDanh {
    private String mssv, ten, lop;
    private int buoihoc, tongbuoi;

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public int getBuoihoc() {
        return buoihoc;
    }

    public void setBuoihoc(int buoihoc) {
        this.buoihoc = buoihoc;
    }

    public int getTongbuoi() {
        return tongbuoi;
    }

    public void setTongbuoi(int tongbuoi) {
        this.tongbuoi = tongbuoi;
    }

    public ThongkeDiemDanh(String mssv, String ten, String lop, int buoihoc, int tongbuoi) {
        this.mssv = mssv;
        this.ten = ten;
        this.lop = lop;
        this.buoihoc = buoihoc;
        this.tongbuoi = tongbuoi;
    }
}
