package com.dev.backend.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entities.City;
import com.dev.backend.repository.CityRepository;

@Service
public class CityService {
  
  @Autowired
  private CityRepository repository;  

  public List<City> findAll(){
    return repository.findAll();
  }

  public City insert(City obj){
    char firstLetter = obj.getName().charAt(0);
    String newName = String.valueOf(firstLetter).toUpperCase() + obj.getName().substring(1);
    obj.setName(newName);
    obj.setCreatDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public City update(City obj){
    obj.setUpdateDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}
