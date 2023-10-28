package com.example.blogappapis.controllers;

import com.example.blogappapis.Response.PostResponse;
import com.example.blogappapis.payloads.PostDto;
import com.example.blogappapis.services.FileService;
import com.example.blogappapis.services.PostService;
import com.example.blogappapis.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class PostsController {

    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String ImagePath;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto post = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto post = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Integer userId){
        List<PostDto> postByUser = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(postByUser, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Integer categoryId){
        List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postByCategory, HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGENUMBER,required = false) Integer pageNumber,
                                                     @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGESIZE,required = false)Integer pageSize,
                                                    @RequestParam(value = "sortBy",defaultValue = AppConstant.SORTBY,required = false)String sortBy,
                                                    @RequestParam(value = "sortDir",defaultValue = AppConstant.SORTDIR,required = false)String sortDir){
        PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postByCategory = this.postService.getPostById(postId);
        return new ResponseEntity<>(postByCategory, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(Map.of("Message","Post has been deleted"), HttpStatus.OK);
    }

    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<List<PostDto>> getPostByKeyword(@PathVariable String keyword){
        List<PostDto> searchedPost = this.postService.searchPost(keyword);
        return new ResponseEntity<>(searchedPost, HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(@RequestParam MultipartFile image,@PathVariable Integer postId) throws IOExcepti on {
        PostDto postById = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(ImagePath, image);
        postById.setPostImageName(fileName);
        PostDto updatedPost = this.postService.updatePost(postById, postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }
}
