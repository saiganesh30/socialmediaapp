package com.example.socialmedia.services;

import com.example.socialmedia.controllers.CommentNotFoundException;
import com.example.socialmedia.controllers.CommentResponse;
import com.example.socialmedia.controllers.PostNotFoundException;
import com.example.socialmedia.controllers.UserNotFoundException;
import com.example.socialmedia.models.Comment;
import com.example.socialmedia.models.Post;
import com.example.socialmedia.models.User;
import com.example.socialmedia.repositories.CommentRepository;
import com.example.socialmedia.repositories.PostRepository;
import com.example.socialmedia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    // Method to create a new comment
    public void createComment(String commentBody, int postID, int userID) {
        // Retrieve the user from the repository
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));

        // Retrieve the post from the repository
        Post post = postRepository.findById(postID)
                .orElseThrow(() -> new PostNotFoundException("Post does not exist"));

        // Create a new comment
        Comment comment = new Comment();
        comment.setCommentBody(commentBody);
        comment.setPost(post);
        comment.setUser(user);

        // Save the new comment to the repository
        commentRepository.save(comment);
    }

    // Method to retrieve an existing comment by commentID
    public CommentResponse getCommentById(int commentID) {
        // Retrieve the comment from the repository
        Optional<Comment> optionalComment = commentRepository.findById(commentID);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            // Create a CommentResponse object from the retrieved comment
            return new CommentResponse(comment);
        } else {
            // Throw an exception if the comment does not exist
            throw new CommentNotFoundException("Comment does not exist");
        }
    }

    // Method to edit an existing comment
    public void editComment(int commentID, String newCommentBody) {
        // Retrieve the comment from the repository
        Comment comment = commentRepository.findById(commentID)
                .orElseThrow(() -> new CommentNotFoundException("Comment does not exist"));

        // Update the comment body
        comment.setCommentBody(newCommentBody);

        // Save the updated comment to the repository
        commentRepository.save(comment);
    }

    // Method to delete an existing comment by commentID
    public void deleteComment(int commentID) {
        // Retrieve the comment from the repository
        Comment comment = commentRepository.findById(commentID)
                .orElseThrow(() -> new CommentNotFoundException("Comment does not exist"));

        // Delete the comment from the repository
        commentRepository.delete(comment);
    }
}
