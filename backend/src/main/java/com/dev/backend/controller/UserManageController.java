package com.dev.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.service.UserManageService;

@RestController
@RequestMapping(value = "/api/user-manage")
public class UserManageController {

  @Autowired
  private UserManageService userManageService; 

  @PostMapping
  public ResponseEntity<String> recoveryPassword(@RequestParam("email") String email ){
    userManageService.cod(email);
    return ResponseEntity.ok().build();
  }
}
