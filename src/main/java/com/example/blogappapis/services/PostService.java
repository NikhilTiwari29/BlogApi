package com.example.blogappapis.services;

import com.example.blogappapis.entities.Post;
import com.example.blogappapis.payloads.PostDto;
import com.example.blogappapis.payloads.PostDto;

import java.util.List;

public interface PostService{
    PostDto createPost (PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    PostDto getPostById(Integer postId);
    List<PostDto> getAllPost();
    void deletePost(Integer postId);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> searchPost(String keyword);
}
