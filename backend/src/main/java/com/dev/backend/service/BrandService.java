package com.dev.backend.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entities.Brand;
import com.dev.backend.repository.BrandRepository;

@Service
public class BrandService {
  
  @Autowired
  private BrandRepository repository;

  public List<Brand> findAll(){
    return repository.findAll();
  }

  public  Brand insert(Brand obj){
    obj.setCreatDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public Brand update(Brand obj){
    obj.setUpdateDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public void delete(Long id){
    repository.deleteById(id);
  }
}
