package com.yousef.sega.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yousef.sega.databinding.ActivityPlayWithFriendOfflineBinding;

public class PlayWithFriendOfflineActivity extends AppCompatActivity {

    private ActivityPlayWithFriendOfflineBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayWithFriendOfflineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}