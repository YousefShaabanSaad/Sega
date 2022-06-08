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
import com.yousef.sega.model.Chat;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.Game;
import com.yousef.sega.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFirebase {
    private final FirebaseDatabase firebaseDatabase;
    private final FirebaseFirestore firebaseFirestore;
    private final FirebaseStorage firebaseStorage;
    private final FirebaseAuth firebaseAuth;
    private Game game;
    private List<Chat> chats;
    private List<User> users;

    public HomeFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
    }

    public String createNewGame(Game game) {
        String id = firebaseDatabase.getReference(Constants.GAMES).push().getKey();
        game.setId(id);
        firebaseDatabase.getReference(Constants.GAMES).child(game.getId()).setValue(game);
        return id;
    }

    public void updateGame(String id, String key, String value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        firebaseDatabase.getReference(Constants.GAMES).child(id).updateChildren(map);
    }

    public Game getGame(String id, GameInterface gameInterface) {
        game = new Game();
        firebaseDatabase.getReference(Constants.GAMES).child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot d : snapshot.getChildren()) {
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

    public void updatePlay(String id, int value) {
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.NUMBER, value);
        firebaseDatabase.getReference(Constants.GAMES).child(id).updateChildren(map);
    }

    public void deleteGame(String id) {
        firebaseDatabase.getReference(Constants.GAMES).child(id).removeValue();
    }

    public List<Chat> getChats(String id, GameInterface gameInterface) {
        chats = new ArrayList<>();
        firebaseDatabase.getReference(Constants.CHATS).child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot d : snapshot.getChildren()) {
                            Chat chat = d.getValue(Chat.class);
                            chats.add(chat);
                        }
                        gameInterface.getChats(chats);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
        return chats;
    }

    public void createNewChat(String id, Chat chat) {
        chat.setId(firebaseDatabase.getReference(Constants.CHATS).child(id).push().getKey());
        firebaseDatabase.getReference(Constants.CHATS).child(id).child(chat.getId()).setValue(chat);
    }

    public List<User> getUsers(String id, GameInterface gameInterface) {
        users = new ArrayList<>();
        firebaseDatabase.getReference(Constants.PARTICIPANTS).child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot d : snapshot.getChildren()) {
                            User user = d.getValue(User.class);
                            users.add(user);
                        }
                        gameInterface.getParticipants(users);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
        return users;
    }

    public void createNewParticipants(String id, User user) {
        firebaseDatabase.getReference(Constants.CHATS).child(id).child(user.getId()).setValue(user);
    }

    public void deleteChat(String id) {
        firebaseDatabase.getReference(Constants.CHATS).child(id).removeValue();
    }

    public void deleteParticipants(String id) {
        firebaseDatabase.getReference(Constants.PARTICIPANTS).child(id).removeValue();
    }
}
