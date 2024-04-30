package com.example.socialmedia.controllers;

public class CommentRequest {
    private String commentBody;
    private int postID;
    private int userID;

    // Constructor, getters, and setters
    public CommentRequest() {}

    public CommentRequest(String commentBody, int postID, int userID) {
        this.commentBody = commentBody;
        this.postID = postID;
        this.userID = userID;
    }

    // Getters and setters
    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}


