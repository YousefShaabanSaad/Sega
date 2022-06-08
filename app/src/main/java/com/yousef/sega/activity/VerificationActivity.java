package com.yousef.sega.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yousef.sega.R;
import com.yousef.sega.database.Repository;
import com.yousef.sega.databinding.ActivityVerificationBinding;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.User;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityVerificationBinding binding = ActivityVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        User user = (User) getIntent().getExtras().getSerializable(Constants.USER);

        String text = getString(R.string.verify) +" "+getEmailHide(user.getEmail())+" "+ getString(R.string.verify2);
        binding.questionVerify.setText(text);

        Repository repository = new Repository();
        repository.createEmail(user.getEmail(), user.getPassword(), getApplicationContext());

        binding.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repository.isVerify()){
                    Intent intent = new Intent(VerificationActivity.this, PhotoActivity.class);
                    intent.putExtra(Constants.USER, user);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), getString(R.string.goToEmail), Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), getString(R.string.goToEmail), Toast.LENGTH_LONG).show();
            }
        });

        binding.resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.createEmail(user.getEmail(), user.getEmail(), getApplicationContext());
            }
        });
    }

    private String getEmailHide(String email){
        String newEmail= "";
        newEmail+=email.charAt(0);
        newEmail+=email.charAt(1);
        newEmail+=email.charAt(2);
        for(int i=3; i<email.length(); i++){
            if(email.charAt(i)=='@'){
                newEmail+="@gmail.com";
                return newEmail;
            }
            else
                newEmail+="*";
        }
        return newEmail;
    }
}