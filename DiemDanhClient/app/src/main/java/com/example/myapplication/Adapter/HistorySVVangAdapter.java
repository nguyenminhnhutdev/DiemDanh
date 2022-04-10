package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Data.model.DataSessionDetailStudent;
import com.example.myapplication.Data.model.SessionDetail;
import com.example.myapplication.Data.model.SessionDetailStudent;
import com.example.myapplication.Data.model.Students;
import com.example.myapplication.Data.model.Studies;
import com.example.myapplication.Data.model.Teachers;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorySVVangAdapter extends ArrayAdapter<DataSessionDetailStudent> {
    private final Activity activity;
    int resource;

    public HistorySVVangAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.activity = (Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();
        View spView = layoutInflater.inflate(this.resource, null);

        TextView txtMssv = spView.findViewById(R.id.item_dsddvm_mssv);
        TextView txtName = spView.findViewById(R.id.item_dsddvm_hoten);
        TextView txtClass = spView.findViewById(R.id.item_dsddvm_lop);
        TextView imgStatus = spView.findViewById(R.id.img_status_uncheck);
        DataSessionDetailStudent spSessionDetail = getItem(position);

        txtMssv.setText(spSessionDetail.getIdstuddent());
        txtName.setText(spSessionDetail.getIdstuddentNavigation().getFullName());
        txtClass.setText(spSessionDetail.getIdstuddentNavigation().getClass_());
        return spView;
    }
}
