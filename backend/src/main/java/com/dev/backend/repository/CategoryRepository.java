package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  
}
