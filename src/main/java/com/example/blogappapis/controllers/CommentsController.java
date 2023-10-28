package com.example.blogappapis.controllers;

import com.example.blogappapis.Response.ApiResponse;
import com.example.blogappapis.payloads.CommentsDto;
import com.example.blogappapis.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;
    @PostMapping("/post/{postId}/user/{userId}/comment")
    public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto commentsDto, @PathVariable Integer postId, @PathVariable Integer userId){
        CommentsDto comment = this.commentsService.createComment(commentsDto, postId,userId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentsService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully",true), HttpStatus.OK);
    }
}
