package com.example.socialmedia.services;

import com.example.socialmedia.controllers.*;
import com.example.socialmedia.models.User;
import com.example.socialmedia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(String email, String name, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        return userRepository.save(user);
    }
    // Method to retrieve user details by userID
    public UserDetailResponse getUserDetailById(int userID) {
        // Retrieve the user from the repository
        Optional<User> optionalUser = userRepository.findById(userID);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Create a UserDetailResponse object from the retrieved user
            UserDetailResponse userDetail = new UserDetailResponse(user.getUserID(), user.getName(), user.getEmail());

            // Return the user detail
            return userDetail;
        } else {
            // Throw an exception if the user does not exist
            throw new UserNotFoundException("User does not exist");
        }
    }


    // Method to retrieve all existing users
    public List<UserResponse> getAllUsers() {
        // Retrieve all users from the repository
        List<User> users = userRepository.findAll();

        // Convert each User entity to UserResponse DTO using a map function
        return users.stream()
                .map(user -> new UserResponse(user))
                .collect(Collectors.toList());
    }

    public User loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        } else if (!user.isPresent()) {
            throw new RuntimeException("User does not exist");
        } else {
            throw new RuntimeException("Username/Password incorrect");
        }
    }

}


