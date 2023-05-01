package com.dev.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.entities.Brand;
import com.dev.backend.service.BrandService;

@RestController
@RequestMapping(value = "/api/brands")
@CrossOrigin("http://localhost:3000")
public class BrandController {

  @Autowired
  private BrandService service;

  @GetMapping
  public ResponseEntity<List<Brand>> findAll(){
    List<Brand> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }
  
  @PostMapping
  public ResponseEntity<Brand> insert(@RequestBody Brand obj){
    obj = service.insert(obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping
  public ResponseEntity<Brand> update(@RequestBody Brand obj){
    obj = service.update(obj);
    return ResponseEntity.ok().body(obj);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Brand> delete(@PathVariable Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
