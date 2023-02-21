package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
  
}
