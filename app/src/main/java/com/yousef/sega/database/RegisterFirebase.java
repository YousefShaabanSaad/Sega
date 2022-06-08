package com.yousef.sega.database;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.yousef.sega.R;
import com.yousef.sega.listener.LoginInterface;
import com.yousef.sega.listener.RegisterInterface;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.User;


public class RegisterFirebase {
    private final FirebaseFirestore firebaseFirestore;
    private final FirebaseStorage firebaseStorage;
    private final FirebaseAuth firebaseAuth;

    public RegisterFirebase() {
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
    }

    public FirebaseUser getUser(){
        return firebaseAuth.getCurrentUser();
    }

    public void signIn(String email, String password, LoginInterface loginInterface) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                loginInterface.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loginInterface.onFailure(e);
            }
        });
    }

    public void resetPassword(String email, LoginInterface loginInterface){
        firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                loginInterface.onSuccessReset();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loginInterface.onFailureReset(e);
            }
        });
    }


    public void createEmail(String email,String password, Context context){
        if(getUser() != null){
            sendLink(context);
        }else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    sendLink(context);
                }
            });
        }
    }

    private void sendLink(Context context) {
        getUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, context.getString(R.string.checkEmail), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void signUp(User user , ProgressDialog dialog, RegisterInterface registerInterface) {
        user.setId(getUser().getUid());
        firebaseStorage.getReference(Constants.USERS).child(Constants.PROFILES).child(user.getId())
                .child(user.getId() + ".png")
                .putFile(Uri.parse(user.getProfile())).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                user.setProfile(taskSnapshot.getUploadSessionUri().toString());
                savePatientFirestore(dialog, user, registerInterface);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                registerInterface.onFailure(e);
            }
        });
    }


    private void savePatientFirestore(Dialog dialog, User user, RegisterInterface registerInterface){
        firebaseFirestore.collection(Constants.USER+"/"+user.getId()).add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        updateUser(dialog, user, registerInterface);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                deleteImageLevel(Constants.USER, Constants.PROFILES, user.getId());
                registerInterface.onFailure(e);
            }
        });
    }

    private void updateUser(Dialog dialog,User user, RegisterInterface registerInterface){
        UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder()
                .setDisplayName(user.getName())
                .setPhotoUri( Uri.parse(user.getProfile())).build();
        getUser().updateProfile(userProfileChangeRequest)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        registerInterface.onSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                registerInterface.onFailure(e);
            }
        });
    }

    private void deleteImageLevel(String child, String child2, String child3){
        firebaseStorage.getReference(child).child(child2).child(child3).delete();
    }

    public void signOut(ProgressDialog progressDialog){
        firebaseAuth.signOut();
        progressDialog.dismiss();
    }
}