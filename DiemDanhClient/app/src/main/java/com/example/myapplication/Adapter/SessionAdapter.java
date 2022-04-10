package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Data.model.GroupSubject;
import com.example.myapplication.Data.model.Session;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionAdapter extends ArrayAdapter<Session> {
    private final Activity activity;
    int resource;
    public SessionAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.activity= (Activity) context;
        this.resource= resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater  layoutInflater= this.activity.getLayoutInflater();
        View spView = layoutInflater.inflate(this.resource, null);
        TextView txtPeriod = spView.findViewById(R.id.itemsv_tiet);
        TextView txtCourse = spView.findViewById(R.id.txt_CourseSV);
        TextView txtClass = spView.findViewById(R.id.txt_ClassSV);
        TextView txtGroup = spView.findViewById(R.id.txt_IdGroupSV);

        Session spSession = getItem(position);

        SOService mService;
        mService = ApiUtils.getSOService();

        mService.getGroupById(spSession.getIdgroup()).enqueue(new Callback<GroupSubject>() {
            @Override
            public void onResponse(Call<GroupSubject> call, Response<GroupSubject> response) {
                GroupSubject gr= response.body();
                txtPeriod.setText("Tiết "+ spSession.getPeriodStart() +"-"+spSession.getPeriodEnd());
                txtCourse.setText("Tên môn học: "+ gr.getIdcourseNavigation().getCoursetName());
                txtClass.setText("Phòng: "+ spSession.getClassroom());
                txtGroup.setText("Nhóm môn học: 0"+ String.valueOf(spSession.getIdgroup()));
            }

            @Override
            public void onFailure(Call<GroupSubject> call, Throwable t) {

            }
        });
        return spView;
    }
}
