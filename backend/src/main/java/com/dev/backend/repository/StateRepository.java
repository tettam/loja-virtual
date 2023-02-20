package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.backend.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State , Long> {
  
}
