package com.yousef.sega.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yousef.sega.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // MyToast.setToast(this, "Hello, Yousef shaaban saad  mohamed", MyToast.LONG, R.drawable.success,getResources().getColor(R.color.green));
    }
}