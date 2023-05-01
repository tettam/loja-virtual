package com.dev.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.dto.UserClientRequestDTO;
import com.dev.backend.entities.User;
import com.dev.backend.service.UserClientService;

@RestController
@RequestMapping(value = "/api/clients")
@CrossOrigin("http://localhost:3000")
public class UserClientController {

  @Autowired
  private UserClientService service; 

  @PostMapping
  public ResponseEntity<User> insert(@RequestBody UserClientRequestDTO obj){
    User newUser = service.insert(obj);
    return ResponseEntity.ok().body(newUser);
  }
}
