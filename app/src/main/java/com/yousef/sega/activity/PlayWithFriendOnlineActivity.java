package com.yousef.sega.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.yousef.sega.databinding.ActivityPlayWithFriendOnlineBinding;

public class PlayWithFriendOnlineActivity extends AppCompatActivity {

    private ActivityPlayWithFriendOnlineBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayWithFriendOnlineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Uri uri = getIntent().getData();
        if(uri != null){
            //get id
            String path = uri.getPath();
            String id = path.substring(17, path.length()-1);;
        }else {
            Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
        }

//        binding.love.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


    }
}