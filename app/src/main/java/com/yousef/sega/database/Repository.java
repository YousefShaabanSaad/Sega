package com.yousef.sega.database;

import com.google.firebase.auth.FirebaseUser;
import com.yousef.sega.LoginInterface;

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
}
