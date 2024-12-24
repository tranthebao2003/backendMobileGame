package com.webgame.webgame.dto;

public class ReviewRequest {
    private String userEmail;
    private String gameName;
    private Integer score;
    private String comment;

    public ReviewRequest() {
    }

    public ReviewRequest(String userEmail, String gameName, Integer score, String comment) {
        this.userEmail = userEmail;
        this.gameName = gameName;
        this.score = score;
        this.comment = comment;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
