package com.example.blogappapis.services;

import com.example.blogappapis.Response.PostResponse;
import com.example.blogappapis.entities.Categories;
import com.example.blogappapis.entities.Post;
import com.example.blogappapis.entities.User;
import com.example.blogappapis.exception.ResourceNotFoundException;
import com.example.blogappapis.payloads.PostDto;
import com.example.blogappapis.repositories.CategoriesRepo;
import com.example.blogappapis.repositories.PostRepo;
import com.example.blogappapis.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoriesRepo categoriesRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        Post post = this.modelMapper.map(postDto, Post.class);
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        Categories categories = this.categoriesRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        post.setPostImageName("default.png");
        post.setPostAddeDate(new Date());
        post.setUser(user);
        post.setCategories(categories);
        this.postRepo.save(post);
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post oldPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("user", "id", postId));
        oldPost.setPostTitle(postDto.getPostTitle());
        oldPost.setPostContent(postDto.getPostContent());
        oldPost.setPostImageName(postDto.getPostImageName());
        Post updatedPost = this.postRepo.save(oldPost);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post getPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("user", "id", postId));
        return this.modelMapper.map(getPost,PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort = null;
        if (sortDir.equals("ASC")||sortDir.equals("asc")){
            sort=Sort.by(sortBy).ascending();
        }
        else{
            sort=Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> postPage = this.postRepo.findAll(pageable);
        List<PostDto> postDtos = postPage
                .stream()
                .map(post -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;
    }

    @Override
    public void deletePost(Integer postId) {
        Post toDeletePost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("postId", "id", postId));
        this.postRepo.delete(toDeletePost);
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId", "id", userId));
        List<Post>  posts = this.postRepo.findByUser(user);
        return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Categories categories = this.categoriesRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("categoryId", "id", categoryId));
        List<Post>  posts = this.postRepo.findByCategories(categories);
        return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> byTitleContaining = this.postRepo.findBypostTitleContaining(keyword);
        return byTitleContaining.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
