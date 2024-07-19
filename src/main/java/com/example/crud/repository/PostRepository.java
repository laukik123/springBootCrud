package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
