package com.dev.backend.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entities.Permission;
import com.dev.backend.repository.PermissionRepository;

@Service
public class PermissionService {
  
  @Autowired
  private PermissionRepository repository;

  public List<Permission> findAll(){
    return repository.findAll();
  }

  public Permission insert(Permission obj){
    obj.setCreatDate(Instant.now());
    return obj = repository.saveAndFlush(obj);
  }

  public Permission update(Permission obj){
    obj.setUpdateDate(Instant.now());
    return obj = repository.saveAndFlush(obj);
  }

  public void delete(Long id){
    repository.deleteById(id);
  }

}
