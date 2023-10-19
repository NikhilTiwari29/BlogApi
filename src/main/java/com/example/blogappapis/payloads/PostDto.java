package com.example.blogappapis.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
}
