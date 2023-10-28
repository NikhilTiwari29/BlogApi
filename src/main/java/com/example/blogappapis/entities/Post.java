package com.example.blogappapis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;
    private String postTitle;
    private String postContent;
    private String postImageName;
    private Date postAddeDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Categories categories;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comments> comments = new HashSet<>();

}
