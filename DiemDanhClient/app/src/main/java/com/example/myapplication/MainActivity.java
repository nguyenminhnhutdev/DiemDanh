package com.example.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myapplication.Data.model.Students;
import com.example.myapplication.Data.model.Teachers;
import com.example.myapplication.Data.remote.ApiUtils;
import com.example.myapplication.Data.remote.SOService;
import com.example.myapplication.checkinternet.NetworkChangeListener;

import com.example.myapplication.Data.model.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static String role;
    public static int id;
    public static String idStudent;
    public static String fullname;
    public static String birthday;
    public static String phone;
    public static String email;
    public static String address;
    public static int idTeacher;
    public static String usernametk;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    private SOService mService;//khai bao doi tuong interface
    TextView btn_login, quenmk;
    EditText username, password;
    TextView btn_quenmk;
    ImageView facebook, intargam, youtube, webhutech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.Black));

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mService = ApiUtils.getSOService();//Tao doi tuong interface


        username = findViewById(R.id.username);
        password = findViewById(R.id.pass);
        btn_login = findViewById(R.id.btn_login);
        facebook = findViewById(R.id.facebook);
        intargam = findViewById(R.id.intargam);
        quenmk = findViewById(R.id.quenmk);
        youtube = findViewById(R.id.youtube);
        webhutech = findViewById(R.id.website_hutech);





        quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Forgot_Password.class);
                startActivity(intent);
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL("https://www.facebook.com/hutechuniversity");
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL("https://www.youtube.com/watch?v=x4Q7jaiUM74&ab_channel=HUTECHChannel");
            }
        });
        intargam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL("https://www.instagram.com/hutechuniversity/");
            }
        });
        webhutech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoURL("https://www.hutech.edu.vn/");
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginApi();

            }
        });


    }

    protected void loginApi() {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        mService.getUsers(user, pass).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Users usersList = response.body();


                if (usersList != null) {


                            role=usersList.getRole();
                            id = usersList.getId();
                            usernametk = user;
                            loadFrofile();
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);

                    Toast.makeText(MainActivity.this, "Đăng nhập thành công! ", Toast.LENGTH_SHORT).show();


                   


                } else {
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại! Vui lòng kiểm tra lại thông tin!", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Mất kết nối máy chủ!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void loadFrofile() {
        mService = ApiUtils.getSOService();//Tao doi tuong interface

        if (role.equals("2")) {
            mService.getStudents(id).enqueue(new Callback<Students>() {
                @Override
                public void onResponse(Call<Students> call, Response<Students> response) {
                    if (response.isSuccessful()) {
                        Students students = response.body();
                        idStudent = students.getIdstudent();
                        fullname = students.getFullName();
                        birthday = students.getBirthday();
                        phone = students.getPhone();
                        address = students.getAddress();
                        email = students.getEmail();
                    } else {
                        Toast.makeText(MainActivity.this, "Lỗi!!!", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Students> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Mất kết nối máy chủ!!!", Toast.LENGTH_SHORT).show();

                }
            });

        } else {

            mService.getTeachers(id).enqueue(new Callback<Teachers>() {
                @Override
                public void onResponse(Call<Teachers> call, Response<Teachers> response) {
                    if (response.isSuccessful()) {
                        Teachers teachers = response.body();
                        idTeacher = teachers.getIdteacher();
                        fullname = (teachers.getName());
                        birthday = (teachers.getBirthday());
                        phone = (teachers.getPhone());
                        address = (teachers.getAdress());
                        email = ((teachers.getGmail()));

                    } else {
                        Toast.makeText(MainActivity.this, "Mất kết nối dữ liệu!!!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Teachers> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Bị mất kết nối!!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
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

    private void gotoURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

}