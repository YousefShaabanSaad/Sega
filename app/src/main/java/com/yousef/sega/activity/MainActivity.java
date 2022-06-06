package com.yousef.sega.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.yousef.sega.R;
import com.yousef.sega.database.Repository;

public class MainActivity extends AppCompatActivity {

    //  SHA1 and SHA-256
    //  Valid until:
    //  TODO Sunday, March 31, 2052

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FirebaseAuth.getInstance().sendPasswordResetEmail("yousefelgen141@gmail.com");

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1000);
                    checkUser();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void checkUser() {
        Repository repository=new Repository();
        Intent intent = new Intent();

        if(repository.getUser() != null){
            intent.setClass(this, HomeActivity.class);
        }else
            intent.setClass(this, PhotoActivity.class);
        startActivity(intent);
        finish();
    }
}