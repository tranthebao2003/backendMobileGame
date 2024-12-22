package com.webgame.webgame.dto;


import java.io.Serializable;
import java.util.List;

public class OrderInfo implements Serializable {
    private List<Long> gameId;
    private List<String> gameName;
    private Double sumprice;
    private String userEmail;

    public Double getSumprice() {
        return sumprice;
    }

    public void setSumprice(Double sumprice) {
        this.sumprice = sumprice;
    }

    public List<String> getGameName() {
        return gameName;
    }

    public void setGameName(List<String> gameName) {
        this.gameName = gameName;
    }

    public List<Long> getGameId() {
        return gameId;
    }

    public void setGameId(List<Long> gameId) {
        this.gameId = gameId;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}

