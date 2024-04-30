package com.example.socialmedia.controllers;

import com.example.socialmedia.models.jsonerror;
import com.example.socialmedia.repositories.UserRepository;
import com.example.socialmedia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public String signup(@RequestBody UserRequest userRequest) {
        try {
            userService.createUser(userRequest.getEmail(), userRequest.getName(), userRequest.getPassword());
            return "Account Creation Successful";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest userRequest) {
        try {
            userService.loginUser(userRequest.getEmail(), userRequest.getPassword());
            return "Login Successful";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    // Endpoint to retrieve user details by userID
    @GetMapping("/user")
    public ResponseEntity<?> getUserDetail(@RequestParam("userID") int userID) {
        try {
            // Use the service to retrieve user details
            UserDetailResponse userDetail = userService.getUserDetailById(userID);

            // Return the user details in the response
            return ResponseEntity.ok(userDetail);
        } catch (UserNotFoundException e) {
            // Return an error response if the user does not exist
            jsonerror error = new jsonerror("User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Endpoint to retrieve all existing users
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        // Use the service to retrieve all users
        List<UserResponse> userResponses = userService.getAllUsers();

        // Return the list of users in the response
        return ResponseEntity.ok(userResponses);
    }
}