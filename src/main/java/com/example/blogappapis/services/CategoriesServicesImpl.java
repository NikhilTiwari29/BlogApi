package com.example.blogappapis.services;

import com.example.blogappapis.entities.Categories;
import com.example.blogappapis.exception.ResourceNotFoundException;
import com.example.blogappapis.payloads.CategoriesDto;
import com.example.blogappapis.repositories.CategoriesRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesServicesImpl implements CategoriesService{

    @Autowired
    private CategoriesRepo categoriesRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoriesDto createCategories(CategoriesDto categoriesDto) {
        Categories categories = this.modelMapper.map(categoriesDto, Categories.class);
        Categories savedCategories = this.categoriesRepo.save(categories);
        return this.modelMapper.map(savedCategories,CategoriesDto.class);
    }

    @Override
    public CategoriesDto updateCategories(CategoriesDto categoriesDto, Integer categoryId) {
        Categories fetchedCategories = this.categoriesRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        fetchedCategories.setCategoryTitle(categoriesDto.getCategoryTitle());
        fetchedCategories.setCategoryDescription(categoriesDto.getCategoryDescription());
        Categories updatedCategory = this.categoriesRepo.save(fetchedCategories);
        return this.modelMapper.map(updatedCategory, CategoriesDto.class);
    }

    @Override
    public CategoriesDto getCategoriesById(Integer categoryId) {
        Categories category = this.categoriesRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        return this.modelMapper.map(category, CategoriesDto.class);
    }

    @Override
    public List<CategoriesDto> getAllCategories() {
        List<Categories> categories = this.categoriesRepo.findAll();
        List<CategoriesDto> allCategories = categories.stream().map((categories1 -> this.modelMapper.map(categories1, CategoriesDto.class))).collect(Collectors.toList());
        return allCategories;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        System.out.println("testnik"+categoryId);
        Categories toBeDelete = this.categoriesRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
         this.categoriesRepo.delete(toBeDelete);
    }
}
