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

import com.example.myapplication.Activity.Result_attendance;
import com.example.myapplication.Data.model.SessionDetail;
import com.example.myapplication.Data.model.Students;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultAdapter extends ArrayAdapter<SessionDetail> {
    private final Activity activity;
    int resource;

    public ResultAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.activity = (Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();
        View spView = layoutInflater.inflate(this.resource, null);

        TextView txtMssv = spView.findViewById(R.id.tv_mssv);
        TextView txtName = spView.findViewById(R.id.tv_hoten);
        TextView txtClass = spView.findViewById(R.id.tv_lop);
        ImageView imgCheck = spView.findViewById(R.id.img_check);


        SessionDetail spSessionDetail = getItem(position);
        txtMssv.setText(spSessionDetail.getIdstuddent());
        SOService mService;
        mService = ApiUtils.getSOService();
        mService.getStudentsByid(spSessionDetail.getIdstuddent()).enqueue(new Callback<Students>() {
            @Override
            public void onResponse(Call<Students> call, Response<Students> response) {
                Students students = response.body();
                txtMssv.setText("MSSV: "+ spSessionDetail.getIdstuddent());
                txtName.setText("Họ tên: " +students.getFullName());
                txtClass.setText("Lớp: "+students.getClass_());
                if (spSessionDetail.getStatus()==null) {
                    imgCheck.setImageResource(R.drawable.ic_check);
                } else {
                    imgCheck.setImageResource(R.drawable.ic_uncheck);
                }
            }

            @Override
            public void onFailure(Call<Students> call, Throwable t) {
            }
        });


        return spView;
    }
}
