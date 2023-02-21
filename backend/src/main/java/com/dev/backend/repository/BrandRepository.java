package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>{
  
}
