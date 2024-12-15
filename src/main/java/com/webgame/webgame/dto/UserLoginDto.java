package com.webgame.webgame.dto;

public class UserLoginDto {
    private String email;
    private String password;
    private String role;
    private String username;
    private String phone;

    public UserLoginDto(String email, String password, String role, String username, String phone) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.username = username;
        this.phone = phone;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}