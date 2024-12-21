package com.webgame.webgame.dto;

//nhận về
public class UserRequest {
    private String email;
    private String password;

    // Getters và Setters
    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
