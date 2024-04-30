package com.example.socialmedia.services;

import com.example.socialmedia.controllers.PostNotFoundException;
import com.example.socialmedia.controllers.PostResponse;
import com.example.socialmedia.controllers.UserNotFoundException;
import com.example.socialmedia.models.Post;
import com.example.socialmedia.models.User;
import com.example.socialmedia.repositories.PostRepository;
import com.example.socialmedia.repositories.UserRepository;
import com.example.socialmedia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Method to create a new post
    public void createPost(String postBody, int userID) {
        // Retrieve the user from the repository
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));

        // Create a new post
        Post post = new Post();
        post.setPostBody(postBody);
        post.setDate(LocalDate.now());
        post.setUser(user);

        // Save the new post to the repository
        postRepository.save(post);
    }

    // Method to retrieve an existing post by postID
    public PostResponse getPostById(int postID) {
        // Retrieve the post from the repository
        Optional<Post> optionalPost = postRepository.findById(postID);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            // Create a PostResponse object from the retrieved post
            return new PostResponse(post);
        } else {
            // Throw an exception if the post does not exist
            throw new PostNotFoundException("Post does not exist");
        }
    }

    // Method to edit an existing post
    public void editPost(int postID, String newPostBody) {
        // Retrieve the post from the repository
        Post post = postRepository.findById(postID)
                .orElseThrow(() -> new PostNotFoundException("Post does not exist"));

        // Update the post body
        post.setPostBody(newPostBody);

        // Save the updated post to the repository
        postRepository.save(post);
    }

    // Method to delete an existing post by postID
    public void deletePost(int postID) {
        // Retrieve the post from the repository
        Post post = postRepository.findById(postID)
                .orElseThrow(() -> new PostNotFoundException("Post does not exist"));

        // Delete the post from the repository
        postRepository.delete(post);
    }
    // Method to retrieve all posts by all users in reverse chronological order
    public List<PostResponse> getAllPosts() {
        // Retrieve all posts sorted by date in descending order
        List<Post> posts = postRepository.findAllByOrderByDateDesc();

        // Convert each Post entity to PostResponse DTO using a map function
        return posts.stream()
                .map(post -> new PostResponse(post))
                .collect(Collectors.toList());
    }
}
