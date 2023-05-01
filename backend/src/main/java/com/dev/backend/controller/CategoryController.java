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

import com.dev.backend.entities.Category;
import com.dev.backend.service.CategoryService;

@RestController
@RequestMapping(value = "/api/categories")
@CrossOrigin("http://localhost:3000")
public class CategoryController {

  @Autowired
  private CategoryService service;

  @GetMapping
  public ResponseEntity<List<Category>> findAll(){
    List<Category> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }
  
  @PostMapping
  public ResponseEntity<Category> insert(@RequestBody Category obj){
    obj = service.insert(obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping
  public ResponseEntity<Category> update(@RequestBody Category obj){
    obj = service.update(obj);
    return ResponseEntity.ok().body(obj);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Category> delete(@PathVariable Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
 
}
