package com.example.blogappapis.repositories;

import com.example.blogappapis.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comments,Integer> {
}
