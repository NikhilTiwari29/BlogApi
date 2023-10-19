package com.example.blogappapis.services;

import com.example.blogappapis.payloads.CategoriesDto;

import java.util.List;

public interface CategoriesService {

    //add
    CategoriesDto createCategories(CategoriesDto categories);
    //update
    CategoriesDto updateCategories(CategoriesDto categories,Integer categoryId);
    //get categoryById
    CategoriesDto getCategoriesById(Integer categoryId);
    //get all categories
    List<CategoriesDto> getAllCategories();
    //delete category
    void deleteCategory(Integer categoryId);

}
