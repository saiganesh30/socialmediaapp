package com.example.socialmedia.controllers;

public class PostRequest {
    private String postBody;
    private int userID;  // Note the naming convention for the variable

    // Constructor, getters, and setters
    public PostRequest() {}

    public PostRequest(String postBody, int userID) {
        this.postBody = postBody;
        this.userID = userID;
    }

    // Getters and setters
    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}

