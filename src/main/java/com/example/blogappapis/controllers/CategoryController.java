package com.example.blogappapis.controllers;

import com.example.blogappapis.payloads.CategoriesDto;
import com.example.blogappapis.services.CategoriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoriesService categoriesService;

    //Creating categories
    @PostMapping("/")
    public ResponseEntity<CategoriesDto> addCategories(@Valid @RequestBody CategoriesDto categoriesDto){
        CategoriesDto categories = this.categoriesService.createCategories(categoriesDto);
        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }

    //Updating categories
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoriesDto> updateCategories(@Valid @RequestBody CategoriesDto categoriesDto,@PathVariable Integer categoryId){
        CategoriesDto category = this.categoriesService.updateCategories(categoriesDto,categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    //Updating categories
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoriesDto> getCategoryById(@PathVariable Integer categoryId){
        CategoriesDto categories = this.categoriesService.getCategoriesById(categoryId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //get all categories
    @GetMapping("/")
    public ResponseEntity<List<CategoriesDto>> getAllCategory(){
        List<CategoriesDto> categories = this.categoriesService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //delete categories
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId){
        this.categoriesService.deleteCategory(categoryId);
        return new ResponseEntity<>(Map.of("message","user deleted successfully"), HttpStatus.OK);
    }

}
