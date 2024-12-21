package com.webgame.webgame.dto;

//gửi đi
public class UserResponse {
    private String username;
    private String email;
    private String password;
    private String phone;
    private boolean checkLogin;

    public UserResponse(String username, String email, String password, String phone, boolean checkLogin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.checkLogin = checkLogin;
    }

    public UserResponse() {
    }

    public boolean isCheckLogin() {
        return checkLogin;
    }

    public void setCheckLogin(boolean checkLogin) {
        this.checkLogin = checkLogin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getters và Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

