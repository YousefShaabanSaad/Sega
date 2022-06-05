package com.yousef.sega.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yousef.sega.R;
import com.yousef.sega.database.Repository;
import com.yousef.sega.databinding.ActivityPhotoBinding;
import com.yousef.sega.listener.RegisterInterface;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.User;

public class PhotoActivity extends AppCompatActivity implements RegisterInterface {

    private ActivityPhotoBinding binding;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       User user = (User) getIntent().getExtras().getSerializable(Constants.USER);

        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri picUri = null;
                Intent cropIntent = new Intent("com.android.camera.action.CROP");
                // indicate image type and Uri
                cropIntent.setDataAndType(picUri, "image/*");
                // set crop properties here
                cropIntent.putExtra("crop", true);
                // indicate aspect of desired crop
                cropIntent.putExtra("aspectX", 1);
                cropIntent.putExtra("aspectY", 1);
                // indicate output X and Y
                cropIntent.putExtra("outputX", 128);
                cropIntent.putExtra("outputY", 128);
                // retrieve data on return
                cropIntent.putExtra("return-data", true);
                // start the activity - we handle returning in onActivityResult
                startActivityForResult(cropIntent, 1);
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