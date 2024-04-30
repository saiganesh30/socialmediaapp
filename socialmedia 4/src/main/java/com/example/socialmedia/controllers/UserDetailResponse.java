package com.example.socialmedia.controllers;

import com.example.socialmedia.models.Post;
import java.util.List;

public class UserDetailResponse {
    private int userID;
    private String name;
    private String email;
    private List<Post> posts;

    // Constructor, getters, and setters
    public UserDetailResponse(int userID, String name, String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
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