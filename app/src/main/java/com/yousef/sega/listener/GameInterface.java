package com.yousef.sega.listener;

import com.yousef.sega.model.Chat;
import com.yousef.sega.model.Game;
import com.yousef.sega.model.User;

import java.util.List;

public interface GameInterface {
    void getGame(Game thisGame);
    void getChats(List<Chat> chats1);
    void getParticipants(List<User> participants);
}
