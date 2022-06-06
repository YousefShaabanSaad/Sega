package com.yousef.sega.activity;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yousef.sega.R;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Uri uri = getIntent().getData();
        if(uri != null){
            //get id
            String path = uri.getPath();
            String id = path.substring(17, path.length()-1);;
        }
    }
}