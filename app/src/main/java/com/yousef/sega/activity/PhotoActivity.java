package com.yousef.sega.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.yousef.sega.R;
import com.yousef.sega.database.Repository;
import com.yousef.sega.databinding.ActivityPhotoBinding;
import com.yousef.sega.listener.RegisterInterface;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.User;

public class PhotoActivity extends AppCompatActivity implements RegisterInterface {

    private ActivityPhotoBinding binding;
    private ProgressDialog dialog;
    private ActivityResultLauncher<String> activityResultLauncher;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = (User) getIntent().getExtras().getSerializable(Constants.USER);

        activityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                result -> {
                    if(result){
                        ImagePicker.with(this)
                                .cropSquare()
                                .start();
                    }
                    else
                        Toast.makeText(getApplicationContext(),getString(R.string.noPermission), Toast.LENGTH_LONG).show();
                }
        );


        binding.pickProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        });

        dialog=new ProgressDialog(getApplicationContext());

        Repository repository = new Repository();
        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setTitle(getString(R.string.signUp));
                dialog.setMessage(getString(R.string.signUpLoading));
                dialog.show();
                repository.signUp(user, dialog, PhotoActivity.this);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            user.setProfile(data.getData().toString());
            binding.pickProfile.setImageURI(data.getData());
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSuccess() {
        dialog.dismiss();
        Intent intent =new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        fileList();
    }

    @Override
    public void onFailure(Exception e) {
        dialog.dismiss();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}