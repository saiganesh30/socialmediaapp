package com.example.socialmedia.controllers;

import com.example.socialmedia.models.jsonerror;
import com.example.socialmedia.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    // Endpoint to create a new post
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        try {
            // Use the service to create a new post
            postService.createPost(postRequest.getPostBody(), postRequest.getUserID());

            // Return a success response
            return ResponseEntity.ok("Post created successfully");
        } catch (UserNotFoundException e) {
            // Return an error response if the user does not exist
            jsonerror error = new jsonerror("User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Endpoint to retrieve an existing post by postID
    @GetMapping("/post")
    public ResponseEntity<?> getPost(@RequestParam("postID") int postID) {
        try {
            // Use the service to retrieve the post details
            PostResponse postResponse = postService.getPostById(postID);

            // Return the post details in the response
            return ResponseEntity.ok(postResponse);
        } catch (PostNotFoundException e) {
            // Return an error response if the post does not exist
            jsonerror error = new jsonerror("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Endpoint to edit an existing post
    @PatchMapping("/post")
    public ResponseEntity<?> editPost(@RequestBody EditPostRequest editPostRequest) {
        try {
            // Use the service to edit the post
            postService.editPost(editPostRequest.getPostID(), editPostRequest.getPostBody());

            // Return a success response
            return ResponseEntity.ok("Post edited successfully");
        } catch (PostNotFoundException e) {
            // Return an error response if the post does not exist
            jsonerror error = new jsonerror("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Endpoint to delete an existing post
    @DeleteMapping("/post")
    public ResponseEntity<?> deletePost(@RequestParam("postID") int postID) {
        try {
            // Use the service to delete the post
            postService.deletePost(postID);

            // Return a success response
            return ResponseEntity.ok("Post deleted");
        } catch (PostNotFoundException e) {
            // Return an error response if the post does not exist
            jsonerror error = new jsonerror("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Endpoint to retrieve all posts by all users
    @GetMapping("/")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        // Use the service to retrieve all posts
        List<PostResponse> postResponses = postService.getAllPosts();

        // Return the list of posts in the response
        return ResponseEntity.ok(postResponses);
    }
}
