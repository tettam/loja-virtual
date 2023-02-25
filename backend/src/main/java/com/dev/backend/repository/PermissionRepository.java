package com.dev.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.entities.Permission;


public interface PermissionRepository extends JpaRepository<Permission, Long>{

  public List<Permission> findByName(String name); //Fazer busca e definir o atributo direto no nome
  
}
