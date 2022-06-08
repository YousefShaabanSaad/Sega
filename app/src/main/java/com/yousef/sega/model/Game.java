package com.yousef.sega.model;

public class Game {
    private String Id, IdOwner,  IdPlayer, Player1, Player2, Status, React, IdReact;
    private int Number;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdOwner() {
        return IdOwner;
    }

    public void setIdOwner(String idOwner) {
        IdOwner = idOwner;
    }

    public String getIdPlayer() {
        return IdPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        IdPlayer = idPlayer;
    }

    public String getPlayer1() {
        return Player1;
    }

    public void setPlayer1(String player1) {
        Player1 = player1;
    }

    public String getPlayer2() {
        return Player2;
    }

    public void setPlayer2(String player2) {
        Player2 = player2;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getReact() {
        return React;
    }

    public void setReact(String react) {
        React = react;
    }

    public String getIdReact() {
        return IdReact;
    }

    public void setIdReact(String idReact) {
        IdReact = idReact;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }
}
