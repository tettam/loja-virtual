package com.dev.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entities.Image;
import com.dev.backend.repository.ImageRepository;

@Service
public class ImageService {

  @Autowired
  private ImageRepository repository;

  public List<Image> findAll(){
    return repository.findAll();
  }

  public  Image insert(Image obj){
    return repository.saveAndFlush(obj);
  }

  public Image update(Image obj){
    return repository.saveAndFlush(obj);
  }

  public void delete(Long id){
    repository.deleteById(id);
  }
}
