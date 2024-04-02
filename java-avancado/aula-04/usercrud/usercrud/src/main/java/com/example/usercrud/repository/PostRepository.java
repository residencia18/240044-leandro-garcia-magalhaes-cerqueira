package com.example.usercrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usercrud.model.Post;

public interface PostRepository extends JpaRepository <Post, Long> {

}
