package com.dev.backend.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entities.Category;
import com.dev.backend.repository.CategoryRepository;

@Service
public class CategoryService {
    
  @Autowired
  private CategoryRepository repository;

  public List<Category> findAll(){
    return repository.findAll();
  }

  public  Category insert(Category obj){
    obj.setCreatDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public Category update(Category obj){
    obj.setUpdateDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public void delete(Long id){
    repository.deleteById(id);
  }
}
