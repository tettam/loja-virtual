package com.dev.backend.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entities.Product;
import com.dev.backend.repository.ProductRepository;

@Service
public class ProductService {
    
  @Autowired
  private ProductRepository repository;
  
  public List<Product> findAll(){
    return repository.findAll();
  }

  public Product insert(Product obj){
    obj.setCreatDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public Product update(Product obj){
    obj.setUpdateDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public void delete(Long id){
    repository.deleteById(id);
  }
}
