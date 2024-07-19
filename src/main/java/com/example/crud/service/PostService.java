package com.example.crud.service;

import java.util.List;

import com.example.crud.payload.PostDto;

public interface PostService {
	PostDto createPost(PostDto postDto);
	
	List<PostDto> getPosts();
	
	PostDto updatePost(PostDto postDto, Long id);
	
	void deletePost(Long id);
}
