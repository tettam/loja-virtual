package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.entities.PermissionUser;


public interface PermissionUserRepository extends JpaRepository<PermissionUser, Long>{

  
  
}
