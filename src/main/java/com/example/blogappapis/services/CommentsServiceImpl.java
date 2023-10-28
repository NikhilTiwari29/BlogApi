package com.example.blogappapis.services;

import com.example.blogappapis.entities.Comments;
import com.example.blogappapis.entities.Post;
import com.example.blogappapis.exception.ResourceNotFoundException;
import com.example.blogappapis.payloads.CommentsDto;
import com.example.blogappapis.repositories.CommentsRepo;
import com.example.blogappapis.repositories.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService{

    @Autowired
    private CommentsRepo commentsRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepo postRepo;
    @Override
    public CommentsDto createComment(CommentsDto commentsDto, Integer postId) {
        Post oldPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("user", "id", postId));
        Comments comments = this.modelMapper.map(commentsDto, Comments.class);
        comments.setPost(oldPost);
        Comments savedComment = this.commentsRepo.save(comments);
        CommentsDto commentDto1 = this.modelMapper.map(savedComment, CommentsDto.class);
        return commentDto1;
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comments comments = this.commentsRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        this.commentsRepo.delete(comments);
    }
}
