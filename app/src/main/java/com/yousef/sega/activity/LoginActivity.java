package com.yousef.sega.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yousef.sega.LoginInterface;
import com.yousef.sega.R;
import com.yousef.sega.database.Repository;
import com.yousef.sega.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements LoginInterface {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Repository repository = new Repository();

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.email.getText().toString();
                String password = binding.Password.getText().toString();
                if(email.equals(""))
                    binding.email.setError(getString(R.string.emailErrorEmpty));
                else if(password.equals(""))
                    binding.Password.setError(getString(R.string.passwordErrorEmpty));
                else if(password.length()<6)
                    binding.Password.setError(getString(R.string.passwordErrorShort));
                else {
                    email +="@gmail.com";
                    repository.signIn(email, password, LoginActivity.this);
                }
            }
        });


        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess() {
        Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailure(Exception e) {
        if(e.getMessage().contains("There is no user"))
            binding.email.setError(getString(R.string.emailError));
        else if(e.getMessage().contains("The password is invalid"))
            binding.Password.setError(getString(R.string.passwordError));
        else
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}