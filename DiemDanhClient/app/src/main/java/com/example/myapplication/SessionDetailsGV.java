package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Activity.AttendanceHistoryTeacher;
import com.example.myapplication.Activity.Result_attendance;
import com.example.myapplication.Data.model.GroupSubject;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.checkinternet.NetworkChangeListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionDetailsGV extends AppCompatActivity {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    private SOService mService;
    private String tr;
    TextView btn_diemdanh, txt_tenmh, txt_tietbd, txt_tiet, txt_mamh,
            txt_thu, txt_maphong, txt_ngayhoc, txt_lop, txt_hocky;
    ImageView btn_close;
    public static int idlession;
    //
    //
    //
    ProgressDialog progressDialog;
    private TextView tv_timeup;
    private static final long START_TIME_IN_MILLIS = 60000;

    private static final long START_TIME_IN_MILLIS_1 = 2000;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mTimeLeftInMillis_1 = START_TIME_IN_MILLIS_1;

    private TextView mButtonStartPause;
    private TextView mButtonReset;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_details_gv);
        getSupportActionBar().hide();
        mService = ApiUtils.getSOService();//Tao doi tuong interface

        txt_tenmh = findViewById(R.id.txt_tenmonhocgv);
        txt_tietbd = findViewById(R.id.txt_tietbatdaugv);
        txt_tiet = findViewById(R.id.txt_tietgv);
        txt_thu = findViewById(R.id.txt_thugv);
        txt_maphong = findViewById(R.id.txt_maphonggv);
        txt_ngayhoc = findViewById(R.id.txt_ngayhocgv);
        txt_lop = findViewById(R.id.txt_lopgv);
        txt_hocky = findViewById(R.id.txt_hockygv);
        txt_mamh = findViewById(R.id.txt_mamh);
        btn_diemdanh = findViewById(R.id.btn_diemdanhgv);
        btn_close = findViewById(R.id.btn_backctbh);
        upDuLieuCT();

        btn_diemdanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // thông báo điểm danh thành công
                progressDialog = new ProgressDialog(SessionDetailsGV.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.layout_dialog_loading);
                startTimerOutLoading();

            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void upDuLieuCT() {
        Intent intent = getIntent();
        mService.getGroupById(intent.getIntExtra("idGroup", 0)).enqueue(new Callback<GroupSubject>() {
            @Override
            public void onResponse(Call<GroupSubject> call, Response<GroupSubject> response) {
                GroupSubject gr = response.body();
                idlession = intent.getIntExtra("idSession", 0);
                txt_tenmh.setText("Tên môn: " + gr.getIdcourseNavigation().getCoursetName());
                txt_mamh.setText("Mã môn học: " + gr.getIdcourseNavigation().getIdcourse());
                txt_tietbd.setText("Tiết bắt đầu: " + String.valueOf(intent.getIntExtra("periodStart", 0)));
                txt_tiet.setText("Tiết: " + String.valueOf(intent.getIntExtra("periodStart", 0) + " - " +
                        String.valueOf(intent.getIntExtra("periodEnd", 0))));
                txt_thu.setText("Thứ: " + intent.getStringExtra("day").replace("Thứ ", ""));
                txt_maphong.setText("Mã phòng học: " + intent.getStringExtra("classRoom"));


                String date = intent.getStringExtra("date").replace("T00:00:00", "");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date dates = (Date) formatter.parse(date);
                    SimpleDateFormat newFormat = new SimpleDateFormat("MM-dd-yyyy");
                    String finalString = newFormat.format(dates);
                    txt_ngayhoc.setText("Ngày: " + finalString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                txt_lop.setText("Lớp : " + intent.getStringExtra("class"));
                txt_hocky.setText("Học kỳ: " + String.valueOf(intent.getIntExtra("semester", 0)));
            }

            @Override
            public void onFailure(Call<GroupSubject> call, Throwable t) {

            }
        });


    }

    public void openDialogAttendance(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_timeup);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

//        if (Gravity.CENTER == gravity) {
//            dialog.setCancelable(false);
//        }//lộn chổ này
        tv_timeup = dialog.findViewById(R.id.tv_timeup);
        //TextView btn_open = dialog.findViewById(R.id.btn_opentimeup);

        mButtonStartPause = dialog.findViewById(R.id.btn_opentimeup);
        mButtonReset = dialog.findViewById(R.id.btn_stoptimeup);
        mButtonReset.setVisibility(View.INVISIBLE);


        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                btn_diemdanh.setVisibility(View.INVISIBLE);
            }
        });
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimerRunning) {
                    //pauseTimer();
                    //mButtonStartPause.setVisibility(View.INVISIBLE);
                } else {
                    if (Gravity.CENTER == gravity) {
                        dialog.setCancelable(false);
                    }
                    startTimer();
                    Intent intent = getIntent();
                    int idSession = intent.getIntExtra("idSession", 0);
                    mService.postDD(idSession).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            Toast.makeText(SessionDetailsGV.this, "Đã gửi yêu cầu điểm danh!!!", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(SessionDetailsGV.this, "Mất kết nối máy chủ!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        updateCountDownText();
        dialog.show();
    }

    //
    private void startTimerOutLoading() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis_1, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis_1 = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                progressDialog.dismiss();

                openDialogAttendance(Gravity.CENTER);
            }
        }.start();

        mTimerRunning = true;
        // mButtonStartPause.setText("pause");
        //mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                mButtonStartPause.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                //mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);

                //code test123
                finish();
                Intent intent = new Intent(SessionDetailsGV.this, AttendanceHistoryTeacher.class);
                startActivity(intent);

            }
        }.start();

        mTimerRunning = true;
        // mButtonStartPause.setText("pause");
        //mButtonReset.setVisibility(View.INVISIBLE);
    }



    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tv_timeup.setText(timeLeftFormatted);
    }


    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}