package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Data.model.GroupSubject;
import com.example.myapplication.Data.model.SessionDetail;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import net.sourceforge.jtds.jdbc.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class attendance_sv extends AppCompatActivity {

    TextView btn_bddiemdanh;
    TextInputLayout txtOTP;
    ImageView btn_close;
    private SOService mService;
    public static SessionDetail sessionDetail;
    private String url;

    //
    ProgressDialog progressDialog;
    //test timeoutdialog
    private static final long START_TIME_IN_MILLIS = 1000;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_attendance_sv);
        mService = ApiUtils.getSOService();//Tao doi tuong interface
        btn_bddiemdanh = findViewById(R.id.btn_bddiemdanhSV1);
        txtOTP = findViewById(R.id.txt_nhapotp);
        btn_close = findViewById(R.id.btn_closeotp);
        mService.getGroupById(SessionDetaisSV.idGroup).enqueue(new Callback<GroupSubject>() {
            @Override
            public void onResponse(Call<GroupSubject> call, Response<GroupSubject> response) {
                GroupSubject groupSubject = response.body();
                url = groupSubject.getLinkaddsr();
            }

            @Override
            public void onFailure(Call<GroupSubject> call, Throwable t) {

            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getOTP();
        btn_bddiemdanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Kiemtra();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    protected void getOTP() {
        mService.getSessionDetail(MainActivity.idStudent, SessionDetaisSV.idSession).enqueue(new Callback<SessionDetail>() {
            @Override
            public void onResponse(Call<SessionDetail> call, Response<SessionDetail> response) {
                sessionDetail = response.body();
            }

            @Override
            public void onFailure(Call<SessionDetail> call, Throwable t) {
                Toast.makeText(attendance_sv.this, "Mất kết nối máy chủ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void Kiemtra() throws ParseException {
        String otp = txtOTP.getEditText().getText().toString();
        //Date currentTime = Calendar.getInstance().getTime();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        // String currentTime= "2021-12-08T23:58:36.137";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSS");

        Date time = format.parse(sessionDetail.getTime());
        Date time1 = formatt.parse(currentTime);
        long diff = time1.getTime() - time.getTime();

        long diffSeconds = diff / 1000;
        //2021-12-04 23:42:40.967
        if (otp.equals(sessionDetail.getOtp()) == true && diffSeconds < 60) {
            Toast.makeText(attendance_sv.this, "Bạn đã điểm danh thành công!", Toast.LENGTH_LONG).show();

            // thông báo điểm danh thành công
            progressDialog = new ProgressDialog(attendance_sv.this);
            progressDialog.show();
            progressDialog.setContentView(R.layout.layout_dialog_loading);
            startTimerCheckfinish();

            //put trang thái lên
            String timecl = currentTime.toString();
            String note = "Sinh viên đã có đi học";
            String status = "1";
            String viTri = "null";
            putSourceOTP(note, status, viTri, timecl, sessionDetail.getOtp());
            postStatus(String.valueOf(SessionDetaisSV.session + 10), MainActivity.idStudent, status);


        } else {
            if (otp.equals(sessionDetail.getOtp()) == true) {
                Toast.makeText(attendance_sv.this, "Bạn đã ngoài thời gian điểm danh!", Toast.LENGTH_LONG).show();

                // thông báo điểm danh thành công
                progressDialog = new ProgressDialog(attendance_sv.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.layout_dialog_loading);
                startTimeroutUnCheckfinish();

                //put trang thai len
                String timecl = currentTime.toString();
                String note = "Sinh viên điểm danh ngoài thời gian!";
                String status = "0";
                String viTri = "null";
                putSourceOTP(note, status, viTri, timecl, sessionDetail.getOtp());
                postStatus(String.valueOf(SessionDetaisSV.session + 10), MainActivity.idStudent, status);


            } else {
                Toast.makeText(attendance_sv.this, "Mã OTP sai. Vui lòng kiểm tra lại!!", Toast.LENGTH_LONG).show();

                // thông báo điểm danh thành công
                progressDialog = new ProgressDialog(attendance_sv.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.layout_dialog_loading);
                //progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                //gọi hàm timeout
                startTimer();

                String note = "Sinh viên vắng học" + " OTP sinh viên xác nhận là: (" + otp + ")";
                if (otp == null) {
                    note = "Sinh viên vắng học!";
                }
                String timecl = currentTime.toString();
                String status = "2";
                String viTri = "null";
                putSourceOTP(note, status, viTri, timecl, sessionDetail.getOtp());
                postStatus(String.valueOf(SessionDetaisSV.session + 10), MainActivity.idStudent, status);

            }
        }
    }

    protected void putSourceOTP(String note, String status, String vitri, String time, String otp) {

        SessionDetail sess = new SessionDetail(status, time, note, otp, vitri);
        mService.putSourceOTP(SessionDetaisSV.idSession, MainActivity.idStudent, sess).enqueue(new Callback<SessionDetail>() {
            @Override
            public void onResponse(Call<SessionDetail> call, Response<SessionDetail> response) {

            }

            @Override
            public void onFailure(Call<SessionDetail> call, Throwable t) {


            }
        });
    }

    protected void postStatus(String session, String mssv, String status) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "addSession");
                params.put("session", session);
                params.put("status", status);
                params.put("mssv", mssv);

                return params;

            }

        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);

    }


    @Override
    public void onBackPressed() {
        //   progressDialog.dismiss();
    }

    //test timeout dialog
    private void startTimeroutUnCheckfinish() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;

                updateCountDownText();

                //

            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                progressDialog.dismiss();

                // thông báo điểm danh thành công
                progressDialog = new ProgressDialog(attendance_sv.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.layout_dialog_timeout_checkfinish);
                //

            }
        }.start();

        mTimerRunning = true;
        // mButtonStartPause.setText("pause");
        //mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void startTimerCheckfinish() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;

                updateCountDownText();

                //

            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                progressDialog.dismiss();

                // thông báo điểm danh thành công
                progressDialog = new ProgressDialog(attendance_sv.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.layout_dialog_checkfinish);
                //

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

                //

            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                progressDialog.dismiss();

                // thông báo điểm danh thành công
                progressDialog = new ProgressDialog(attendance_sv.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.layout_dialog_uncheckfinish);
                //

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


        //  tv_timeup.setText(timeLeftFormatted);
    }
    //
}