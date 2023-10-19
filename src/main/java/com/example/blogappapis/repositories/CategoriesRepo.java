package com.example.blogappapis.repositories;

import com.example.blogappapis.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepo extends JpaRepository<Categories,Integer> {
}
