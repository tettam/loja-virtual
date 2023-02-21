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

  public City insert(City city){
    city.setCreatDate(Instant.now());
    return repository.saveAndFlush(city);
  }

  public City update(City city){
    city.setUpdateDate(Instant.now());
    return repository.saveAndFlush(city);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}
