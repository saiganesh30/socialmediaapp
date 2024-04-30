package com.example.socialmedia.repositories;
import com.example.socialmedia.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p ORDER BY p.date DESC")
    List<Post> findAllByOrderByDateDesc();
}

