package com.example.socialmedia.controllers;

import com.example.socialmedia.models.Post;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import com.example.socialmedia.models.Comment;
import com.example.socialmedia.models.Post;

public class PostResponse {
    private int postID;
    private String postBody;
    private LocalDate date;
    private List<CommentResponse> comments;

    // Constructor
    public PostResponse(Post post) {
        this.postID = post.getPostID();
        this.postBody = post.getPostBody();
        this.date = post.getDate();

        // Convert comments to CommentResponse DTOs
        this.comments = post.getComments().stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

    // Getters and setters for PostResponse properties
    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}