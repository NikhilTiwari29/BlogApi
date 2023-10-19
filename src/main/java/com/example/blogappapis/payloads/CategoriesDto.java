package com.example.blogappapis.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoriesDto {
    private int categoryId;
    private String categoryTitle;
    private String categoryDescription;
}
