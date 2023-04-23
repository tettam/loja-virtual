package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.entities.Permission;
import java.util.List;



public interface PermissionRepository extends JpaRepository<Permission, Long>{
  
  List<Permission> findByName(String name);
}
