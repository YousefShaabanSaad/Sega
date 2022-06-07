package com.yousef.sega.listener;

import com.yousef.sega.model.Chat;
import com.yousef.sega.model.Game;

import java.util.List;

public interface GameInterface {
    void getGame(Game thisGame);

    void getChats(List<Chat> chats1);
}
