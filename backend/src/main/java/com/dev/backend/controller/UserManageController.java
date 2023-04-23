package com.dev.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.entities.User;
import com.dev.backend.service.UserManageService;

@RestController
@RequestMapping(value = "/api/user-manage")
public class UserManageController {

  @Autowired
  private UserManageService userManageService; 

  @PostMapping(value = "/cod-password")
  public ResponseEntity<String> recoveryCod(@RequestBody User user){
    userManageService.cod(user.getEmail());
    return ResponseEntity.ok().build();
  }

  @PostMapping(value = "/change-password")
  public ResponseEntity<String> recoveryPassword(@RequestBody User user){
    userManageService.changePassword(user);
    return ResponseEntity.ok().build();
  }
}
