package com.dev.backend.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entities.Permission;
import com.dev.backend.entities.PermissionUser;
import com.dev.backend.entities.User;
import com.dev.backend.repository.PermissionRepository;
import com.dev.backend.repository.PermissionUserRepository;

@Service
public class PermissionUserService {
  
  @Autowired
  private PermissionUserRepository permissionUserRepository;

  @Autowired
  private PermissionRepository permissionRepository;

  //Vincular permissão pessoa a permissão User
  public void bindUserPermissionClient(User user){
    List<Permission> listPermission = permissionRepository.findByName("client");
    if(listPermission.size() > 0){
      PermissionUser permissionUser = new PermissionUser();
      permissionUser.setUser(user);
      permissionUser.setPermission(listPermission.get(0));
      permissionUser.setCreatDate(Instant.now());
      permissionUserRepository.saveAndFlush(permissionUser);
    }
  }

}
