package com.yousef.sega.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yousef.sega.R;
import com.yousef.sega.databinding.ActivityRegistrationBinding;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.User;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.name.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.Password.getText().toString();
                String confirmPassword = binding.confirmPassword.getText().toString();

                if(name.equals("")) {
                    binding.name.setError(getString(R.string.nameErrorEmpty));
                    binding.name.requestFocus();
                }
                else if(email.equals("")) {
                    binding.email.setError(getString(R.string.emailErrorEmpty));
                    binding.email.requestFocus();
                }
                else if(password.equals("")) {
                    binding.Password.setError(getString(R.string.passwordErrorEmpty));
                    binding.Password.requestFocus();
                }
                else if(confirmPassword.equals("")) {
                    binding.confirmPassword.setError(getString(R.string.passwordErrorEmpty));
                    binding.confirmPassword.requestFocus();
                }
                else if(password.length()<6) {
                    binding.Password.setError(getString(R.string.passwordErrorShort));
                    binding.Password.requestFocus();
                }
                else if (!password.equals(confirmPassword)){
                    binding.confirmPassword.setError(getString(R.string.notEqualPassword));
                    binding.confirmPassword.requestFocus();
                }
                else {
                    email+="@gmail.com";
                    //Toast.makeText(RegistrationActivity.this, email, Toast.LENGTH_SHORT).show();
                    User user=new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setStatus(Constants.ONLINE);
                    Intent intent = new Intent(RegistrationActivity.this, VerificationActivity.class);
                    intent.putExtra(Constants.USER, user);
                    startActivity(intent);
                }
            }
        });


    }
}