package com.yousef.sega.database;

import com.google.firebase.auth.FirebaseUser;

public class Repository {

    RegisterFirebase registerFirebase;
    public Repository(){
        registerFirebase = new RegisterFirebase();
    }
    public FirebaseUser getUser(){
        return registerFirebase.getUser();
    }
}
