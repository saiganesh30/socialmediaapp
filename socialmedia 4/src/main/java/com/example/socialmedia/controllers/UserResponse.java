package com.example.socialmedia.controllers;

import com.example.socialmedia.models.User;

public class UserResponse {
    private int userID;
    private String name;
    private String email;


    public UserResponse(User user) {
        this.userID = user.getUserID();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    // Getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

