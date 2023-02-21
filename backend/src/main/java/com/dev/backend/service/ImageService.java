package com.dev.backend.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev.backend.entities.Image;
import com.dev.backend.repository.ImageRepository;


public class ImageService {
  
  @Autowired
  private ImageRepository repository;   

  public List<Image> findAll(){
    return repository.findAll();
  }

  public Image insert(Image obj){
    obj.setCreatDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public Image update(Image obj){
    obj.setUpdateDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  } 
}
