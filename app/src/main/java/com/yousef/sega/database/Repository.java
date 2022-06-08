package com.yousef.sega.database;

import android.app.ProgressDialog;
import android.content.Context;
import com.google.firebase.auth.FirebaseUser;
import com.yousef.sega.listener.GameInterface;
import com.yousef.sega.listener.LoginInterface;
import com.yousef.sega.listener.RegisterInterface;
import com.yousef.sega.model.Chat;
import com.yousef.sega.model.Game;
import com.yousef.sega.model.User;
import java.util.List;

public class Repository {

    private final RegisterFirebase registerFirebase;
    private final HomeFirebase homeFirebase;
    public final CommonFunction commonFunction;
    public Repository(){
        registerFirebase = new RegisterFirebase();
        homeFirebase = new HomeFirebase();
        commonFunction = new CommonFunction();
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

    public void signUp(User user , ProgressDialog dialog, RegisterInterface registerInterface) {
        registerFirebase.signUp(user, dialog, registerInterface);
    }

    public void signOut(ProgressDialog progressDialog){
        registerFirebase.signOut(progressDialog);
    }

    //Home
    public void setStatus(String idUser, String value) {
        homeFirebase.setStatus(idUser, value);
    }

    public String createNewGame(Game game, GameInterface gameInterface){
        return homeFirebase.createNewGame(game, gameInterface);
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

    public void deleteChat(String id) {
        homeFirebase.deleteChat(id);
    }

    public List<User> getUsers(String id, GameInterface gameInterface) {
        return homeFirebase.getUsers(id, gameInterface);
    }

    public void createNewParticipants(String id, User user) {
        homeFirebase.createNewParticipants(id, user);
    }

    public void deleteParticipants(String id) {
        homeFirebase.deleteParticipants(id);
    }

    public void deleteOneParticipant(String id, String idUser) {
        homeFirebase.deleteOneParticipant(id, idUser);
    }




    //CommonFunction
    public String encryption(String text) throws Exception {
        return commonFunction.encryption(text);
    }

    public String decryption(String text) throws Exception{
        return commonFunction.decryption(text);
    }
}
