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

import com.dev.backend.entities.State;
import com.dev.backend.service.StateService;


@RestController
@RequestMapping(value = "/api/states")
@CrossOrigin("http://localhost:3000")
public class StateController {

  @Autowired
  private StateService service;

  @GetMapping
  public ResponseEntity<List<State>> findAll(){
    List<State> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }

  @PostMapping
  public ResponseEntity<State> insert(@RequestBody State obj){
    obj = service.insert(obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping
  public ResponseEntity<State> update(@RequestBody State obj){
    obj = service.update(obj);
    return ResponseEntity.ok().body(obj);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
