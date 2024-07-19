package com.example.crud.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.entity.Post;
import com.example.crud.exception.PostNotFoundException;
import com.example.crud.payload.PostDto;
import com.example.crud.repository.PostRepository;
import com.example.crud.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = mapToEntity(postDto);
		
		Post newPost = postRepository.save(post);
		
		PostDto postResponse = mapToDto(newPost);
		return postResponse;
	}

	@Override
	public List<PostDto> getPosts() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		
	}
	
	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("post not found"));
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		post.setTitle(postDto.getTitle());
		
		Post updatedPost = postRepository.save(post);
		return mapToDto(updatedPost);
	}
	
	@Override
	public void deletePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("post not found"));
		postRepository.deleteById(id);
		
	}
	
	private PostDto mapToDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setDescription(post.getDescription());
		
		return postDto;
	}
	
	private Post mapToEntity(PostDto postDto) {
		Post post = new Post();
		post.setId(postDto.getId());
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		
		return post;
	}

	

	
	
}
