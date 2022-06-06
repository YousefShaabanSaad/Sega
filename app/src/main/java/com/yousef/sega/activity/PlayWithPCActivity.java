package com.yousef.sega.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.yousef.sega.databinding.ActivityPlayWithPcBinding;
public class PlayWithPCActivity extends AppCompatActivity {

    private ActivityPlayWithPcBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayWithPcBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}