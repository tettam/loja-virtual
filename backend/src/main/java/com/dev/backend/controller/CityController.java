package com.dev.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.dev.backend.entities.City;
import com.dev.backend.service.CityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value="/api/cities")
@CrossOrigin("http://localhost:3000")
public class CityController {
  
  @Autowired
  private CityService service;

  @GetMapping
  public ResponseEntity<List<City>> findAll(){
    List<City> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }

  @PostMapping
  public ResponseEntity<City> insert(@RequestBody City obj){
    obj = service.insert(obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping
  public ResponseEntity<City> update(@RequestBody City obj){
    obj = service.update(obj);
    return ResponseEntity.ok().body(obj);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<City> delete(@PathVariable Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
