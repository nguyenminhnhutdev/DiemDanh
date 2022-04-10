package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Data.model.QuantitySession;
import com.example.myapplication.Data.model.QuantityStudentByGroup;
import com.example.myapplication.Data.model.Session;
import com.example.myapplication.Data.model.SessionDetail;
import com.example.myapplication.Data.model.StatusSessionDetail;
import com.example.myapplication.Data.model.Studies;
import com.example.myapplication.Data.model.ThongkeDiemDanh;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.R;
import com.example.myapplication.Thongke_Diemdanh;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticalAdapter extends ArrayAdapter<ThongkeDiemDanh> {
    private final Activity activity;
    int resource;
    List<Integer> listSessionGroup = new ArrayList<>(), listStatus;
    private int status = 0;
    ArrayList<Session> listSession;
    private StatusSessionDetail statusSessionDetail = new StatusSessionDetail();
    private TextView txt_NumberSession;
    private int quantityNotAbsent = 0;
    private String mssv;

    public StatisticalAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.activity = (Activity) context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();
        View spView = layoutInflater.inflate(this.resource, null);


        TextView txt_Mssv_Statictical = spView.findViewById(R.id.item_dstkdd_mssv);
        TextView txt_FullName_Statictical = spView.findViewById(R.id.item_dstkdd_hoten);
        txt_NumberSession = spView.findViewById(R.id.item_dstkdd_sobuoi);
        ThongkeDiemDanh studies = getItem(position);
        txt_Mssv_Statictical.setText(studies.getMssv());
        txt_FullName_Statictical.setText(studies.getTen());
        txt_NumberSession.setText(String.valueOf(studies.getBuoihoc()) + " / "+String.valueOf(studies.getTongbuoi()));

       /* mService.getListSessionById(Thongke_Diemdanh.idGroup, studies.getIdstudent()).enqueue(new Callback<QuantitySession>() {
            @Override
            public void onResponse(Call<QuantitySession> call, Response<QuantitySession> response) {
                txt_NumberSession.setText(String.valueOf(response.body().getYes()));
            }

            @Override
            public void onFailure(Call<QuantitySession> call, Throwable t) {

            }
        });

        txt_Mssv_Statictical.setText(studies.getIdstudent());
        txt_FullName_Statictical.setText(studies.getIdstudentNavigation().getFullName());
*/


        return spView;
    }

    /*private void setListSessionGroup() {
        mService.getListSessionById(Thongke_Diemdanh.idGroup, ).enqueue(new Callback<QuantitySession>() {
            @Override
            public void onResponse(Call<QuantitySession> call, Response<QuantitySession> response) {

                txt_NumberSession.setText(response.body().getQuantitySession().toString());
                //listSession = (ArrayList<Session>) response.body().getList();
                setListSessionGroup();
            }

            @Override
            public void onFailure(Call<QuantitySession> call, Throwable t) {

            }
        });

        listSessionGroup = new ArrayList<>();
        for (Session session : listSession) {
            int idSession = session.getIdsession();
            listSessionGroup.add(idSession);
        }




    }*/

    private void setSessionAbsent(String mssv) {

    }


}
