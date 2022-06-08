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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.UploadTask;
import com.yousef.sega.R;
import com.yousef.sega.listener.GameInterface;
import com.yousef.sega.listener.LoginInterface;
import com.yousef.sega.listener.RegisterInterface;
import com.yousef.sega.model.Chat;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.Game;
import com.yousef.sega.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {

    private final RegisterFirebase registerFirebase;
    private final HomeFirebase homeFirebase;
    public Repository(){
        registerFirebase = new RegisterFirebase();
        homeFirebase = new HomeFirebase();
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


    //Home

    public String createNewGame(Game game){
        return homeFirebase.createNewGame(game);
    }

    public void updateGame(String id, String key, String value){
        homeFirebase.updateGame(id, key, value);
    }

    public Game getGame(String id, GameInterface gameInterface){
        return homeFirebase.getGame(id, gameInterface);
    }

    public void updatePlay(String id, int value){
       homeFirebase.updatePlay(id, value);
    }

    public void deleteGame(String id){
        homeFirebase.deleteGame(id);
    }

    public List<Chat> getChats(String id, GameInterface gameInterface){
        return homeFirebase.getChats(id, gameInterface);
    }

    public void createNewChat(String id, Chat chat) {
        homeFirebase.createNewChat(id, chat);
    }

    public List<User> getUsers(String id, GameInterface gameInterface) {
        return homeFirebase.getUsers(id, gameInterface);
    }

    public void createNewParticipants(String id, User user) {
        homeFirebase.createNewParticipants(id, user);
    }

    public void deleteChat(String id) {
        homeFirebase.deleteChat(id);
    }

    public void deleteParticipants(String id) {
        homeFirebase.deleteParticipants(id);
    }
}
