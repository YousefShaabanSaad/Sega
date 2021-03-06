package com.yousef.sega.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.yousef.sega.listener.LoginInterface;
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
                if(email.equals("")) {
                    binding.email.setError(getString(R.string.emailErrorEmpty));
                    binding.email.requestFocus();
                }
                else if(password.equals("")) {
                    binding.Password.setError(getString(R.string.passwordErrorEmpty));
                    binding.Password.requestFocus();
                }
                else if(password.length()<6) {
                    binding.Password.setError(getString(R.string.passwordErrorShort));
                    binding.Password.requestFocus();
                }
                else {
                    email +="@gmail.com";
                    Toast.makeText(LoginActivity.this, email, Toast.LENGTH_SHORT).show();

                    try {
                        repository.signIn(email, repository.encryption(password), LoginActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.email.getText().toString();
                if(email.equals("")) {
                    binding.email.setError(getString(R.string.emailErrorForgetPassword));
                    binding.email.requestFocus();
                }
                else
                    repository.resetPassword(email, LoginActivity.this);
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
        assert e.getMessage() !=null;
        if(e.getMessage().contains("There is no user")) {
            binding.email.setError(getString(R.string.emailError));
            binding.email.requestFocus();
        }
        else if(e.getMessage().contains("The password is invalid")) {
            binding.Password.setError(getString(R.string.passwordError));
            binding.Password.requestFocus();
        }
        else
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessReset() {
        Toast.makeText(this, getString(R.string.checkEmail), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailureReset(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}