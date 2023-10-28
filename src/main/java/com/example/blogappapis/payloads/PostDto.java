package com.example.blogappapis.payloads;

import com.example.blogappapis.entities.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private int postId;
    private String postTitle;
    private String postContent;
    private String postImageName;
    private Date addedDate = new Date();
    private UserDto user;
    private CategoriesDto categories;
    private Set<CommentsDto> comments = new HashSet<>();
}
