package com.webgame.webgame.dto;

import java.math.BigDecimal;

public class ItemOrderList {
    private String gamename;
    private String account;
    private String pass;
    private String price;

    public ItemOrderList(String gamename, String account, String pass, String price) {
        this.gamename = gamename;
        this.account = account;
        this.pass = pass;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ItemOrderList() {
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
