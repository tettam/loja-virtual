package com.dev.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.dto.UserClientRequestDTO;
import com.dev.backend.entities.User;
import com.dev.backend.service.UserService;

@RestController
@RequestMapping(value = "/api/clients")
public class UserClientController {

  @Autowired
  private UserService service; 

  @PostMapping
  public ResponseEntity<User> insert(@RequestBody UserClientRequestDTO obj){
    User user = new UserClientRequestDTO().convertUser(obj);
    user = service.insert(user);
    return ResponseEntity.ok().body(user);
  }
}
