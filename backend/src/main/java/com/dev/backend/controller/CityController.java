package com.dev.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.dev.backend.entities.City;
import com.dev.backend.service.CityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value="/api/cities")
public class CityController {
  
  @Autowired
  private CityService service;

  @GetMapping
  public ResponseEntity<List<City>> findAll(){
    List<City> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }

  @PostMapping
  public ResponseEntity<City> insert(@RequestBody City city){
    city = service.insert(city);
    return ResponseEntity.ok().body(city);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<City> update(@RequestBody City city){
    city = service.update(city);
    return ResponseEntity.ok().body(city);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<City> delete(@PathVariable Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
