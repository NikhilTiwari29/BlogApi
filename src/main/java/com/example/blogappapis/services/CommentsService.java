package com.example.blogappapis.services;

import com.example.blogappapis.payloads.CommentsDto;

public interface CommentsService {

    CommentsDto createComment(CommentsDto commentsDto,Integer postId);
    void deleteComment(Integer commentId);
}
