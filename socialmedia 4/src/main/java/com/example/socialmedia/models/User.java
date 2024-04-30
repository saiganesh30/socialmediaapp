package com.example.socialmedia.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"user\"") // Escape the table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;


    private String email;


    private String name;


    private String password;

    // Default constructor (required by JPA)
    public User() {
    }

    // Constructor for initializing new user
    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    // Getters and setters

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

