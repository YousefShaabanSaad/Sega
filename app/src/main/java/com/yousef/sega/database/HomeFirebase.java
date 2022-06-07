package com.yousef.sega.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.yousef.sega.listener.GameInterface;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.Game;

import java.util.HashMap;
import java.util.Map;

public class HomeFirebase {
    private final FirebaseDatabase firebaseDatabase;
    private final FirebaseFirestore firebaseFirestore;
    private final FirebaseStorage firebaseStorage;
    private final FirebaseAuth firebaseAuth;
    private Game game;

    public HomeFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
    }

    public String createNewGame(Game game){
        String id = firebaseDatabase.getReference(Constants.GAMES).push().getKey();
        game.setId(id);
        firebaseDatabase.getReference(Constants.GAMES).child(id).setValue(game).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("joo","no");
            }
        });
        return id;
    }

    public void updateGame(String id, String key, String value){
        Map<String, Object> map=new HashMap<>();
        map.put(key, value);
        firebaseDatabase.getReference(Constants.GAMES).child(id).updateChildren(map);
    }

    public Game getGame(String id, GameInterface gameInterface){
        game =new Game();
        firebaseDatabase.getReference(Constants.GAMES).child(id)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d:snapshot.getChildren()) {
                    game = d.getValue(Game.class);
                    gameInterface.getGame(game);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return game;
    }

    public void updatePlay(String id, int value){
        Map<String, Object> map=new HashMap<>();
        map.put(Constants.NUMBER, value);
        firebaseDatabase.getReference(Constants.GAMES).child(id).updateChildren(map);
    }

    public void deleteGame(String id){
        firebaseDatabase.getReference(Constants.GAMES).child(id).removeValue();
    }

}
