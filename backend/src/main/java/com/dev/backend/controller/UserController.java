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

import com.dev.backend.entities.User;
import com.dev.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

  @Autowired
  private UserService service; 

  @GetMapping
  public ResponseEntity<List<User>> findAll(){
    List<User> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }

  @PostMapping
  public ResponseEntity<User> insert(@RequestBody @Valid User obj){
    obj= service.insert(obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping
  public ResponseEntity<User> update(@RequestBody @Valid User obj){
    obj = service.update(obj);
    return ResponseEntity.ok().body(obj);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
