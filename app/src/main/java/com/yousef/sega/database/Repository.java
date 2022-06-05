package com.yousef.sega.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.UploadTask;
import com.yousef.sega.R;
import com.yousef.sega.listener.LoginInterface;
import com.yousef.sega.listener.RegisterInterface;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.User;

public class Repository {

    RegisterFirebase registerFirebase;
    public Repository(){
        registerFirebase = new RegisterFirebase();
    }

    public FirebaseUser getUser(){
        return registerFirebase.getUser();
    }

    public void signIn(String email, String password, LoginInterface loginInterface) {
        registerFirebase.signIn(email, password, loginInterface);
    }

    public void resetPassword(String email, LoginInterface loginInterface){
       registerFirebase.resetPassword(email, loginInterface);
    }

    public void createEmail(String email,String password, Context context){
       registerFirebase.createEmail(email, password, context);
    }

    public boolean isVerify(){
        return registerFirebase.isVerify();
    }

    public void signUp(User user , ProgressDialog dialog, RegisterInterface registerInterface) {
        registerFirebase.signUp(user, dialog, registerInterface);
    }
}
