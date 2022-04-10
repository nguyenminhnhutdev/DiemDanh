package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Data.model.LichSuDDSV;
import com.example.myapplication.Data.model.Session;
import com.example.myapplication.Data.model.SessionDetail;
import com.example.myapplication.R;

public class LichSuSV extends ArrayAdapter<SessionDetail> {
    private final Activity activity;
    int resource;
    public LichSuSV(@NonNull Context context, int resource) {
        super(context, resource);
        this.activity = (Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();
        View spView = layoutInflater.inflate(this.resource, null);

       // SessionDetail sessionDetail = getItem(position);

       /* TextView txt_lsdd_gio = spView.findViewById(R.id.txt_lsdd_gio);
        TextView txt_lsdd_ngay = spView.findViewById(R.id.txt_lsdd_ngay);
        TextView txt_lsdd_trangthai = spView.findViewById(R.id.txt_lsdd_trangthai);


        txt_lsdd_ngay.setText("null"+ sessionDetail.getTime().toString());
        txt_lsdd_trangthai.setText("null"+ sessionDetail.getStatus());*/

        //TextView txt_lsdd_tenmh = spView.findViewById(R.id.txt_lsdd_ngay);

        return spView;
    }
}
