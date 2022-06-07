package com.yousef.sega.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.Game;

public class HomeFirebase {
    private final FirebaseDatabase firebaseDatabase;
    private final FirebaseFirestore firebaseFirestore;
    private final FirebaseStorage firebaseStorage;
    private final FirebaseAuth firebaseAuth;
    private String id;

    public HomeFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
    }

    public String createNewGame(Game game){
        String id = firebaseDatabase.getReference(Constants.GAMES).push().toString();
        game.setId(id);
        firebaseDatabase.getReference(Constants.GAMES).child(id).setValue(game);
        return id;
    }

}
