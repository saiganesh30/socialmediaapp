package com.example.socialmedia.controllers;

import com.example.socialmedia.models.Comment;

public class CommentResponse {
    private int commentID;
    private String commentBody;
    private int userID;
    private String userName;

    // Constructor that takes a Comment object as input
    public CommentResponse(Comment comment) {
        this.commentID = comment.getCommentID();
        this.commentBody = comment.getCommentBody();
        this.userID = comment.getUser().getUserID();
        this.userName = comment.getUser().getName();
    }


    // Getters and setters for CommentResponse properties
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}