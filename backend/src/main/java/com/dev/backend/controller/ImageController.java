package com.dev.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.backend.entities.Image;
import com.dev.backend.service.ImageService;

@RestController
@RequestMapping(value = "/api/images")
public class ImageController {
  
  @Autowired
  private ImageService service;

  @GetMapping
  public ResponseEntity<List<Image>> findAll(){
    List<Image> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }
  
  @PostMapping
  public ResponseEntity<Image> insert(@RequestParam("product_id") Long id, @RequestParam("file") MultipartFile file ){
    Image image = service.insert(id, file);
    return ResponseEntity.status(201).body(image);
  }

  @PutMapping
  public ResponseEntity<Image> update(@RequestBody Image obj){
    obj = service.update(obj);
    return ResponseEntity.ok().body(obj);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Image> delete(@PathVariable Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
