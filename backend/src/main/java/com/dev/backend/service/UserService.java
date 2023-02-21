package com.dev.backend.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entities.User;
import com.dev.backend.repository.UserRepository;

@Service
public class UserService {
  
  @Autowired
  private UserRepository repository; 

  public List<User> findAll(){
    return repository.findAll();
  }

  public User insert(User obj){
    obj.setCreatDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public User update(User obj){
    obj.setUpdateDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public void delete(Long id){
    repository.deleteById(id);
  }
}
