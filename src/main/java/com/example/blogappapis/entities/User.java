package com.example.blogappapis.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    @Size(min = 4,message = "username must be of minimum 4 character")
    private String name;

    @Email(message = "Email must be correct")
    private String email;

    @NotEmpty
    @Size(min = 4,max = 12,message = "password must be of minimum 4 character and maximum 12 characters")
    private String password;

    @NotEmpty
    @Column(length = 100)
    @Size(max = 100,message = "Must be under 100 characters")
    private String about;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Comments> comments = new HashSet<>();

}
