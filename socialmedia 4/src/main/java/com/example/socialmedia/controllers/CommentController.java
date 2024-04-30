package com.example.socialmedia.controllers;

import com.example.socialmedia.models.jsonerror;
import com.example.socialmedia.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    // Endpoint to create a new comment
    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest) {
        try {
            // Use the service to create the comment
            commentService.createComment(commentRequest.getCommentBody(), commentRequest.getPostID(), commentRequest.getUserID());

            // Return a success response
            return ResponseEntity.ok("Comment created successfully");
        } catch (UserNotFoundException e) {
            // Return an error response if the user does not exist
            jsonerror error = new jsonerror("User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (PostNotFoundException e) {
            // Return an error response if the post does not exist
            jsonerror error = new jsonerror("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Endpoint to retrieve an existing comment by commentID
    @GetMapping("/comment")
    public ResponseEntity<?> getComment(@RequestParam("commentID") int commentID) {
        try {
            // Use the service to retrieve the comment details
            CommentResponse commentResponse = commentService.getCommentById(commentID);

            // Return the comment details in the response
            return ResponseEntity.ok(commentResponse);
        } catch (CommentNotFoundException e) {
            // Return an error response if the comment does not exist
            jsonerror error = new jsonerror("Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Endpoint to edit an existing comment
    @PatchMapping("/comment")
    public ResponseEntity<?> editComment(@RequestBody EditCommentRequest editCommentRequest) {
        try {
            // Use the service to edit the comment
            commentService.editComment(editCommentRequest.getCommentID(), editCommentRequest.getCommentBody());

            // Return a success response
            return ResponseEntity.ok("Comment edited successfully");
        } catch (CommentNotFoundException e) {
            // Return an error response if the comment does not exist
            jsonerror error = new jsonerror("Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Endpoint to delete an existing comment
    @DeleteMapping("/comment")
    public ResponseEntity<?> deleteComment(@RequestParam("commentID") int commentID) {
        try {
            // Use the service to delete the comment
            commentService.deleteComment(commentID);

            // Return a success response
            return ResponseEntity.ok("Comment deleted");
        } catch (CommentNotFoundException e) {
            // Return an error response if the comment does not exist
            jsonerror error = new jsonerror("Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
