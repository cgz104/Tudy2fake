package com.tudi.tudiapp;

public class User {
    private String userID;
    private String email;
    private String userName;
    private String password;
    private int totalCorrectDone;
    private int totalDone;

    public User(){

    }

    public User(String userID, String userName, String email, String password, int totalCorrectDone, int totalDone) {
        this.userID = userID;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.totalCorrectDone = totalCorrectDone;
        this.totalDone = totalDone;
    }

    public String getEmail(){ return email; }

    public String getUserID(){ return userID; }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getTotalCorrectDone() {
        return totalCorrectDone;
    }

    public int getTotalDone() {
        return totalDone;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTotalCorrectDone(int totalCorrectDone) {
        this.totalCorrectDone = totalCorrectDone;
    }

    public void setTotalDone(int totalDone) {
        this.totalDone = totalDone;
    }
}
