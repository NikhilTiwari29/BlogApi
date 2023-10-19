package com.example.blogappapis.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Categories {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;
    @NotNull
    private String categoryTitle;
    @NotNull
    @Size(min = 10,max = 1000,message = "description must contain minimum 10 characters and maximum 1000 characters")
    @Column(length = 1000)
    private String categoryDescription;
    @OneToMany(mappedBy = "categories",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> post = new ArrayList<>();


}
