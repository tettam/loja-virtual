package com.dev.backend.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.dto.UserClientRequestDTO;
import com.dev.backend.entities.User;
import com.dev.backend.repository.UserClientRepository;

@Service
public class UserClientService {
  
  @Autowired
  private UserClientRepository repository; 

  @Autowired
  private PermissionUserService permissionUserService;

  //Salvar User
  public User insert(UserClientRequestDTO obj){
    User user = new UserClientRequestDTO().convertUser(obj); //Transformando objeto UserClient(DTO) em User
    user.setCreatDate(Instant.now());
    User newUser = repository.saveAndFlush(user); 
    permissionUserService.bindUserPermissionClient(newUser); //Associar as permissões ao User
    return newUser;
  }




}
