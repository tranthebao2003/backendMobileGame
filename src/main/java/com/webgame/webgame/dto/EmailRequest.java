package com.webgame.webgame.dto;

public class EmailRequest {
    private String email;

    // Constructor
    public EmailRequest(String email) {
        this.email = email;
    }
    public EmailRequest() {
    }


    // Getter and Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

