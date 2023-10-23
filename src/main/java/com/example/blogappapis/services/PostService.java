package com.example.blogappapis.services;

import com.example.blogappapis.Response.PostResponse;
import com.example.blogappapis.payloads.PostDto;

import java.util.List;

public interface PostService{
    PostDto createPost (PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    PostDto getPostById(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy);
    void deletePost(Integer postId);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> searchPost(String keyword);
}
