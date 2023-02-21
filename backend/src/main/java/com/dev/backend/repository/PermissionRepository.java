package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.entities.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
  
}
