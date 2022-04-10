package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.Activity.Fragment_Student;
import com.example.myapplication.Activity.Fragment_TimeTable;
import com.example.myapplication.checkinternet.NetworkChangeListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      /*  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(ContextCompat.getColor(Home.this, R.color.white));*/


        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        frament_home frament_home= new frament_home();

        loadFragment(frament_home);
        bottomNavigationView = findViewById(R.id.nav_menu);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.homeid:
                        loadFragment(frament_home);
                        return true;

                    case R.id.session:
                        if(MainActivity.role.equals("1")){
                            fragment = new Fragment_TimeTable();
                            loadFragment(fragment);
                        }
                        else{

                        }

                        return true;

                    case R.id.search:
                        fragment = new sesion0();
                        loadFragment(fragment);
                        return true;
                    case R.id.profile:
                        //fragment = new profileFragment();
                        fragment = new fragment_thongtincanhanmoi();
                        loadFragment(fragment);
                        return true;

                    /*case R.id.nav_thongtin:
                        fragment = new Toi();
                        loadFragment(fragment);
                        return true;*/
                }
                return false;
            }
        });







    }





    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.continer,fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart(){
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected  void onStop(){
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}